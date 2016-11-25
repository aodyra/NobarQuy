/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Twittertest;

import twitter4j.*;
/**
 *
 * @author Toshiba
 */
public class EatenTweets {
    private String name;
    private String id;
    private String url; // url of the tweet
    private String text;
    private String date;
    private String account_image_url; // can be null
    private String account_url; // can be null
    private String location; // can be null
    
    public EatenTweets(Status s){
        name = s.getUser().getScreenName();
        id = ""+s.getId();
        url = "https://twitter.com/" + name + "/status/" + id;
        text = s.getText();
        date = s.getCreatedAt().toString();
        account_image_url = s.getUser().getProfileImageURL();
        account_url = s.getUser().getURL();
        location = s.getUser().getLocation();
    }
    
    public void print(){
        System.out.printf("@%-20s: %s\n",name,text);
        System.out.printf("Tweeted at %s from %s\n%s\n",date,location,url);
        System.out.printf("Account image location\n%s\nAccount url\n%s\n\n",account_image_url,account_url);
    }
    
    public void setName(String val){
        name = val;
    }
    
    public void setId(String val){
        id = val;
    }
    
    public void resetUrl(){
        url = "https://twitter.com/" + name + "/status/" + id;
    }
    
    public void setText(String val){
        text = val;
    }
    
    public void setDate(String val){
        date = val;
    }
    
    public void setAccImgUrl(String val){
        account_image_url = val;
    }
    
    public void setAccUrl(String val){
        account_url = val;
    }
    
    public void setLocation(String val){
        location = val;
    }
    
    public String getName(){
        return name;
    }
    
    public String getId(){
        return id;
    }
    
    public String getUrl(){
        return url;
    }
    
    public String getText(){
        return text;
    }
    
    public String getDate(){
        return date;
    }
    public String getAccImgUrl(){
        return account_image_url;
    }
    
    public String getAccUrl(){
        return account_url;
    }
    
    public String getLocation(){
        return location;
    }
}
