import tweepy
import json
import xlsxwriter

consumer_key = "mASc5bPjmxUd6Pwwnz7M7rPz0"
consumer_secret = "TF2qhPSMefXLdWGV1AWHlyeg25lP9CEtRoxwAhg22dJGf8xNAu"
access_token = "57809695-0pCM0qEkBiL4prFfd5Epj0Y1tYOxvqrXYPdC0OowO"
access_token_secret = "uSNhB4JAtgZ117McL4cJ1QiGzVcFhts9AEAnLRFte29o0"
auth = tweepy.OAuthHandler(consumer_key, consumer_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)
user_id = 'Lokasi_Nobar'
max_tweets = 1000
query="#infoNobar AND -#repost AND -filter:retweets AND -filter:replies"
search_tweets = []
last_id = -1
while len(search_tweets) < max_tweets:
    count = max_tweets - len(search_tweets)
    try:
        new_tweets = api.search(q=query, count=count, max_id=str(last_id - 1))
        if not new_tweets:
            break
        search_tweets.extend(new_tweets)
        last_id = new_tweets[-1].id
    except tweepy.TweepError as e:
        break
search_tweets2 = []
last_id = -1
while len(search_tweets2) < max_tweets:
    count = max_tweets - len(search_tweets2)
    try:
        if count == max_tweets:
            new_tweets = api.user_timeline(screen_name=user_id, count=count, include_rts=True)
        else:
            new_tweets = api.user_timeline(screen_name=user_id, count=count, include_rts=True, max_id=str(last_id-1))
        if not new_tweets:
            break
        search_tweets2.extend(new_tweets)
        last_id = new_tweets[-1].id
    except tweepy.TweepError as e:
        break

##### Simple Text #####
with open('data_nobar.txt', 'w') as outfile:
    for tweet in search_tweets:
        dict_temp = json.loads(json.dumps(tweet._json))
        dict_tweet = {}
        dict_tweet['text'] = dict_temp['text']
        dict_tweet['created_at'] = dict_temp['created_at']
        # dict_tweet['geo'] = dict_temp['geo']
        # dict_tweet['coordinates'] = dict_temp['coordinates']
        outfile.write(json.dumps(dict_tweet))
        outfile.write("\n")
    for tweet in search_tweets2:
        dict_temp = json.loads(json.dumps(tweet._json))
        dict_tweet = {}
        dict_tweet['text'] = dict_temp['text']
        dict_tweet['created_at'] = dict_temp['created_at']
        # dict_tweet['geo'] = dict_temp['geo']
        # dict_tweet['coordinates'] = dict_temp['coordinates']
        outfile.write(json.dumps(dict_tweet))
        outfile.write("\n")

##### Excel #####
# workbook = xlsxwriter.Workbook('data_nobar.xlsx')
# worksheet = workbook.add_worksheet()

# row = 0
# col = 0

# worksheet.write(row, col, 'Label')
# worksheet.write(row, col+1, 'Created at')
# worksheet.write(row, col+2, 'Text')

# row += 1

# for tweet in search_tweets:
# 	worksheet.write(row, col+1, tweet.created_at)
# 	worksheet.write(row, col+2, tweet.text)
# 	row+=1
# for tweet in search_tweets2:
#     worksheet.write(row, col+1, tweet.created_at)
#     worksheet.write(row, col+2, tweet.text)
#     row+=1

# workbook.close()
