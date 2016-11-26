/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nobarquy;

import java.io.IOException;

/**
 *
 * @author ryanyonata
 */
public class Main {
    
    public static void main (String[] args) throws IOException, Exception {
        NobarQuy nq = new NobarQuy();
        String filename = "data\\nobar.txt";
        
        
        nq.classifyTweetFromFile(filename);
    }
    
}
