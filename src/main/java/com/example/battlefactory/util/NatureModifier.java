package com.example.battlefactory.util;

import java.util.Map;

public class NatureModifier {
	public static final Map<String, double[]> NATURE_MAP = Map.ofEntries(
		Map.entry("さみしがり", new double[] {1.1, 0.9, 1.0, 1.0, 1.0}),
		Map.entry("いじっぱり", new double[] {1.1, 1.0, 0.9, 1.0, 1.0}),
		Map.entry("やんちゃ", new double[] {1.1, 1.0, 1.0, 0.9, 1.0}),
		Map.entry("ゆうかん", new double[] {1.1, 1.0, 1.0, 1.0, 0.9}),
		Map.entry("ずぶとい", new double[] {0.9, 1.1, 1.0, 1.0, 1.0}),
		Map.entry("わんぱく", new double[] {1.0, 1.1, 0.9, 1.0, 1.0}),
		Map.entry("のうてんき", new double[] {1.0, 1.1, 1.0, 0.9, 1.0}),
		Map.entry("のんき", new double[] {1.0, 1.1, 1.0, 1.0, 0.9}),
		Map.entry("ひかえめ", new double[] {0.9, 1.0, 1.1, 1.0, 1.0}),
		Map.entry("おっとり", new double[] {1.0, 0.9, 1.1, 1.0, 1.0}),
		Map.entry("うっかりや", new double[] {1.0, 1.0, 1.1, 0.9, 1.0}),
		Map.entry("れいせい", new double[] {1.0, 1.0, 1.1, 1.0, 0.9}),
		Map.entry("おだやか", new double[] {0.9, 1.0, 1.0, 1.1, 1.0}),
		Map.entry("おとなしい", new double[] {1.0, 0.9, 1.0, 1.1, 1.0}),
		Map.entry("しんちょう", new double[] {1.0, 1.0, 0.9, 1.1, 1.0}),
		Map.entry("なまいき", new double[] {1.0, 1.0, 1.0, 1.1, 0.9}),
		Map.entry("おくびょう", new double[] {0.9, 1.0, 1.0, 1.0, 1.1}),
		Map.entry("せっかち", new double[] {1.0, 0.9, 1.0, 1.0, 1.1}),
		Map.entry("ようき", new double[] {1.0, 1.0, 0.9, 1.0, 1.1}),
		Map.entry("むじゃき", new double[] {1.0, 1.0, 1.0, 0.9, 1.1})
	);
	public static double get(String nature, int index) {
		return NATURE_MAP.getOrDefault(nature, new double[] {1.0, 1.0, 1.0, 1.0, 1.0})[index];
	}
}
