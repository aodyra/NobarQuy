import re

with open('bukan_nobar.txt') as infile:
	with open('preproses_bukan.txt','w') as outfile:
		for line in infile:
			line = line.replace("\\n"," ")
			line = re.sub(r"http\S+", "URL", line)
			line = re.sub(r"RT @\S+", "", line)
			line = line.replace("\u26bd","#emotbola")
			line = re.sub(r"\\u\S+", "", line)
			outfile.write(line)


