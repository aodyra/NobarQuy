/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import testset.classifier.NobarInformation;

/**
 *
 * @author ryanyonata
 */
public class InformationExtraction {
    //TODO: List Regex buat ekstrak informasi: match, lokasi, sama date
    public static final String REGEX_MATCH = "(.*)( vs )(.*)|(.*)( x )(.*)|(.*)( X )(.*)";
    public static final String REGEX_PLACE = "(di +.*)|(at + .*)";
    
    private NobarInformation nobarinfo;
    private String tweet;
    
    public InformationExtraction(){}
    
    public InformationExtraction(String tweet) {
        this.nobarinfo = new NobarInformation();
    }
    
    public void extractMatch() {
        String extractedMatch;
        Pattern pattern = Pattern.compile(REGEX_MATCH);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            
        }
    }
}
