/** * Credits to Charles McGuinness */
package twitter.crawler;

import java.util.ArrayList;
import java.util.List;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;
import java.util.Map;
import twitter.crawler.EatenTweets;


public class TwitCatStatusEater {
    private static final String CONSUMER_KEY = "mASc5bPjmxUd6Pwwnz7M7rPz0";
    private static final String CONSUMER_SECRET = "TF2qhPSMefXLdWGV1AWHlyeg25lP9CEtRoxwAhg22dJGf8xNAu";
    private static final String ACCESS_TOKEN = "57809695-0pCM0qEkBiL4prFfd5Epj0Y1tYOxvqrXYPdC0OowO";
    private static final String ACCESS_TOKEN_SECRET = "uSNhB4JAtgZ117McL4cJ1QiGzVcFhts9AEAnLRFte29o0";
    // Maximum allowed tweets retreived per call
    static final int TWEETS_PER_QUERY = 1000;
    // How many loops required to fullfil the request
    private static int SEARCH_LOOPS;
    // Tweets retrieved successfuly
    private int TOTALTWEETS;
    
    /** * Gets the amount of retrieved tweets *
     * @return Total successfully retrieved tweets */
    public int getTotalTweets(){
        return TOTALTWEETS;
    }
    
    /** * Replace newlines and tabs in text with escaped versions to making printing cleaner *
     * @param text The text of a tweet, sometimes with embedded newlines and tabs
     * @return The text passed in, but with the newlines and tabs replaced */
    public static String cleanText(String text) { 
        text = text.replace("\n", "\\n");
        text = text.replace("\t", "\\t");
        return text;
    }
    /** * Retrieve the "bearer" token from Twitter in order to make application-authenticated calls. *
     * @return The oAuth2 bearer token */
    public static OAuth2Token getOAuth2Token() {
        OAuth2Token token = null;
        ConfigurationBuilder cb;
        cb = new ConfigurationBuilder();
        cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY).setOAuthConsumerSecret(CONSUMER_SECRET);
        
