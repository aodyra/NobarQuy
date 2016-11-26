/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import testset.classifier.TweetPreprocessor;
import weka.access.WekaAccessor;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.RandomForest;
import weka.core.Instances;

/**
 *
 * @author ryanyonata
 */
public class Main {
    
    public static void main (String[] args) throws IOException, Exception {
        NobarQuy nq = new NobarQuy();
        String filename = "nobar.txt";
        String path = "model/nobar.model";
        
//        WekaAccessor accessor = new WekaAccessor();
//        Instances trainset;
//        trainset = accessor.readARFF(filename);
//        RandomForest classifier = new RandomForest();
//        classifier.setNumTrees(100);
//        classifier.buildClassifier(trainset);
//        accessor.saveModel(classifier, path);
//        System.out.println(classifier);
//        System.out.println(accessor.tenFoldCrossValidation(trainset, classifier).toSummaryString());
//        Evaluation eval = new Evaluation(trainset);
//        eval.evaluateModel(classifier, trainset);
        
//        TweetPreprocessor tw = new TweetPreprocessor();
//        List<String> nobarList = new ArrayList<>();
//        nobarList = tw.convertTxtToStringList(filename);
//        System.out.println(nobarList.size());
        nq.loadModel();
        nq.classifyTweetFromFile(filename);
    }
    
}
