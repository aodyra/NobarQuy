/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.classifier;

import IndonesianNLP.IndonesianStemmer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Ahmad
 */
public class SmsClassifier {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // File file = File.Input();
        File fSpam = new File("spam.txt");
        File fNotSpam = new File("notspam.txt");
        SpamPreprocess spamPreprocess = new SpamPreprocess();
        
        List<String> spamDataset = spamPreprocess.spamStemming(fSpam);
        List<String> notSpamDataset = spamPreprocess.spamStemming(fNotSpam);
        
        StringToWordProcessor st = new StringToWordProcessor();
        Map<String, Integer> spamWords = st.stringAttribute(spamDataset);
        Map<String, Integer> totalDataset = MapUtil.sortByValue(st.stringAttribute(notSpamDataset));
        
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream("dataset.arff", true));
            
            pw.println("@relation spamdataset");
            pw.println();
            
            Iterator it = totalDataset.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                pw.printf("@attribute\t%s\tNUMERIC\n", pair.getKey());
            }
            
            pw.println("@attribute\tclass\t{0,1}");
            
            pw.println("\n@data");
            for (String line : spamDataset) {
                int wordCount[] = new int[totalDataset.size()];
                String words[] = line.split(" ");
                for (String word : words) {
                    if (totalDataset.containsKey(word)) {
                        wordCount[totalDataset.get(word)]++;
                    }
                }
                for(int val : wordCount) {
                    pw.print(val + ",");
                }
                pw.println("0");
            }

            for (String line : notSpamDataset) {
                int wordCount[] = new int[totalDataset.size()];
                String words[] = line.split(" ");
                for (String word : words) {
                    if (totalDataset.containsKey(word)) {
                        wordCount[totalDataset.get(word)]++;
                    }
                }
                for(int val : wordCount) {
                    pw.print(val + ",");
                }
                pw.println("1");
            }
            
            pw.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
            Logger.getLogger(SmsClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }

        // preprocessing(file);
        // IDTree idt = buildIDTree(file);
        // idt.isSpam(String);
    }
    
}
