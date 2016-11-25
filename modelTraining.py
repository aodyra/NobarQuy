import nltk
import json
import re

filename = 'data_nobar2.json'
with open(filename, r) as fileopen:
    for line in fileopen:
        text = json.loads(line)['text']
        text = 
        text = text .replace("\\n"," ")
        text = re.sub(r"http\S+", "URL", text )
        text = re.sub(r"RT @\S+", "", text )
        text = text .replace("\u26bd","#emotbola")
        text = re.sub(r"\\u\S+", "", text )


