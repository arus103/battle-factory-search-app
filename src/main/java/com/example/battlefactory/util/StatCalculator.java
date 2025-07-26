package com.example.battlefactory.util;

public class StatCalculator {
    public static int calcHp(int base, int iv, int ev, int level) {
    	// ヌケニンのHP実数値の例外処理
        if (base == 1) {
            return 1; // ヌケニンのHPは常に1
        }
    	
        // まず努力値 / 4 を切り捨て
        double evTerm = Math.floor(ev / 4.0);
        // コアとなる計算を行い、その結果を切り捨て
        double coreTerm = Math.floor((base * 2 + iv + evTerm) * level / 100.0);
        return (int)(coreTerm + level + 10);
    }

    public static int calcStat(int base, int iv, int ev, int level, double natureMod) {
        // まず努力値 / 4 を切り捨て
        double evTerm = Math.floor(ev / 4.0);
        // 計算式の最初の部分を計算し、その結果を切り捨て
        double part1 = Math.floor((base * 2 + iv + evTerm) * level / 100.0);
        // 5を加え、性格補正を適用し、最終結果を切り捨て
        return (int)Math.floor((part1 + 5) * natureMod);
    }
}