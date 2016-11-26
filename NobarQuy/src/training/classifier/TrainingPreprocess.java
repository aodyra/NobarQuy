/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.classifier;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author fauzanrifqy
 */
public class TrainingPreprocess {
    
    public final static String REGEX_RTAT = "RT @\\w+";
    public final static String REGEX_EMOTICON = "\\\\u\\w+";
    public final static String REGEX_URL = "https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_+.~#?&//=]*)";
   
    
    public TrainingPreprocess(){}
    
    public List<String> spamStemming(File file){
        List<String> processedSentences = new ArrayList<>();
        try {
            Scanner text = new Scanner(file);
            IndonesianSentenceFormalization formalizer = new IndonesianSentenceFormalization();
            IndonesianSentenceDetector detector = new IndonesianSentenceDetector();
            
            while(text.hasNextLine()){
                String line = text.nextLine();
                //line = line.toLowerCase();
                String sentence = line;
                String stemmed;
                
                //Hapus \n, RT @, dan emoticon
                sentence = sentence.replace("\\n", " ");
                //sentence = replaceChars(REGEX_RTAT,sentence,"");
                //sentence = replaceChars(REGEX_EMOTICON,sentence,"");
                
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
                processedSentences.add(stemmed);
            } 
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TrainingClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return processedSentences;
    }
    
    public static String replaceChars (String regex, String text1, String text2){
        String matchedString;
        String result = text1;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(result);
        if(matcher.find()){
            matchedString = matcher.group(0);
            result = result.replace(matchedString, text2);
        }
        return result;
    }
    
    
}
