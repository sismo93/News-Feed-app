package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import com.be.ac.ulb.g05.Controller.Router.*;
import java.util.ResourceBundle;

/**
 * Article Preview controller
 * @author @iyamani
 * @codereview @vtombou
 */
public class ArticlePreviewController extends Controller {
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
    public HBox buttonsContainer;

    @FXML
    public Button readArticle;

    @FXML
    public Button openLink;

    @FXML
    public Button copyLink;

    @FXML
    public Button deleteFromFeed;

    /**
     * Sets up the preview
     * Initialises the containers for title, content and buttons
     */
    @Override
    public void setupView() {
        Platform.runLater(() -> {
            setArticle(articleService.getArticle());
            this.articleTitleArea.setText(this.articleService.getArticle().getTitle());
            this.articlePreviewContentArea.setText(this.articleService.getArticle().getContent());

            // Displays full article
            this.readArticle.setOnAction(event -> {
                try {
                    displayArticle(articleService.getArticle());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            // Opens the link in a new browser
            this.openLink.setOnAction(event -> {
                try {
                    Desktop.getDesktop().browse(new URL(article.getLink()).toURI());
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            });

            // Copies the link to the clipboard
            this.copyLink.setOnAction(event -> {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection strSel = new StringSelection(article.getLink());
                clipboard.setContents(strSel, null);

                displayQuickAlert();
            });

        // Deletes the article from the feed
        this.deleteFromFeed.setOnAction(event -> {
            articleService.deleteArticle(articleService.getArticle());
            Router.Instance().changeView(Views.Feed);
        });
    }

    /**
     * Displays an information that disappears within 2 seconds
     */
    private void displayQuickAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Link copied");
        alert.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(alertEvent -> alert.close());
        delay.play();
    }

    /**
     * Initialises the article
     * @param article the article
     */
    public void setArticle(Article article) {
        this.article = article;
    }

    /**
     * Displays the article
     * @param article the article
     * @throws IOException thrown when article can't be opened
     */
    private void displayArticle(Article article) throws IOException {
        articleService.setArticle(article);
        Router.Instance().changeView(Views.Article);
    }

    /**
     * Sets up the articleService
     * @param articleService article service
     */
    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

}
