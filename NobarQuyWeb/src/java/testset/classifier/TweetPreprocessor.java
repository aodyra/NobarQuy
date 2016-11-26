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
import training.classifier.TrainingPreprocess;

/**
 *
 * @author ryanyonata
 */
public class TweetPreprocessor {
    public final static String REGEX_RTAT = "RT @\\\\w+";
    public final static String REGEX_EMOTICON = "\\\\\\\\u\\\\w+";
    public final static String REGEX_URL = "https?:\\\\/\\\\/(www\\\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\\\.[a-z]{2,6}\\\\b([-a-zA-Z0-9@:%_+.~#?&//=]*)";
    
    IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
    IndonesianSentenceDetector detector = new IndonesianSentenceDetector();
    
    public String preprocessTweet(String tweet) {
        String sentence = tweet;
        String stemmed;
        
        //Hapus \n, RT @, dan emoticon
        TrainingPreprocess t = new TrainingPreprocess();
        sentence = sentence.replace("\\n", " ");
        sentence = t.replaceChars(REGEX_RTAT,sentence,"");
        sentence = t.replaceChars(REGEX_EMOTICON,sentence,"");
        sentence = t.replaceChars(REGEX_URL,sentence,"URL");

        //Replace emoticon bola
        sentence = sentence.replace("\\u26bd", "#emotbola");

        //Normalisasi
        sentence = formalizer.normalizeSentence(sentence);
        //Stop word elimination
        formalizer.initStopword();
        sentence = formalizer.deleteStopword(sentence);
        //Stemming
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
            list.add(line);
        }
        return list;
    }
}
