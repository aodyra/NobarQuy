/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.io.IOException;
import java.util.ArrayList;
import testset.classifier.NobarInformation;
import twitter.crawler.TweetCrawler;
import twitter4j.JSONObject;
import IndonesianNLP.IndonesianNETagger;
import IndonesianNLP.IndonesianSentenceFormalization;

/**
 *
 * @author ryanyonata
 */
public class Main {
    public static void main (String[] args) throws IOException, Exception {
//        NobarQuy nq = new NobarQuy();
//        String filename = "nobar.txt";
//        String path = "model/nobar.model";    
        String tweet = "Nobar Chelsea vs Tottenham sabtu, 21 november 2016 21.00 di APA";
        
        //nq.loadModel();
        //nq.classifyTweetFromFile(filename);

        /*TweetCrawler tcrwl = new TweetCrawler();
        ArrayList<JSONObject> tweets = new ArrayList<>();
        tweets = tcrwl.getJSON(100, "nobar vs");
        
        nq.loadModel();
        nq.classifyTweetFromJSON(tweets);*/
        InformationExtraction ie = new InformationExtraction(tweet);
        ie.extractAllNobarInfo();
        System.out.println("Tweet: " + ie.getTweet());
        NobarInformation nobarinfo = ie.getNobarInfo();
        System.out.println("Match: " + nobarinfo.getMatch());
        System.out.println("Place: " + nobarinfo.getPlace());
        System.out.println("Date: " + nobarinfo.getDate());
        System.out.println("Time: " + nobarinfo.getTime());
//        System.out.println("Match: " + tim1 + "vs" + tim2);
//        System.out.println("Tempat: " + tempat);
//        System.out.println("Jam: " + waktu);
//        IndonesianSentenceFormalization formal = new IndonesianSentenceFormalization();
//        IndonesianNETagger net = new IndonesianNETagger();
//        for(String s: net.extractNamedEntity(formal.normalizeSentence(tweet.toLowerCase()))){
//        for(String s: net.extractNamedEntity(formal.normalizeSentence(tweet))){
//            System.out.println(s);
//        }
     }
}

