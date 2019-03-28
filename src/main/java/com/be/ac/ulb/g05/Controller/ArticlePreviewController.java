package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import com.be.ac.ulb.g05.Controller.Router.*;

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
    public Button readArticle;
    @FXML
    public Button openLink;
    @FXML
    public Button copyLink;
    @FXML
    public Button deleteFromFeed;

    @FXML
    public StackPane stackPane;

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
                displayArticle(articleService.getArticle());
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
        });
    }


    /**
     * Displays an information dialog that disappears within 2 seconds
     */
    private void displayQuickAlert() {
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text("Information"));
        content.setBody(new Text("Link has been copied to clipboard"));

        JFXDialog dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);
        JFXButton button = new JFXButton("Ok");
        button.setOnAction(event -> dialog.close());

        content.setActions(button);
        dialog.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(alertEvent -> dialog.close());
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
     */
    private void displayArticle(Article article) {
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

