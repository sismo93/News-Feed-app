package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ArticlePreviewController extends Controller {
    private Article article = null;

    @FXML
    public HBox articleTitleContainer;
    @FXML
    public TextArea articleTitleArea;

    @FXML
    public HBox articlePreviewContentContainer;
    @FXML
    public TextArea articlePreviewContentArea;

    @FXML
    public HBox readArticleContainer;
    @FXML
    public Button readArticle;

    @FXML
    public HBox openLinkContainer;
    @FXML
    public Button openLink;

    @FXML
    public HBox copyLinkContainer;
    @FXML
    public Button copyLink;

    @FXML
    public HBox deleteFromFeedContainer;
    @FXML
    public Button deleteFromFeed;

    @Override
    public void setupView() {
        this.articleTitleArea.setEditable(false);
        this.articlePreviewContentArea.setEditable(false);
        this.articleTitleArea.setText(this.articleService.getArticle().getTitle());
        this.articlePreviewContentArea.setText(this.articleService.getArticle().getArticle());

        this.readArticle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    displayArticle(articleService.getArticle());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.openLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Desktop.getDesktop().browse(new URL(article.getLink()).toURI());
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });

        this.copyLink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Clipboard clipboard = toolkit.getSystemClipboard();
                StringSelection strSel = new StringSelection(article.getLink());
                clipboard.setContents(strSel, null);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Link copied");
                alert.show();

                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(alertEvent -> alert.close());
                delay.play();
            }
        });

        this.deleteFromFeed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(articleService);
                articleService.deleteArticle(articleService.getArticle());
                Router.Instance().changeView(Router.Views.Feed, null);
            }
        });
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    private void displayArticle(Article article) throws IOException {
        System.out.println("display" + article);
        articleService.setArticle(article);
        Router.Instance().changeView(Router.Views.Article, null);
    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

}
