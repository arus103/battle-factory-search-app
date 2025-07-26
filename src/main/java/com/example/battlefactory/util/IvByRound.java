package com.example.battlefactory.util;

public class IvByRound {
	public static int getIvByRound(int round) {
		if (round == 8) {
			return 31;
		} else {
			return (round - 1) * 4;
		}
	}
}
