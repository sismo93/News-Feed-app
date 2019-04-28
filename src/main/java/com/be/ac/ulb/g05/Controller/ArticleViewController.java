package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Controller.Router.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

/**
 * Article View controller
 *
 * @author @iyamani
 * @codereview @vtombou
 */
public class ArticleViewController extends AbstractController implements Observer {

    /**
     * FXML control buttons & containers
     */
    @FXML
    public HBox titleContainer;

    @FXML
    public Label articleTitle;

    @FXML
    public HBox imageContainer;

    @FXML
    public TextArea articleContent;

    @FXML
    public HBox authorContainer;
    @FXML
    public Label articleAuthor;
    public Label mediaLabel;
    public BorderPane mediaView;

    /**
     * Sets up the view
     */
    @Override
    public void setupView() {
        articleService.addObserver(this);
        pushArticleToView();
    }


    /**
     * Sets up the view
     */
    private void pushArticleToView() {
        Platform.runLater(() -> {
            Article article = articleService.getArticle();
            this.articleContent.setEditable(false);
            // Setting the containers with their appropriate texts
            this.articleTitle.setText(article.getTitle());
            this.articleContent.setText(article.getContent());
            this.articleAuthor.setText(article.getSource());

            // Puts image into the view
            try {

                if (!article.getVideo().isEmpty()) {

                    String url = articleService.getArticle().getVideo();
                    WebView webView = new WebView();
                    WebEngine webEngine = webView.getEngine();
                    webEngine.load(url);

                    mediaView.setCenter(webView);


                } else {

                    ImageView imageView = new ImageView();
                    mediaView.setCenter(imageView);

                    saveImage(article.getImage(), "file:temp-feedbuzz.jpg", imageView);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * @param imageUrl        the URL of the image
     * @param destinationFile the destination file
     * @param imageView       the image container in the window
     * @throws IOException thrown when problems displaying the image
     *                     <p>
     *                     Saves the image found on the article. If there is no images, nothing will happen
     */
    public static void saveImage(String imageUrl, String destinationFile, ImageView imageView) throws IOException {
        Image image = null; // usefull when there is no image to put on the article
        if (!imageUrl.isEmpty()) {
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();

            image = new Image(destinationFile);
        }
        imageView.setImage(image);


    }


    public void openMediaView(MouseEvent mouseEvent) {
        Router.Instance().changeView(Views.Media);
    }

    @Override
    public void update(Observable o, Object arg) {
        pushArticleToView();
    }
}
