package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.sound.midi.Soundbank;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.SQLOutput;
import java.util.Observable;
import java.util.Observer;

/**
 * Article View controller
 * @author @iyamani
 * @codereview @vtombou
 */
public class ArticleViewController extends Controller implements Observer {

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
    public ImageView articleImage;

    @FXML
    public TextArea articleContent;

    @FXML
    public HBox authorContainer;
    @FXML
    public Label articleAuthor;

    @Override
    public void setupView() {
        articleService.addObserver(this);

        pushArticleToView();
    }


    /**
     * Sets up the view
     */
    public void pushArticleToView() {
        Platform.runLater(() -> {
            Article article = articleService.getArticle();
            this.articleContent.setEditable(false);
            // Setting the containers with their appropriate texts
            this.articleTitle.setText(article.getTitle());
            this.articleContent.setText(article.getContent());
            this.articleAuthor.setText(article.getSource());

            // Puts image into the view
            try {

                saveImage(article.getImage(), "file:temp-feedbuzz.jpg", this.articleImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    /**
     * @param imageUrl the URL of the image
     * @param destinationFile the destination file
     * @param imageView the image container in the window
     * @throws IOException thrown when problems displaying the image
     *
     * Saves the image found on the article. If there is no images, nothing will happen
     */
    public static void saveImage(String imageUrl, String destinationFile, ImageView imageView) throws IOException {
        System.out.println(imageUrl.isEmpty());
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

    @Override
    public void update(Observable o, Object arg) {
        pushArticleToView();

    }
}
