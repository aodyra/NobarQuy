/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import testset.classifier.NobarClassifier;
import testset.classifier.TweetPreprocessor;
import training.classifier.MapUtil;
import training.classifier.StringToWordProcessor;
import training.classifier.TrainingClassifier;

/**
 *
 * @author ryanyonata
 */
public class NobarQuy {
    protected NobarClassifier nobarClassifier;
    
    public NobarQuy() throws Exception {
        nobarClassifier = new NobarClassifier();
    }
    
    public void buildModel() {
        TrainingClassifier tc = new TrainingClassifier();
        String arff = tc.generateARFF();
    }
    
    public void loadModel() throws Exception {
        nobarClassifier.loadModel();
    }
    
    public void classifyTweetFromFile(String path) throws Exception {
        TweetPreprocessor tw = new TweetPreprocessor();
        List<String> nobarList = new ArrayList<>();
        nobarList = tw.convertTxtToStringList(path);

        for (String line : nobarList) {
            String result;
            result = nobarClassifier.classifyUnseenData(line);
            System.out.println(result);
        }
    }
}
