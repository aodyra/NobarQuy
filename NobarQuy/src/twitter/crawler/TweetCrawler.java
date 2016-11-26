/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.TwitterException;

/**
 *
 * @author ryanyonata
 */
public class TweetCrawler {
    public ArrayList<JSONObject> getJSON (int REQUEST, String SEARCHTAG) throws TwitterException {
        ArrayList<JSONObject> result = new ArrayList();
        TwitCatStatusEater tweater = new TwitCatStatusEater();
        List<EatenTweets> tweets = tweater.eat(REQUEST, SEARCHTAG);
        tweets.stream().forEach((tweet) -> {
            JSONObject obj = new JSONObject();
            try {
                obj.put("label", "?");
                obj.put("created_at",tweet.getDate());
                obj.put("text",TwitCatStatusEater.cleanText(tweet.getText()));
                result.add(obj);
            } catch (JSONException ex) {
                Logger.getLogger(TweetCrawler.class.getName()).log(Level.SEVERE, null, ex);
            } 
            //result.add("{\"label\":null,\"created_at\":\""+tweet.getDate()+"\",\"text\":\""+TwitCatStatusEater.cleanText(tweet.getText())+"\"");
        });
        return result;
    }
}
