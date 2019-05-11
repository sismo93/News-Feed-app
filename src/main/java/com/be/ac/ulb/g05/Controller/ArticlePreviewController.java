package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import com.be.ac.ulb.g05.Controller.Router.*;
import twitter4j.TwitterException;

import static com.be.ac.ulb.g05.Controller.AddFromMapController.showAlert;


/**
 * Article Preview controller
 * @author @iyamani
 * @codereview @vtombou
 */
public class ArticlePreviewController extends AbstractTwitterController implements Observer {



    /**
     * FXML control buttons & containers
     */
    @FXML
    public HBox articleTitleContainer;
    @FXML
    public Label articleTitleArea;

    @FXML
    public HBox articlePreviewContentContainer;
    @FXML
    public Label articlePreviewContentArea;

    @FXML
    public Button readArticle;
    @FXML
    public Button openLink;
    @FXML
    public Button copyLink;
    @FXML
    public Button deleteFromFeed;

    @FXML
    public Button shareTwitter;

    @FXML
    public StackPane stackPane;


    /**
     * Article object
     */
    private Article article;

    /**
     * Sets up the view. Called the first time UI element is loaded
     */
    @Override
    public void setupView() {
        articleService.addObserver(this);
        displayArticle();
    }

    /**
     * Sets up the preview
     * Initialises the containers for title, content and buttons
     */
    public void setArticle(Article article) {
        this.article = article;
    }




    /**
     * Sets up the articleService
     *
     * @param articleService article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

    @Override
    public void update(Observable o, Object arg) {
        displayArticle();

    }

    public void onReadPressed( ) {
        articleService.selectArticle(article);
        Router.Instance().changeView(Views.Article);
    }

    /**
     * Copies the link to the clipboard
     */
    public void onCopyPressed() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(article.getLink());
        clipboard.setContents(strSel, null);

        showAlert("Link has been copied to clipboard", "information");
    }

    /**
     * Deletes the article from the feed
     */
    public void onDeletePressed( ) {
        articleService.deleteArticle(article);
        twitterService.deleteTweet(article); //in case its a tweet

        Router.Instance().changeView(Views.Feed);
    }

    public void onOpenPressed() {
        try {
            Desktop.getDesktop().browse(new URL(article.getLink()).toURI());
        } catch (IOException | URISyntaxException e) {
            showAlert("An Error has occurred","Error");
        }
    }

    /**
     * Share on Twitter
     */
    public void onSharePressed() {
        try {
            twitterService.postTweet("Je partage cet article via" +
                    " l'application FeedBuzz " + article.getLink());
            showAlert("The post has been tweeted", "Information");
        } catch (IllegalStateException e) { // Handle exception when the user didnt connect to twitter yet
            showAlert("You have to log in first. Come back when you are connected","Information");
            Router.Instance().changeView(Views.TwitterAuth);

        } catch (TwitterException e) {
            showAlert("An error has occurred", "Error");
        }

    }

    /**
     * Displays the article
     *
     */
    private void displayArticle() {
        this.article = articleService.getArticle();
        this.articleTitleArea.setText(article.getTitle());
        this.articlePreviewContentArea.setText(article.getContent());
    }
}

