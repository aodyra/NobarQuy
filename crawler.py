import tweepy
import json

consumer_key = "mASc5bPjmxUd6Pwwnz7M7rPz0"
consumer_secret = "TF2qhPSMefXLdWGV1AWHlyeg25lP9CEtRoxwAhg22dJGf8xNAu"
access_token = "57809695-0pCM0qEkBiL4prFfd5Epj0Y1tYOxvqrXYPdC0OowO"
access_token_secret = "uSNhB4JAtgZ117McL4cJ1QiGzVcFhts9AEAnLRFte29o0"
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)

max_tweets = 1000
query="nobar"
search_tweets = []
last_id = -1
while len(search_tweets) < max_tweets:
    count = max_tweets - len(search_tweets)
    try:
        new_tweets = api.search(q=query, count=count, max_id=str(last_id - 1))
        if not new_tweets:
            break
        search_tweets.extend(new_tweets)
    except tweepy.TweepError as e:
        break

with open('data_nobar.txt', 'w') as outfile:
    for tweet in search_tweets:
        dict_temp = json.loads(json.dumps(tweet._json))
        dict_tweet = {}
        dict_tweet['text'] = dict_temp['text']
        dict_tweet['created_at'] = dict_temp['created_at']
        dict_tweet['geo'] = dict_temp['geo']
        dict_tweet['coordinates'] = dict_temp['coordinates']
        outfile.write(json.dumps(dict_tweet))
