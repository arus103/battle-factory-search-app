package com.example.battlefactory.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.battlefactory.model.Trainer;
import com.opencsv.CSVReader;

@Service
public class TrainerService {
    private List<Trainer> trainers = new ArrayList<>();

    public TrainerService() {
            ClassPathResource resource = new ClassPathResource("trainers.csv");
            try (InputStream input = resource.getInputStream();
                 CSVReader reader = new CSVReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
        	
            	reader.readNext();
            	String[] line;
			
            	while ((line = reader.readNext()) != null) {
            	Trainer t = new Trainer();
            	t.setLevelType(parseStringOrNull(line[0]));
				t.setRounds(parseStringOrNull(line[1]));
				t.setTrainerType(parseStringOrNull(line[2]));
				t.setTrainerName(parseStringOrNull(line[3]));
				t.setPokemonIds(parseStringOrNull(line[4]));
				t.setRoundList(parseRounds(t.getRounds()));
					
				trainers.add(t);
            }
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to load trainers.csv", e);
    }
}
	
    public List<Trainer> findByFilters(Integer round, String levelType, String trainerType, String trainerName) {
        return trainers.stream()
                .filter(t -> (levelType == null || levelType.equals(t.getLevelType())))
                .filter(t -> (round == null || matchRound(t.getRounds(), round)))
                .filter(t -> (trainerType == null || trainerType.equals(t.getTrainerType())))
                .filter(t -> (trainerName == null || trainerName.equals(t.getTrainerName())))
                .toList();
    }
    
    private boolean matchRound(String roundRange, int round) {
        if (roundRange == null) return false;
        try {
            if (roundRange.contains("-")) {
                String[] parts = roundRange.split("-");
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                return round >= start && round <= end;
            } else {
                return Integer.parseInt(roundRange) == round;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
	private static String parseStringOrNull(String s) {
	    return (s == null || s.trim().isEmpty()) ? null : s.trim();
	}
    
	private static List<Integer> parseRounds(String rounds) {
	    List<Integer> result = new ArrayList<>();
	    if (rounds == null || rounds.trim().isEmpty()) return result;

	    for (String part : rounds.split(",")) {
	        part = part.trim();
	        if (part.isEmpty()) continue;
	        try {
	        	if (part.contains("-")) {
	        		String[] range = part.split("-");
	        		int start = Integer.parseInt(range[0]);
	        		int end = Integer.parseInt(range[1]);
	        		for (int i = start; i <= end; i++) {
	        			result.add(i);
	        		}
	        	} else {
	        		result.add(Integer.parseInt(part));
	        	}
	        } catch (NumberFormatException e) {
	        	e.printStackTrace();
	        }
	    }
	    return result;
	}
	
    public List<Trainer> getAll() {
        return trainers;
    }
}
