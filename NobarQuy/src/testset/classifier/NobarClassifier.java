/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testset.classifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import weka.access.WekaAccessor;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

/**
 *
 * @author ryanyonata
 */
public class NobarClassifier {
    protected Instances trainingInstances;
    protected RandomForest classifier;
    protected Evaluation eval;
    protected ArrayList<ArrayList<String>> data = new ArrayList<>();
    protected ArrayList<String> features;
    
    public NobarClassifier() throws Exception {
        //Read ARFF File
        
        WekaAccessor accessor = new WekaAccessor();
        Instances trainset;
        trainset = accessor.readARFF("dataset2.arff");
        trainingInstances = trainset;
    }
    
//    void buildRandomForestClassifier() throws Exception {
//        classifier = new RandomForest();
//        classifier.setNumTrees(50);
//        classifier.buildClassifier(trainingInstances);
//        WekaAccessor wk = new WekaAccessor();
//        wk.saveModel(classifier,"nobar.model");
//    }
    
    public String classifyUnseenData(String tweet) throws Exception {
        TweetPreprocessor preproses = new TweetPreprocessor();
        tweet = preproses.preprocessTweet(tweet);
        
        Instance newInstance = new DenseInstance(trainingInstances.numAttributes());
        newInstance.setDataset(trainingInstances);
        //System.out.println("attr: " + trainingInstances.numAttributes());
        for(int i=0; i<trainingInstances.numAttributes(); i++){
            if(tweet.contains(trainingInstances.attribute(i).name())) {
                newInstance.setValue(trainingInstances.attribute(i), 1);
            } else {
                newInstance.setValue(trainingInstances.attribute(i), 0);
            }
        }
        //System.out.println("NewInstance: " + newInstance);
        //System.out.println("NumFeatures: " + classifier.getNumFeatures());
        
        double clsLabel = classifier.classifyInstance(newInstance);
        newInstance.setClassValue(clsLabel);
        
        String result = trainingInstances.classAttribute().value((int) clsLabel);
        
        return result;
    }
    
    public void saveModel(String filename) throws Exception {
        SerializationHelper.write(filename, classifier);
        
        // Save features
        /*StringBuilder featuresBuilder = new StringBuilder();
        for (String token : features) {
            featuresBuilder.append(token).append("\n");
        }
        writeFile("model/Nobar.features", featuresBuilder.toString());*/
    }
    
    public void loadModel() throws Exception {
        WekaAccessor wk = new WekaAccessor();
        classifier = (RandomForest) wk.loadModel("model/nobar.model");
        BufferedReader reader = new BufferedReader(new FileReader("dataset2.arff"));
        trainingInstances = new Instances(reader);
        trainingInstances.setClassIndex(trainingInstances.numAttributes() - 1);
        reader.close();
    }
    
    public void fullTraining() throws Exception {
        eval = new Evaluation(trainingInstances);
        eval.evaluateModel(classifier, trainingInstances);   
    }    
    
    public void crossValidate(int folds) throws Exception {
        eval = new Evaluation(trainingInstances);
        eval.crossValidateModel(classifier, trainingInstances, folds, new Random(1));     
    }
    
    public void writeFile(String filename, String content) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
            }

            java.io.FileWriter fw = new java.io.FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> readFile(String filename) {    
        BufferedReader br = null;
        String line;
        ArrayList<String> content = new ArrayList();

        try {
            br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                content.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Currently no model is saved. Build first.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return content;
    }
}
