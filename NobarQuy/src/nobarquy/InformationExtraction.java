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
        this.tweet = tweet;
    }
    
    public NobarInformation getNobarInfo() {
        return this.nobarinfo;
    }
    
    public String getTweet() {
        return this.tweet;
    }
    
    public void extractMatch() {
        String extractedMatch;
        Pattern pattern = Pattern.compile(REGEX_MATCH);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            extractedMatch = matcher.group(0);
        } else {
            extractedMatch = "Football Match, see Original Tweet";
        }
        
        nobarinfo.setMatch(extractedMatch);
    }
    
    public void extractPlace() {
        String extractedPlace;
        
        Pattern pattern = Pattern.compile(REGEX_MATCH);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            extractedPlace = matcher.group(0);
        } else {
            extractedPlace = "Place";
        }
        
        nobarinfo.setMatch(extractedPlace);
    }
    
    public void extractdate() {
        String extractedDate;
        
        Pattern pattern = Pattern.compile(REGEX_MATCH);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            extractedDate = matcher.group(0);
        } else {
            extractedDate = "Place";
        }
        
        nobarinfo.setMatch(extractedDate);
    }
    
    public void extractAllNobarInfo() {
        extractMatch();
        extractPlace();
    }
}
