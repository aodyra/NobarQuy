import nltk
import json
import re
import pickle
import nltk
from nltk.classify.scikitlearn import SklearnClassifier
from sklearn.svm import LinearSVC

stop_words = "stopword_en_id.txt"

def get_stopwords():
    stop = set()
    with open(stop_words, "r") as fs:
        for word in fs: stop.add(word.strip())
    return stop

def get_punctuations():
    punctuations = {"''","...","``",".."}
    return punctuations

def clean_text(text):
    text = text.replace("\\n"," ")
    text = re.sub(r"http\S+", "URL", text )
    text = re.sub(r"RT @\S+", "", text )
    text = text.replace("\u26bd","#emotbola")
    text = re.sub(r"\\u\S+", "", text )
    return text

filename = 'data_nobar2.json'
nobar_list_text = []
bukan_nobar_list_text = []
with open(filename, "r") as fileopen:
    for line in fileopen:
        json_data = json.loads(line)
        text = clean_text(json_data['text'])
        label = json_data['label']
        if label == 'NOBAR':
            print(text, '\n\n')
            nobar_list_text.append(text)
        else:
            bukan_nobar_list_text.append(text)


remove_words = get_stopwords().union(get_punctuations())
nobar_list_praproses = []
bukan_nobar_list_praproses = []
bags_of_words = set()
for nobar_tweet in nobar_list_text:
    nobar = nltk.word_tokenize(nobar_tweet.lower())
    # nobar = [word for word in nobar if word not in remove_words]
    nobar_list_praproses.append(' '.join(nobar))
    bags_of_words = bags_of_words.union(set(nobar))

for bukan_nobar_tweet in bukan_nobar_list_text:
    bukan_nobar = nltk.word_tokenize(bukan_nobar_tweet.lower())
    # bukan_nobar = [word for word in bukan_nobar if word not in remove_words]
    bukan_nobar_list_praproses.append(' '.join(bukan_nobar))
    bags_of_words = bags_of_words.union(set(bukan_nobar))

# f = open('model/bag_of_words.pickle', 'wb')
# pickle.dump(bags_of_words, f)
# f.close()

def extract_features(tweet):
    tweet_words = tweet.lower().split()
    tweet_words = set(tweet_words)
    features = {}
    for word in bags_of_words:
        features['contains({})'.format(word)] = word in tweet_words
    return features

nobar_list_features = [(extract_features(tweet),'NOBAR') for tweet in nobar_list_text]
bukan_nobar_list_features = [(extract_features(tweet),'BUKAN') for tweet in bukan_nobar_list_text]
train_extracted = nobar_list_features + bukan_nobar_list_features

# num_folds = 10
# subset_size = int(len(train_extracted)/num_folds)
# sum_SV_accuracy = 0
# for i in range(num_folds):
#     testing_data = train_extracted[i*subset_size:][:subset_size]
#     training_data = train_extracted[:i*subset_size] + train_extracted[(i+1)*subset_size:]

#     SVC_classifier = SklearnClassifier(LinearSVC())
#     SVC_classifier.train(training_data)
#     SV_accuracy = nltk.classify.accuracy(SVC_classifier, testing_data)
#     sum_SV_accuracy += SV_accuracy

# sum_SV_accuracy /= num_folds
# print("SV accuracy :", sum_SV_accuracy)

# f = open("model/classifier.pickle", "wb")
# pickle.dump(SVC_classifier, f)
# f.close()
