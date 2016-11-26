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
    public static final String REGEX_MATCH = "\\w+\\s+((V|v)(S|s)|(x|X)|(v|V))\\s+\\w+";
    public static final String REGEX_PLACE = "(di|at|venue)\\s+\\w+";
    public static final String REGEX_DATE = "(senin|selasa|rabu|kamis|jumat|jum'at|sabtu|minggu|mnggu|sbtu|mggu)[, (]*\\d{1,2}([- /\\.])?([a-zA-Z]+|\\d{1,2})([- /\\.])+\\d{0,4}\\)?";
    public static final String REGEX_TIME = "\\d{1,2}[:\\.]\\d{1,2}";
    
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
            extractedMatch = "null";
        }
        
        nobarinfo.setMatch(extractedMatch);
    }
    
    public void extractPlace() {
        String extractedPlace;
        
        Pattern pattern = Pattern.compile(REGEX_PLACE);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            extractedPlace = matcher.group(0);
        } else {
            extractedPlace = null;
        }
        
        nobarinfo.setPlace(extractedPlace);
    }
    
    public void extractDate() {
        String extractedDate;
        
        Pattern pattern = Pattern.compile(REGEX_DATE);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            extractedDate = matcher.group(0);
        } else {
            extractedDate = null;
        }
        
        nobarinfo.setDate(extractedDate);
    }
    
    public void extractTime() {
        String extractedTime;
        
        Pattern pattern = Pattern.compile(REGEX_TIME);
        Matcher matcher = pattern.matcher(tweet);
        
        if (matcher.find()) {
            extractedTime = matcher.group(0);
        } else {
            extractedTime = null;
        }
        
        nobarinfo.setTime(extractedTime);
    }
    
    public void extractAllNobarInfo() {
        extractMatch();
        extractPlace();
        extractDate();
        extractTime();
    }
}
