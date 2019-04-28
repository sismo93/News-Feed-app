package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

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

import static com.be.ac.ulb.g05.Controller.AddController.showAlert;


/**
 * Article Preview controller
 * @author @iyamani
 * @codereview @vtombou
 */
public class ArticlePreviewController extends AbstractTwitterController implements Observer {

    /**
     * Article object
     */
    private Article article;

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
     * Displays the article
     *
     */
    private void displayArticle() {
        this.article = articleService.getArticle();
        this.articleTitleArea.setText(article.getTitle());
        this.articlePreviewContentArea.setText(article.getContent());
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

    public void onReadPressed(ActionEvent actionEvent) {
        articleService.selectArticle(article);
        Router.Instance().changeView(Views.Article);
    }

    /**
     * @param actionEvent
     * Copies the link to the clipboard
     */
    public void onCopyPressed(ActionEvent actionEvent) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection strSel = new StringSelection(article.getLink());
        clipboard.setContents(strSel, null);

        showAlert("Link has been copied to clipboard", "information");
    }

    /**
     * @param actionEvent
     * Deletes the article from the feed
     */
    public void onDeletePressed(ActionEvent actionEvent) {
        articleService.deleteArticle(article);
        twitterService.deleteTweet(article); //in case its a tweet

        Router.Instance().changeView(Views.Feed);
    }

    public void onOpenPressed(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URL(article.getLink()).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param actionEvent
     * Share on Twitter
     */
    public void onSharePressed(ActionEvent actionEvent) {
        try {
            if (article.getTitle().equals("twitter feed")) { // in case he want to RT a tweet
                twitterService.rtTweet(article.getContent());
            } else { // mean that he want to share an article and not rt one
                twitterService.postTweet("Je partage cet article via" +
                        " l'application FeedBuzz " + article.getLink());
            }
        } catch (IllegalStateException e) { // Handle exception when the user didnt connect to twitter yet
            Router.Instance().changeView(Views.TwitterAuth);

        } catch (TwitterException e) {
            //TODO HANDLE
        }
        showAlert("The post has been tweeted", "information");
    }
}

