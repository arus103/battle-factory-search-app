package com.example.battlefactory.model;

public class Pokemon {
	private int id;
	private String levelType;
	private int level;
	private int group;
	private int round;
	private String name;
	private String type1, type2;
	private String heldItem;
	private String move1, move2, move3, move4;
	private String nature;
	private String ev;
	private int baseHp, baseAtk, baseDef, baseSpAtk, baseSpDef, baseSpeed;
	private int evHp, evAtk, evDef, evSpAtk, evSpDef, evSpeed;
	private int calculatedHp, calculatedAtk, calculatedDef, calculatedSpAtk, calculatedSpDef, calculatedSpeed;
	
	public Pokemon(int id, String levelType, int level, int group, int round, String name, String type1, String type2,
			String heldItem, String move1, String move2, String move3, String move4, String nature, String ev,
			int baseHp, int baseAtk,int baseDef, int baseSpAtk, int baseSpDef, int baseSpeed,
			int evHp, int evAtk, int evDef, int evSpAtk, int evSpDef, int evSpeed) {
		super();
		this.id = id;
		this.levelType = levelType;
		this.level = level;
		this.group = group;
		this.round = round;
		this.name = name;
		this.type1 = type1;
		this.type2 = type2;
		this.heldItem = heldItem;
		this.move1 = move1;
		this.move2 = move2;
		this.move3 = move3;
		this.move4 = move4;
		this.nature = nature;
		this.ev = ev;
		this.baseHp = baseHp;
		this.baseAtk = baseAtk;
		this.baseDef = baseDef;
		this.baseSpAtk = baseSpAtk;
		this.baseSpDef = baseSpDef;
		this.baseSpeed = baseSpeed;
		this.evHp = evHp;
		this.evAtk = evAtk;
		this.evDef = evDef;
		this.evSpAtk = evSpAtk;
		this.evSpDef = evSpDef;
		this.evSpeed = evSpeed;
	}

	public Pokemon() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevelType() {
		return levelType;
	}

	public void setLevelType(String levelType) {
		this.levelType = levelType;
	}
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getHeldItem() {
		return heldItem;
	}

	public void setHeldItem(String heldItem) {
		this.heldItem = heldItem;
	}

	public String getMove1() {
		return move1;
	}

	public void setMove1(String move1) {
		this.move1 = move1;
	}

	public String getMove2() {
		return move2;
	}

	public void setMove2(String move2) {
		this.move2 = move2;
	}

	public String getMove3() {
		return move3;
	}

	public void setMove3(String move3) {
		this.move3 = move3;
	}

	public String getMove4() {
		return move4;
	}

	public void setMove4(String move4) {
		this.move4 = move4;
	}
	
	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public String getEv() {
		return ev;
	}

	public void setEv(String ev) {
		this.ev = ev;
	}

	public int getBaseHp() {
		return baseHp;
	}

	public void setBaseHp(int baseHp) {
		this.baseHp = baseHp;
	}

	public int getBaseAtk() {
		return baseAtk;
	}

	public void setBaseAtk(int baseAtk) {
		this.baseAtk = baseAtk;
	}

	public int getBaseDef() {
		return baseDef;
	}

	public void setBaseDef(int baseDef) {
		this.baseDef = baseDef;
	}

	public int getBaseSpAtk() {
		return baseSpAtk;
	}

	public void setBaseSpAtk(int baseSpAtk) {
		this.baseSpAtk = baseSpAtk;
	}

	public int getBaseSpDef() {
		return baseSpDef;
	}

	public void setBaseSpDef(int baseSpDef) {
		this.baseSpDef = baseSpDef;
	}

	public int getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(int baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public int getEvHp() {
		return evHp;
	}

	public void setEvHp(int evHp) {
		this.evHp = evHp;
	}

	public int getEvAtk() {
		return evAtk;
	}

	public void setEvAtk(int evAtk) {
		this.evAtk = evAtk;
	}

	public int getEvDef() {
		return evDef;
	}

	public void setEvDef(int evDef) {
		this.evDef = evDef;
	}

	public int getEvSpAtk() {
		return evSpAtk;
	}

	public void setEvSpAtk(int evSpAtk) {
		this.evSpAtk = evSpAtk;
	}

	public int getEvSpDef() {
		return evSpDef;
	}

	public void setEvSpDef(int evSpDef) {
		this.evSpDef = evSpDef;
	}

	public int getEvSpeed() {
		return evSpeed;
	}

	public void setEvSpeed(int evSpeed) {
		this.evSpeed = evSpeed;
	}

	public int getCalculatedHp() {
		return calculatedHp;
	}

	public void setCalculatedHp(int calculatedHp) {
		this.calculatedHp = calculatedHp;
	}

	public int getCalculatedAtk() {
		return calculatedAtk;
	}

	public void setCalculatedAtk(int calculatedAtk) {
		this.calculatedAtk = calculatedAtk;
	}

	public int getCalculatedDef() {
		return calculatedDef;
	}

	public void setCalculatedDef(int calculatedDef) {
		this.calculatedDef = calculatedDef;
	}

	public int getCalculatedSpAtk() {
		return calculatedSpAtk;
	}

	public void setCalculatedSpAtk(int calculatedSpAtk) {
		this.calculatedSpAtk = calculatedSpAtk;
	}

	public int getCalculatedSpDef() {
		return calculatedSpDef;
	}

	public void setCalculatedSpDef(int calculatedSpDef) {
		this.calculatedSpDef = calculatedSpDef;
	}

	public int getCalculatedSpeed() {
		return calculatedSpeed;
	}

	public void setCalculatedSpeed(int calculatedSpeed) {
		this.calculatedSpeed = calculatedSpeed;
	}
}