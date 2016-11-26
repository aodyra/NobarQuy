/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.io.IOException;
import java.util.ArrayList;
import twitter.crawler.TweetCrawler;
import twitter4j.JSONObject;

/**
 *
 * @author ryanyonata
 */
public class Main {
        public static void main (String[] args) throws IOException, Exception {
        NobarQuy nq = new NobarQuy();
        String filename = "nobar.txt";
        String path = "model/nobar.model";
        
        //nq.loadModel();
        //nq.classifyTweetFromFile(filename);

        TweetCrawler tcrwl = new TweetCrawler();
        ArrayList<JSONObject> tweets = new ArrayList<>();
        tweets = tcrwl.getJSON(100, "nobar vs");
        
        nq.loadModel();
        nq.classifyTweetFromJSON(tweets);
     }
}