        try {
            token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
        }
        catch (Exception e) {
            System.out.println("Could not get OAuth2 token");
            e.printStackTrace();
            System.exit(0);
        }
        return token;
    }
    
    /** * Get a fully application-authenticated Twitter object useful for making subsequent calls. *
     * @return Twitter4J Twitter object that's ready for API calls */
    public static Twitter getTwitter() {
        OAuth2Token token;
        
        token = getOAuth2Token();
        // Authentication
        ConfigurationBuilder cb = new ConfigurationBuilder();
        //cb.setApplicationOnlyAuthEnabled(true);
        cb.setOAuthConsumerKey(CONSUMER_KEY);
        cb.setOAuthConsumerSecret(CONSUMER_SECRET);
        cb.setOAuthAccessToken(ACCESS_TOKEN);
        cb.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        cb.setOAuth2TokenType(token.getTokenType());
        cb.setOAuth2AccessToken(token.getAccessToken());
        
        return new TwitterFactory(cb.build()).getInstance();
    }
    
    public List<EatenTweets> eat(int REQUEST) throws TwitterException {
        List<EatenTweets> eatenTweets = new ArrayList<EatenTweets>();
        TOTALTWEETS = 0;
        
        // Determining loops to get requested tweets
        int REMAINDER = REQUEST % TWEETS_PER_QUERY;
        SEARCH_LOOPS = REQUEST / TWEETS_PER_QUERY;
        if(REMAINDER > 0){
            SEARCH_LOOPS++;
        }
        
        // Set to start from id 0
        long maxID = -1;
        
        Twitter twitter = getTwitter();
        
        try {
            // Get limits of search calls
            Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus("search");
            RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
            
            // Get 100 tweets each loop
            for (int queryNumber=0;queryNumber < SEARCH_LOOPS; queryNumber++) {
                // Check rate limit
                if (searchTweetsRateLimit.getRemaining() == 0) {
                    System.out.printf("!!! Sleeping for %d seconds due to rate limits\n", searchTweetsRateLimit.getSecondsUntilReset());
                    // Wait until rate limit replenished
                    Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset()+2) * 1000l);
                }
                // Search all tweets in Indonesia
                Query q = new Query("[*]");
                q.setLang("id");
                // Check if last loop gets less than 100 tweets
                if((REMAINDER > 0) && (queryNumber == SEARCH_LOOPS)){
                    q.setCount(REMAINDER);
                }
                // How many tweets, max, to retrieve
                q.setCount(TWEETS_PER_QUERY);
                // Set to get all tweets
                q.resultType(Query.RECENT);
                // Check if it's the first tweet
                if (maxID != -1) {
                    q.setMaxId(maxID - 1);
                }
                // Get tweets
                QueryResult searchResult = twitter.search(q);
                // Check if there's no tweets left
                if (searchResult.getTweets().size() == 0) {
                    break;
                }
                // Loop to insert tweets into the list
                for (Status s: searchResult.getTweets())
                {
                    TOTALTWEETS++;
                    // Enables retrieving multiple blocks of tweets
                    if (maxID == -1 || s.getId() < maxID) {
                        maxID = s.getId();
                    }
                    // Insert the tweet
                    eatenTweets.add(new EatenTweets(s));
                }
                // Get the remaining rate limit
                searchTweetsRateLimit = searchResult.getRateLimitStatus();
            }
        } catch (Exception e) {
            // When all else fails
            System.out.println("Gagal");
            e.printStackTrace();
        }
        for (Status s: twitter.getUserTimeline()){
            // Insert the tweet
            eatenTweets.add(new EatenTweets(s));
        }
        return eatenTweets;
    }

    public List<EatenTweets> eat(int REQUEST, String SEARCHTAG) throws TwitterException {
        List<EatenTweets> eatenTweets = new ArrayList<EatenTweets>();
        TOTALTWEETS = 0;
        
        // Determining loops to get requested tweets
        int REMAINDER = REQUEST % TWEETS_PER_QUERY;
        SEARCH_LOOPS = REQUEST / TWEETS_PER_QUERY;
        if(REMAINDER > 0){
            SEARCH_LOOPS++;
        }
        
        // Set to start from id 0
        long maxID = -1;
        
        Twitter twitter = getTwitter();
        
        try {
            // Get limits of search calls
            Map<String, RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus("search");
            RateLimitStatus searchTweetsRateLimit = rateLimitStatus.get("/search/tweets");
            
            // Get 100 tweets each loop
            for (int queryNumber=0;queryNumber < SEARCH_LOOPS; queryNumber++) {
                // Check rate limit
                if (searchTweetsRateLimit.getRemaining() == 0) {
                    System.out.printf("!!! Sleeping for %d seconds due to rate limits\n", searchTweetsRateLimit.getSecondsUntilReset());
                    // Wait until rate limit replenished
                    Thread.sleep((searchTweetsRateLimit.getSecondsUntilReset()+2) * 1000l);
                }
                // Search all tweets in Indonesia
                Query q = new Query(SEARCHTAG);
                q.setLang("id");
                // Check if last loop gets less than 100 tweets
                if((REMAINDER > 0) && (queryNumber == SEARCH_LOOPS)){
                    q.setCount(REMAINDER);
                }
                // How many tweets, max, to retrieve
                q.setCount(TWEETS_PER_QUERY);
                // Set to get all tweets
                q.resultType(Query.RECENT);
                // Check if it's the first tweet
                if (maxID != -1) {
                    q.setMaxId(maxID - 1);
                }
                // Get tweets
                QueryResult searchResult = twitter.search(q);
                // Check if there's no tweets left
                if (searchResult.getTweets().size() == 0) {
                    break;
                }
                // Loop to insert tweets into the list
                for (Status s: searchResult.getTweets())
                {
                    TOTALTWEETS++;
                    // Enables retrieving multiple blocks of tweets
                    if (maxID == -1 || s.getId() < maxID) {
                        maxID = s.getId();
                    }
                    // Insert the tweet
                    eatenTweets.add(new EatenTweets(s));
                }
                // Get the remaining rate limit
                searchTweetsRateLimit = searchResult.getRateLimitStatus();
            }
        } catch (Exception e) {
            // When all else fails
            System.out.println("Gagal");
            e.printStackTrace();
        }
        for (Status s: twitter.getUserTimeline()){
            // Insert the tweet
            eatenTweets.add(new EatenTweets(s));
        }
        return eatenTweets;
    }
}