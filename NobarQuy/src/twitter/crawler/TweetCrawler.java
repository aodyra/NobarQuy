/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitter.crawler;

import java.util.ArrayList;
import java.util.List;
import twitter4j.TwitterException;

/**
 *
 * @author ryanyonata
 */
public class TweetCrawler {
    public ArrayList<String> getJSON (int REQUEST, String SEARCHTAG) throws TwitterException {
        ArrayList<String> result = new ArrayList();
        TwitCatStatusEater tweater = new TwitCatStatusEater();
        List<EatenTweets> tweets = tweater.eat(REQUEST, SEARCHTAG);
        tweets.stream().forEach((tweet) -> {
            result.add("{\"label\":null,\"created_at\":\""+tweet.getDate()+"\",\"text\":\""+TwitCatStatusEater.cleanText(tweet.getText())+"\"");
        });
        return result;
    }
}
