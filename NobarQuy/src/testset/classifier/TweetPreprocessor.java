/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testset.classifier;

import IndonesianNLP.IndonesianSentenceDetector;
import IndonesianNLP.IndonesianSentenceFormalization;
import IndonesianNLP.IndonesianStemmer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import training.classifier.TrainingClassifier;

/**
 *
 * @author ryanyonata
 */
public class TweetPreprocessor {
    
    
    IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
    IndonesianSentenceDetector detector = new IndonesianSentenceDetector();
    
    public String preprocessTweet(String tweet) {
        String sentence = tweet;
        String stemmed;
        
        sentence = formalizer.normalizeSentence(sentence);
        formalizer.initStopword();
        sentence = formalizer.deleteStopword(sentence);        
        IndonesianStemmer stemmer = new IndonesianStemmer();
        stemmed = stemmer.stem(sentence);
        stemmed = stemmer.stemSentence(stemmed);
        stemmed = stemmer.stemRepeatedWord(stemmed);
        
        return stemmed;
    }
    
    public List<String> convertTxtToStringList(File file) throws FileNotFoundException {
        List<String> list = new ArrayList<>();
 
        Scanner text = new Scanner(file);
        while(text.hasNextLine()){
            String line = text.nextLine();
            //System.out.println(line);
            list.add(line);
        }
        return list;
    }
}
