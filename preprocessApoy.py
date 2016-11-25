import nltk
import csv
import string
from tqdm import *
import re

nltk.download()
unprocessedData = "dataset_nobar2.csv"
preprocessedData = "preprocessed_data_nobar2.csv"

with open(unprocessedData) as csvfile:
	reader = csv.reader(csvfile)
	nobarlist = list(reader)

datanobar = []
dataset = set()

for line in tqdm(nobarlist):
	#line = re.sub(r"\\n", " ", line)
	# line = re.sub(r"http\S+", "URL", line)
	# line = re.sub(r"RT @\S+", "", line)
	# line = re.sub(r"\u26bd", "#emotbola", line)
	# line = re.sub(r"\\u\S+", "", line)
	line = nltk.word_tokenize(line)
	line = ' '.join(line)
	dataset.add(line)

with open(preprocessedData, 'w') as f:
	writer = csv.writer(f)
	for line in tqdm(dataset):
		writer.writerow(line)