package com.example.battlefactory.model;

import java.util.List;

public class Trainer {
	private String levelType;
	private String rounds;
	private String trainerType;
	private String trainerName;
	private String pokemonIds;
	
	private List<Integer> roundList;

	public List<Integer> getRoundList() {
	    return roundList;
	}

	public void setRoundList(List<Integer> roundList) {
	    this.roundList = roundList;
	}
	
	public Trainer(String levelType, String rounds, String trainerType, String trainerName, String pokemonIds) {
		super();
		this.levelType = levelType;
		this.rounds= rounds;
		this.trainerType = trainerType;
		this.trainerName = trainerName;
		this.pokemonIds = pokemonIds;
	}
	
	public Trainer() {
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}

	public String getRounds() {
		return rounds;
	}

	public void setRounds(String rounds) {
		this.rounds = rounds;
	}
	
	public String getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(String trainerType) {
		this.trainerType = trainerType;
	}

	public String getTrainerName() {
		return trainerName;
	}

	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}

	public String getPokemonIds() {
		return pokemonIds;
	}

	public void setPokemonIds(String pokemonIds) {
		this.pokemonIds = pokemonIds;
	}
}
