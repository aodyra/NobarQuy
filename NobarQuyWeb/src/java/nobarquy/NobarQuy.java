/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import testset.classifier.NobarClassifier;
import testset.classifier.TweetPreprocessor;
import training.classifier.MapUtil;
import training.classifier.StringToWordProcessor;
import training.classifier.TrainingClassifier;
import twitter4j.JSONObject;

/**
 *
 * @author ryanyonata
 */
public class NobarQuy {
    protected NobarClassifier nobarClassifier;
    
    public NobarQuy() throws Exception {
        nobarClassifier = new NobarClassifier();
    }
    
    public NobarQuy(String path) throws Exception {
        nobarClassifier = new NobarClassifier(path);
    }
    
    public void buildModel() {
        TrainingClassifier tc = new TrainingClassifier();
        String arff = tc.generateARFF();
    }
    
    public void loadModel() throws Exception {
        nobarClassifier.loadModel();
    }
    
    public void classifyTweetFromFile(String path) throws Exception {
        TweetPreprocessor tw = new TweetPreprocessor();
        List<String> nobarList = new ArrayList<>();
        File file = new File(path);
        nobarList = tw.convertTxtToStringList(file);
        List<String[]> result = new ArrayList();
        
        for (int i=0; i< nobarList.size(); i++) {
            String[] record = new String[3];
            record[0] = nobarList.get(i);
            
            String nobar = nobarClassifier.classifyUnseenData(nobarList.get(i));
            //System.out.println("Hasil klasifikasi: " + nobar);
            
            if(nobar.equals("1")) {
                System.out.println("Nobar" + nobarList.get(i));
                record[1] = "Nobar";
                
            } else {
                record[1] = "Bukan";
            }
            result.add(record);
        }
    }
    
    public void classifyTweetFromJSON(ArrayList<JSONObject> tweets) throws Exception {
        List<String[]> result = new ArrayList();
        for (JSONObject tweet : tweets) {
            String tweet_text = tweet.get("text").toString();
            String[] record = new String[3];
            record[0] = tweet_text;
            
            String nobar = nobarClassifier.classifyUnseenData(tweet_text);
            //System.out.println("Hasil klasifikasi: " + nobar);
            
            if(nobar.equals("1")) {
                System.out.println("Nobar: " + tweet_text);
                record[1] = "Nobar";
                //TODO: Information Extraction, save in JSON
                
            } else {
                record[1] = "Bukan";
            }
            result.add(record);
        }
    }
    
    public ArrayList<JSONObject> classifyTweetFunction(ArrayList<JSONObject> tweets) throws Exception {
        ArrayList<JSONObject> finalJSON = new ArrayList<>();
        List<String[]> result = new ArrayList();
        for (JSONObject tweet : tweets) {
            String tweet_text = tweet.get("text").toString();
            String[] record = new String[3];
            record[0] = tweet_text;
            
            String nobar = nobarClassifier.classifyUnseenData(tweet_text);
            //System.out.println("Hasil klasifikasi: " + nobar);
            
            if(nobar.equals("1")) {
                System.out.println("Nobar: " + tweet_text);
                InformationExtraction ie = new InformationExtraction(tweet_text);
                ie.extractAllNobarInfo();
                if ((ie.getNobarInfo().getMatch() != null) && (ie.getNobarInfo().getPlace() != null) && (ie.getNobarInfo().getDate() != null)) {
                    record[1] = "Nobar";
                    JSONObject json = new JSONObject();
                    json.put("label","1");
                    json.put("tweet",tweet_text);
                    //TODO: Information Extraction, save in JSON

                    finalJSON.add(json);
                }
                
                
            } else {
                record[1] = "Bukan";
            }
            result.add(record);
        }
        return finalJSON;
    }
    
    public ArrayList<JSONObject> classifyTweetFromFile2 (String path) throws Exception {
        ArrayList<JSONObject> finalJSON = new ArrayList<>();
        TweetPreprocessor tw = new TweetPreprocessor();
        List<String> nobarList = new ArrayList<>();
        File file = new File(path);
        nobarList = tw.convertTxtToStringList(file);
        List<String[]> result = new ArrayList();
        
        for (int i=0; i< nobarList.size(); i++) {
            String[] record = new String[3];
            record[0] = nobarList.get(i);
            
            String nobar = nobarClassifier.classifyUnseenData(nobarList.get(i));
            //System.out.println("Hasil klasifikasi: " + nobar);
            
            if(nobar.equals("1")) {
                System.out.println("Nobar" + nobarList.get(i));
                record[1] = "Nobar";
                JSONObject json = new JSONObject();
                json.put("label","1");
                json.put("tweet",nobarList.get(i));
                //InformationExtraction
                finalJSON.add(json);
                
            } else {
                record[1] = "Bukan";
            }
            result.add(record);
        }
        return finalJSON;
    }
}
