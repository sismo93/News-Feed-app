package com.be.ac.ulb.g05.Model;

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


import java.util.*;
import java.util.List;

/**
 * @author @borsalinok
 * @codereview @mnrbnm
 * TwitterService represent a connexion to twitter
 * Allow us to do several thing such as post a tweet, retweet, search
 */
public class TwitterService extends Observable {

    private static final String CONSUMER_KEY = "c5QH9G5KXl26uwUO83oN3omtD";
    private static final String CONSUMER_SECRET = "mwPhVED1YhsQgdEEnUQdA80aVFmgzUFEv7UnhccTAOH54yMlFd";
    public static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";
    public static final String AUTHORIZED_URL = "https://api.twitter.com/oauth/authorize";
    private String TWITTER_IMAGE = "https://abilitynet.org.uk/sites/abilitynet.org.uk/files/admin/alltwitter-twitter-bird-logo-white-on-blue.png";

    private List<Status> statuses;
    private List<String> tagList;
    private ArrayList<Article> twitterArticleObj;


    private Twitter twitter;
    private boolean authenticated;
    private String tag="";

    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        statuses = new ArrayList<>();
        tagList = new ArrayList<>();
        twitterArticleObj = new ArrayList<>();
        authenticated = false;

    }

    public AccessToken getAccessToken(String pin) throws TwitterException {
        return twitter.getOAuthAccessToken(pin);
    }

    /**
     * @param text
     * @throws TwitterException
     * @throws IllegalStateException
     * allow us to share an article from the feed
     */
    public void postTweet(String text) throws TwitterException, IllegalStateException {
        twitter.updateStatus(text);
    }

    private RequestToken getRequestToken() throws TwitterException {
        return twitter.getOAuthRequestToken();
    }

    public String getAuthUrl() throws TwitterException {
        RequestToken requestToken = getRequestToken();
        return requestToken.getAuthenticationURL();
    }

    public void setAccessToken(AccessToken accessToken) {
        twitter.setOAuthAccessToken(accessToken);
    }


    /**
     * @throws TwitterException
     * get the timeline of the user in order to display it on the Feed
     */
    public void syncTimeline() throws TwitterException {

        statuses.addAll(twitter.getHomeTimeline());
        this.twitterArticleObj.addAll(getStatusAll());
    }

    /**
     * @param query
     * @throws TwitterException
     * search a specific word in twitter and add all tweet with the word
     * on it
     */
    public void searchBy(String query) throws TwitterException {
        QueryResult result;
        result = twitter.search(new Query(query));
        List<Status> tweets = result.getTweets();
        tagList.add(query);
        this.tag = query;
        statuses.addAll(tweets);
        this.twitterArticleObj.addAll(getStatusAll());


    }


    public List<String> getTagList(){
        return tagList;
    }

    /**
     * @param username
     * @throws TwitterException
     * follow username in twitter
     */
    public void searchUser(String username) throws TwitterException {
        twitter.createFriendship("@"+username);
    }


    /**
     * @return an arraylist of all tweet in form of Article
     */
    public ArrayList<Article> getStatusAll() {
        ArrayList<Article> articles = new ArrayList<>();
        Article article;
        String url = "https://twitter.com/";
        for (Status status : statuses) {
            article = new Article();

            article.setDefaultThumbnail(TWITTER_IMAGE);
            article.setLink(url  +status.getUser().getScreenName()
                    + "/status/" + status.getId());
            article.setPubDate(status.getCreatedAt().toString());
            article.setSource(status.getUser().getName());
            article.setContent(status.getText());
            article.setContent(status.getText());
            article.setTitle("twitter feed | Tag : "+ this.tag);

            // Tag for twitter
            handleTwitterTag(article,"Twitter");
            handleTwitterTag(article,this.tag); // in case he added to tweet via the research
            handleTwitterTag(article,"All");
            articles.add(article);
        }

        return articles;
    }

    private void handleTwitterTag(Article article,String tag){
        if (!article.getTags().contains(tag)){
            article.addTag(tag);
        }
    }

    /**
     * @return true if he's log
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }


    /**
     * @param article
     * delete a tweet from the list
     */
    public void deleteTweet(Article article) {
        twitterArticleObj.remove(article);
    }


    /**
     * @param content
     * @throws TwitterException
     * retweet a tweet
     */
    public void rtTweet(String content) throws TwitterException {
        Status status = findStatus(content);
        twitter.retweetStatus(status.getId());
    }


    /**
     * @param content
     * @return the right tweet
     * search a tweet matching the content and return him
     */
    public Status findStatus(String content){
        Status requestedStatus = null;
        for (Status status : statuses){
            if (status.getText().equals(content)){
                requestedStatus = status;}
        }
        return requestedStatus;
    }

    public ArrayList<Article> getTwitterArticleObj(){
        return this.twitterArticleObj;
    }
}
