/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmad
 */
public class StringToWordProcessor {
    
    private Map<String, Integer> totalWords;
    private int curIdx;

    /**
     *
     */
    public StringToWordProcessor() {
        totalWords = new HashMap<>();
        curIdx = 0;
    }
    
    /**
     * 
     * @param l
     * @return 
     */
    public Map<String, Integer> stringAttribute(List<String> l) {
        
        for (String line : l) {
            String words[] = line.split(" ");
            for (String word : words) {
                if (word.length() > 1 && !word.matches("[0-9]+") && !totalWords.containsKey(word)) {
                    totalWords.put(word, curIdx);
                    curIdx++;
                }
            }
        }
        
        return totalWords;
    }
    
}
