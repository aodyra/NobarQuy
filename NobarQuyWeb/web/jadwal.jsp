    <%@page import="java.util.ArrayList"%>
    <%@page import="twitter4j.JSONObject"%>
    <%@page import="twitter.crawler.TweetCrawler"%>
    <%@page import="nobarquy.NobarQuy"%>
    <%@page import="java.util.List"%>
    <%@page import="nobarquy.InformationExtraction" %>
    <%@page import="testset.classifier.NobarInformation" %>

    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!doctype html>
    <html class="main-color" lang="en" xmlns:fb="http://ogp.me/ns/fb#">
      <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <title>QuY NoBar!
      </title>
        <link rel="icon" type="image/x-icon" href="../assets/images/logos.png" />
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <link type="text/css" rel="stylesheet" href="//fast.fonts.net/cssapi/af419a60-4e6f-4e2d-9c02-eb754fe7c4eb.css"/>
        <link type="text/css" rel="stylesheet" href="assets/css/stylesheet.css"/>
      </head>
      <body class="">
        <header class="header">
          <div class="container">
            <img class="logo-sm" src="assets/images/logos-w.png">
          </div>
        </header>
        
        <div class="container main-container">
          <div class="page--video">       
            <div class="row">
              <div class="col-xs-12 text-center">
                <%
                    NobarQuy nq = new NobarQuy();
                    TweetCrawler tcrwl = new TweetCrawler();
                    ArrayList<JSONObject> tweets = new ArrayList();
                    ArrayList<JSONObject> nobarTweets = new ArrayList();

                    tweets = tcrwl.getJSON(100, "nobar vs");
                    nq.loadModel();
                    nobarTweets = nq.classifyTweetFunction(tweets);
                %>
                <table class="table">
                  <thead>
                    <tr>
                      <th>Tweet Nobar</th>
                      <th>Match</th>
                      <th>Place</th>
                      <th>Date</th>
                      <th>Time</th>
    <!--                  <th>Tempat</th>
                      <th>Waktu</th>-->
                    </tr>
                  </thead>
                  <tbody>
                    <% 
                        for (JSONObject tweet : nobarTweets) {
                    %>
                    <tr>
                        <td>
                            <%= tweet.get("text").toString() %>                       
                        </td>
                        <td>
                            <%= tweet.get("match").toString() %>                       
                        </td>
                        <td>
                            <%= tweet.get("place").toString() %>                       
                        </td>
                        <td>
                            <%= tweet.get("date").toString() %>                       
                        </td>
                        <td>
                            <%= tweet.get("time").toString() %>                       
                        </td>
                    </tr>
                    <% }
                    %>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>

        <footer class="clearfix">
        </footer>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
      </body>
    </html>

