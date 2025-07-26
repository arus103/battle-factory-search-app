package com.example.battlefactory.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.battlefactory.model.Pokemon;
import com.example.battlefactory.model.Trainer;
import com.example.battlefactory.service.PokemonService;
import com.example.battlefactory.service.TrainerService;
import com.example.battlefactory.util.IvByRound;
import com.example.battlefactory.util.NatureModifier;
import com.example.battlefactory.util.StatCalculator;

@Controller
public class HomeController {
	
	@Autowired
    private TrainerService trainerService;

    @Autowired
    private PokemonService pokemonService;

	
	@GetMapping("/")
	public String index(
			@RequestParam(required = false) String levelType,
			@RequestParam(required = false) Integer round,
			@RequestParam(required = false) String trainerType,
            @RequestParam(required = false) String trainerName,
            @RequestParam(required = false) String pokemonName,
            @RequestParam(required = false) String pokemonType,
            @RequestParam(required = false) String moveName,
            Model model) {
		
		// 各検索パラメータが実際に指定されているかどうかのフラグ
        boolean hasLevelType = (levelType != null && !levelType.isEmpty());
        boolean hasRound = (round != null);
        boolean hasTrainerType = (trainerType != null && !trainerType.isEmpty());
        boolean hasTrainerName = (trainerName != null && !trainerName.isEmpty());
        boolean hasPokemonName = (pokemonName != null && !pokemonName.isEmpty());
        boolean hasPokemonType = (pokemonType != null && !pokemonType.isEmpty());
        boolean hasMoveName = (moveName != null && !moveName.isEmpty());

        // ページ初回ロード時（検索パラメータが何も指定されていない状態）を判定
        boolean isInitialLoad = !(hasLevelType || hasRound || hasTrainerType || hasTrainerName ||
        		hasPokemonName || hasPokemonType || hasMoveName);
        
        
		List<Trainer> allTrainers = trainerService.getAll();
		
		List<String> uniqueLevelTypes = Optional.ofNullable(allTrainers.stream()
			    .map(Trainer::getLevelType)
			    .filter(Objects::nonNull)
			    .map(String::trim)
			    .filter(s -> !s.isEmpty())
			    .distinct()
			    .collect(Collectors.toList())
		).orElse(new ArrayList<>());
	    
		List<Pokemon> pokemonsToDisplay;
		if (isInitialLoad || !hasLevelType) {
            pokemonsToDisplay = Collections.emptyList();
        } else {
        	// トレーナー関連の検索条件が指定されているかどうかのフラグ
            // round, trainerType, trainerName のいずれかが指定されていればtrue
            boolean includeSpecialTrainerPokemon = hasTrainerType || hasTrainerName;

            // PokemonServiceから基本フィルタリング済みのポケモンリストを取得
            // hasAnyTrainerSearchがtrueの場合、ネジキのポケモンを含める (includeTrainerPokemon = true)
            // hasAnyTrainerSearchがfalseの場合、ネジキのポケモンを含めない (includeTrainerPokemon = false)
            pokemonsToDisplay = Optional.ofNullable(
                    pokemonService.getPokemons(
                            hasRound ? round : null,
                            levelType,
                            trainerName,
                            trainerType,
                            pokemonName,
                            includeSpecialTrainerPokemon
                    )
            ).orElse(Collections.emptyList());

            
            // タイプでフィルタリング
            if (hasPokemonType) {
                pokemonsToDisplay = pokemonsToDisplay.stream()
                        .filter(p -> (p.getType1() != null && p.getType1().equals(pokemonType)) ||
                                     (p.getType2() != null && p.getType2().equals(pokemonType)))
                        .collect(Collectors.toList());
            }

            // 技でフィルタリング - 技1のみを対象とし、完全一致で検索
            if (hasMoveName) {
                pokemonsToDisplay = pokemonsToDisplay.stream()
                        .filter(p -> (p.getMove1() != null && p.getMove1().equals(moveName)))
                        .collect(Collectors.toList());
            }
        }
		
     // ポケモンの実数値計算ロジック
        pokemonsToDisplay.forEach(p -> {
			// ここでのp.getRound()は、CSVから読み込まれた各ポケモンの本来の周回数を使用
			int iv = IvByRound.getIvByRound(p.getRound());
			p.setCalculatedHp(StatCalculator.calcHp(p.getBaseHp(), iv, p.getEvHp(), p.getLevel()));
			p.setCalculatedAtk(StatCalculator.calcStat(p.getBaseAtk(), iv, p.getEvAtk(), p.getLevel(),
					NatureModifier.get(p.getNature(), 0)));
			p.setCalculatedDef(StatCalculator.calcStat(p.getBaseDef(), iv, p.getEvDef(), p.getLevel(),
					NatureModifier.get(p.getNature(), 1)));
			p.setCalculatedSpAtk(StatCalculator.calcStat(p.getBaseSpAtk(), iv, p.getEvSpAtk(), p.getLevel(),
					NatureModifier.get(p.getNature(), 2)));
			p.setCalculatedSpDef(StatCalculator.calcStat(p.getBaseSpDef(), iv, p.getEvSpDef(), p.getLevel(),
					NatureModifier.get(p.getNature(), 3)));
			p.setCalculatedSpeed(StatCalculator.calcStat(p.getBaseSpeed(), iv, p.getEvSpeed(), p.getLevel(),
					NatureModifier.get(p.getNature(), 4)));
		});	
		
		// モデルに属性を追加
		model.addAttribute("pokemons", pokemonsToDisplay); // 表示するポケモンリスト
        model.addAttribute("trainers", allTrainers); // JSで利用するため、常に全てのトレーナーデータを渡す
        model.addAttribute("uniqueLevelTypes", uniqueLevelTypes); // レベルタイプセレクトボックス用
        
        model.addAttribute("levelType", levelType);
        model.addAttribute("round", round);
        model.addAttribute("trainerName", trainerName);
        model.addAttribute("trainerType", trainerType);
        model.addAttribute("pokemonName", pokemonName);
        model.addAttribute("pokemonType", pokemonType);
        model.addAttribute("moveName", moveName);
        
        model.addAttribute("isInitialLoad", isInitialLoad);
        
        return "index";
	}
}
