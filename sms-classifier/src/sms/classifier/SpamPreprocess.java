/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.classifier;

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

/**
 *
 * @author fauzanrifqy
 */
public class SpamPreprocess {
    
    public SpamPreprocess(){}
    
    public List<String> spamStemming(File file){
        List<String> processedSentences = new ArrayList<>();
        try {
            
            Scanner text = new Scanner(file);
            IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
            IndonesianSentenceDetector detector = new IndonesianSentenceDetector();
            
            while(text.hasNextLine()){
                String line = text.nextLine();
                line = line.toLowerCase();
                String sentence = line;
                String stemmed;
                //ArrayList<String> sentences = new ArrayList<String>();
                //sentences = detector.splitSentence(line);
                //for (String sentence : sentences){
                    System.out.println("original: "+sentence);
                    sentence = formalizer.normalizeSentence(sentence);
                    System.out.println("normalized: "+sentence);
                    formalizer.initStopword();
                    sentence = formalizer.deleteStopword(sentence);
                    System.out.println("no stopword: "+sentence);
                    IndonesianStemmer stemmer = new IndonesianStemmer();
                    stemmed = stemmer.stem(sentence);
                    stemmed = stemmer.stemSentence(stemmed);
                    stemmed = stemmer.stemRepeatedWord(stemmed);

                    // Debugging print out
                    //System.out.println(line);
                    System.out.println("stemmed: "+stemmed+"\n\n");
                //}
                processedSentences.add(stemmed);
            } 
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SmsClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return processedSentences;
    }
}
