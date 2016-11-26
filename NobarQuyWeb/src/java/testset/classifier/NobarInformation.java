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
    private String time;
    private String clubA;
    private String clubB;
    protected Instance instance;
    
    public NobarInformation(){}
    
    public NobarInformation(String match, String place, String date, String time) {
        this.match = match;
        this.place = place;
        this.date = date;
        this.time = time;
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
    
    public String getTime() {
        return time;
    }
    
    //Setter
    public void setMatch(String new_match) {
        this.match = new_match;
    }
    
    public void setPlace(String new_place) {
        this.place = new_place;
    }
    
    public void setDate(String new_date) {
        this.date = new_date;
    }
    
    public void setTime(String new_time) {
        this.time = new_time;
    }
}
