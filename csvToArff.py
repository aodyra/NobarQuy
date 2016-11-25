import csv
import pdb


#Variable Intialization
filename = "data/processedSpamdata.csv"
ITEMS = set()
arffFilename = filename.split(".")[0] + ".arff"

myfile = csv.reader(open(filename,'rb'))


#Get List of all the unique items from the Input
for line in myfile:
    #[ITEMS.add(item.strip()) for item in line]
    line2='. '.join(line)
    [ITEMS.add(word) for word in line2.split()[:-1]]


myARFF = open(arffFilename,'w+')
myARFF.write('@relation Weka\n\n\n')

ITEMS = list(ITEMS) #So that items will iterate in order
for item in ITEMS: myARFF.write(str("@attribute "+item+" {false,true}\n"))

myARFF.write('\n\n@data\n\n')

#Iterate over the data file
myfile = csv.reader(open(filename,'rb'))

for line in myfile:
    tmp = []
    newLine = "{ "
	#get the index first and sort them and then loop on sorted value to add true
    line2='. '.join(line)
    for word in line2.split():
        tmp.append(ITEMS.index(word.strip()))
    for i in sorted(list(set(tmp))):
        newLine += str(i) +" "+"true"+","
    writeLine = newLine[:-1]
    writeLine += "}\n"
    print writeLine
    myARFF.write(writeLine)

myARFF.close()





    


