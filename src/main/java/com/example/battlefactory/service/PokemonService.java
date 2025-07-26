package com.example.battlefactory.service;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.battlefactory.model.Pokemon;
import com.example.battlefactory.model.Trainer;
import com.opencsv.CSVReader;

@Service
public class PokemonService {
	private List<Pokemon> allPokemons = new ArrayList<>(); // CSVから読み込んだ全てのポケモンデータ
	
	@Autowired
    private TrainerService trainerService;
	
	// コンストラクタでCSVを読み込み、allPokemonsに格納
	public PokemonService() {
		try (CSVReader reader = new CSVReader(new InputStreamReader(
				getClass().getResourceAsStream("/pokemons.csv"),"UTF-8"))) {
			
			reader.readNext(); // ヘッダー行をスキップ
			String[] line;
			while ((line = reader.readNext()) != null) {
				Pokemon p = new Pokemon();
				p.setId(Integer.parseInt(line[0]));
				p.setLevelType(parseStringOrNull(line[1]));
				p.setLevel(Integer.parseInt(line[2]));
				p.setGroup(Integer.parseInt(line[3]));
				p.setRound(Integer.parseInt(line[4]));
				p.setName(parseStringOrNull(line[5]));
				p.setType1(parseStringOrNull(line[6]));
				p.setType2(parseStringOrNull(line[7]));
				p.setHeldItem(parseStringOrNull(line[8]));
				p.setMove1(parseStringOrNull(line[9]));
				p.setMove2(parseStringOrNull(line[10]));
				p.setMove3(parseStringOrNull(line[11]));
				p.setMove4(parseStringOrNull(line[12]));
				p.setNature(parseStringOrNull(line[13]));
				p.setEv(parseStringOrNull(line[14]));
				p.setBaseHp(Integer.parseInt(line[15]));
				p.setBaseAtk(Integer.parseInt(line[16]));
				p.setBaseDef(Integer.parseInt(line[17]));
				p.setBaseSpAtk(Integer.parseInt(line[18]));
				p.setBaseSpDef(Integer.parseInt(line[19]));
				p.setBaseSpeed(Integer.parseInt(line[20]));
				p.setEvHp(Integer.parseInt(line[21]));
				p.setEvAtk(Integer.parseInt(line[22]));
				p.setEvDef(Integer.parseInt(line[23]));
				p.setEvSpAtk(Integer.parseInt(line[24]));
				p.setEvSpDef(Integer.parseInt(line[25]));
				p.setEvSpeed(Integer.parseInt(line[26]));
				
				
                allPokemons.add(p);
			}
		} catch (Exception e) {
			System.err.println("Error reading pokemons.csv:");
			e.printStackTrace();
			this.allPokemons = Collections.emptyList();
		}
	}
	
	// 文字列がnullまたは空の場合にnullを返すヘルパーメソッド
	private static String parseStringOrNull(String s) {
	    return (s == null || s.trim().isEmpty()) ? null : s.trim();
	}
	
	/**
     * 全てのポケモンデータを返します。
     * コントローラーの初期表示時に使用します。
     * @return 全てのポケモンのリスト
     */
    public List<Pokemon> getAllPokemons() {
        return Collections.unmodifiableList(allPokemons); // 外部からの変更を防ぐためにunmodifiableListで返す
    }
    
    /**
     * 検索条件に基づいてポケモンデータをフィルタリングして返す
     * @param round 周回数 (任意)
     * @param levelType レベルタイプ (任意)
     * @param trainerName トレーナー名 (任意)
     * @param trainerType トレーナータイプ (任意)
     * @param pokemonName ポケモン名 (任意)
     * @param includeTrainerPokemon ネジキなどのID4439以降のトレーナーポケモンを含めるか (trueで含める)
     * @return フィルタリングされたポケモンのリスト
     */
    public List<Pokemon> getPokemons(
            Integer round,
            String levelType,
            String trainerName,
            String trainerType,
            String pokemonName,
            boolean includeTrainerPokemon
    ) {
        // 全てのポケモンからストリームを開始
        List<Pokemon> filteredPokemons = allPokemons.stream()
            .filter(p -> {
                // 1. ポケモン名でのフィルタリング (常に適用)
                boolean matchesPokemonName = (pokemonName == null || pokemonName.isEmpty() ||
                                              (p.getName() != null && p.getName().toLowerCase().contains(pokemonName.toLowerCase())));

                // 2. ネジキなどのID4439以降のポケモンを含めるかどうかの判定
                // includeTrainerPokemonがtrueなら常に含み、falseならIDが4439未満のみ含める
                boolean isSpecialTrainerPokemon = p.getId() >= 4439;
                boolean shouldIncludeById = includeTrainerPokemon || !isSpecialTrainerPokemon;

                // 3. トレーナー関連の条件 (trainerName, trainerType) が指定されているか判定
                boolean hasTrainerCriteria = (trainerName != null && !trainerName.isEmpty()) ||
                                             (trainerType != null && !trainerType.isEmpty());

                if (hasTrainerCriteria) {
                    // トレーナー関連の条件がある場合、トレーナーをフィルタリングし、関連ポケモンIDを抽出
                    List<Trainer> allTrainers = Optional.ofNullable(trainerService.getAll())
                                .orElse(Collections.emptyList());

                    Set<Integer> targetPokemonIds = new HashSet<>();
                    allTrainers.stream()
                        .filter(t -> Objects.equals(levelType, t.getLevelType()) || (levelType == null || levelType.isEmpty()))
                        .filter(t -> round == null || (t.getRoundList() != null && t.getRoundList().contains(round)))
                        .filter(t -> Objects.equals(trainerName, t.getTrainerName()) || (trainerName == null || trainerName.isEmpty()))
                        .filter(t -> Objects.equals(trainerType, t.getTrainerType()) || (trainerType == null || trainerType.isEmpty()))
                        .forEach(t -> {
                            if (t.getPokemonIds() != null) {
                                String[] idRange = t.getPokemonIds().split("-");
                                if (idRange.length == 2) {
                                    int start = Integer.parseInt(idRange[0]);
                                    int end = Integer.parseInt(idRange[1]);
                                    for (int i = start; i <= end; i++) {
                                        targetPokemonIds.add(i);
                                    }
                                } else if (idRange.length == 1) {
                                    targetPokemonIds.add(Integer.parseInt(idRange[0]));
                                }
                            }
                        });

                    // 現在のポケモンpが、フィルタリングされたトレーナーに関連するポケモンIDに含まれているか
                    return matchesPokemonName && shouldIncludeById && targetPokemonIds.contains(p.getId());

                } else {
                    // トレーナー関連の条件がない場合、ポケモン自身のレベルタイプと周回でフィルタリング
                    boolean matchesLevelType = (levelType == null || levelType.isEmpty() || Objects.equals(levelType, p.getLevelType()));
                    boolean matchesRound = (round == null || Objects.equals(round, p.getRound()));

                    return matchesPokemonName && shouldIncludeById && matchesLevelType && matchesRound;
                }
            })
            .collect(Collectors.toList());

        return filteredPokemons;
    }

    public List<Pokemon> findPokemonsByIds(List<String> ids) {
        Set<Integer> idSet = ids.stream()
                               .map(Integer::parseInt)
                               .collect(Collectors.toSet());
        return allPokemons.stream()
                .filter(p -> idSet.contains(p.getId()))
                .toList();
    }
}