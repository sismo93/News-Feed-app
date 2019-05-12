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

    /**
     * Authentication Information
     */
    public static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate";
    public static final String AUTHORIZED_URL = "https://api.twitter.com/oauth/authorize";
    /**
     * Twitter Api App information
     */
    private static final String CONSUMER_KEY = "c5QH9G5KXl26uwUO83oN3omtD";
    private static final String CONSUMER_SECRET = "mwPhVED1YhsQgdEEnUQdA80aVFmgzUFEv7UnhccTAOH54yMlFd";

    /**
     * Statuses
     */
    private List<Status> statuses;

    /**
     * List with all Tag
     */
    private List<String> tagList;
    /**
     * Statutes in Article object
     */
    private ArrayList<Article> twitterArticleObj;

    /**
     * Twitter
     */
    private Twitter twitter;

    /**
     * Authenticated
     */
    private boolean authenticated;

    /**
     * Tag
     */
    private String tag="";

    /**
     * Constructor
     */
    public TwitterService() {
        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
        statuses = new ArrayList<>();
        tagList = new ArrayList<>();
        twitterArticleObj = new ArrayList<>();
        authenticated = false;
    }

    /**
     * Access token getter
     * @param pin pin code
     * @return access token object
     * @throws TwitterException if pin wrong
     */
    public AccessToken getAccessToken(String pin) throws TwitterException {
        return twitter.getOAuthAccessToken(pin);
    }

    /**
     * @param text tweet content
     * @throws TwitterException twitter exception
     * @throws IllegalStateException if not authorised
     * allow us to share an article from the feed
     */
    public void postTweet(String text) throws TwitterException, IllegalStateException {
        twitter.updateStatus(text);
    }

    /**
     * Request token getter
     * @return request token object
     * @throws TwitterException twitter exception
     */
    private RequestToken getRequestToken() throws TwitterException {
        return twitter.getOAuthRequestToken();
    }

    /**
     * Authentication url getter
     * @return authentication url
     * @throws TwitterException twitter exception
     */
    public String getAuthUrl() throws TwitterException {
        RequestToken requestToken = getRequestToken();
        return requestToken.getAuthenticationURL();
    }

    /**
     * Access token setter
     * @param accessToken access token object
     */
    public void setAccessToken(AccessToken accessToken) {
        twitter.setOAuthAccessToken(accessToken);
    }


    /**
     * @throws TwitterException
     * get the timeline of the user in order to display it on the Feed
     */
    public void syncTimeline() throws TwitterException {
        statuses.clear();
        statuses.addAll(twitter.getHomeTimeline());
        this.tag = "TimeLine";
        this.twitterArticleObj.addAll(getStatusAll());
    }

    /**
     * @param query search content
     * @throws TwitterException
     * search a specific word in twitter and add all tweet with the word
     * on it
     */
    public void searchBy(String query) throws TwitterException {
        QueryResult result;
        result = twitter.search(new Query(query));
        statuses.clear();
        List<Status> tweets = result.getTweets();
        tagList.add(query);
        this.tag = query;

        statuses.addAll(tweets);
        this.twitterArticleObj.addAll(getStatusAll());
    }


    /**
     * Gets a list of tags
     * @return list of tags
     */
    public List<String> getTagList(){
        return tagList;
    }

    /**
     * @param username twitter username
     * @throws TwitterException
     * follow username in twitter + add his last tweet to the view
     */
    public void searchUser(String username) throws TwitterException {
        twitter.createFriendship("@"+username);
        this.tag = "@"+username;
        statuses.clear();
        statuses.addAll(twitter.getUserTimeline(username));
        this.twitterArticleObj.addAll(getStatusAll());
    }

    /**
     * @return true if he's log
     */
    public boolean isAuthenticated() {
        return authenticated;
    }

    /**
     * Sets authenticated
     * @param authenticated boolean
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }


    /**
     * @param article tweet
     * delete a tweet from the list
     */
    public void deleteTweet(Article article) {
        twitterArticleObj.remove(article);
    }

    /**
     * Twitter articles
     * @return list of twitter in articles format
     */
    public ArrayList<Article> getTwitterArticleObj(){
        return this.twitterArticleObj;
    }

    /**
     * clear list in case he disconnect
     */
    public void reset() {
        statuses.clear();
        twitterArticleObj.clear();
        tagList.clear();
        this.tag = "";

    }
    /**
     * @return an arraylist of all tweet in form of Article
     */
    private ArrayList<Article> getStatusAll() {
        ArrayList<Article> articles = new ArrayList<>();
        Article article;
        String url = "https://twitter.com/";
        for (Status status : statuses) {
            article = new Article();

            String TWITTER_IMAGE = "https://abilitynet.org.uk/sites/abilitynet.org.uk/files/admin/alltwitter-twitter-bird-logo-white-on-blue.png";
            article.setDefaultThumbnail(TWITTER_IMAGE);
            article.setLink(url  +status.getUser().getScreenName()
                    + "/status/" + status.getId());
            article.setPubDate(status.getCreatedAt().toString());
            article.setSource(status.getUser().getName());
            article.setContent(status.getText());
            article.setContent(status.getText());


            // Tag for twitter
            handleTwitterTag(article);

            articles.add(article);
        }

        return articles;
    }

    /**
     * @param article
     * add the tag to the list + add it to the title
     */
    private void handleTwitterTag(Article article){
        article.addTag("All");
        article.addTag("Twitter");
        article.addTag(this.tag);
        StringBuilder allTag = new StringBuilder();
        for (String tag:article.getTags()){
            allTag.append(tag);
            allTag.append(" ");
        }
        article.setTitle("twitter feed | Tag : "+ allTag);
    }


}
