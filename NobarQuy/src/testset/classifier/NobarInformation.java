/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testset.classifier;

import weka.core.Instance;

/**
 *
 * @author ryanyonata
 */
public class NobarInformation {
    
    private String match;
    private String place;
    private String date;
    private String clubA;
    private String clubB;
    protected Instance instance;
    
    public NobarInformation(){}
    
    public NobarInformation(String match, String place, String date) {
        this.match = match;
        this.place = place;
        this.date = date;
    }
    
    //Getter
    public String getMatch() {
        return match;
    }
    
    public String getPlace() {
        return place;
    }
    
    public String getDate() {
        return date;
    }
    
    //Setter
    public void setMatch(String new_match) {
        this.match = new_match;
    }
    
    public void setPlace(String new_place) {
        this.match = new_place;
    }
    
    public void setDate(String new_date) {
        this.match = new_date;
    }
}
