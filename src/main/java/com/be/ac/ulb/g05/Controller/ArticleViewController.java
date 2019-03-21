package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class ArticleViewController extends Controller {

    private Article article = null;

    @FXML
    public HBox titleContainer;
    @FXML
    public TextArea articleTitle;

    @FXML
    public HBox imageContainer;
    @FXML
    public ImageView articleImage;

    @FXML
    public HBox articleContentContainer;
    @FXML
    public TextArea articleContent;

    @FXML
    public HBox authorContainer;
    @FXML
    public TextArea articleAuthor;

    @FXML private void initialize() {
        Platform.runLater(() -> {
            this.articleTitle.setEditable(false);
            this.articleContent.setEditable(false);
            this.articleAuthor.setEditable(false);

            this.articleTitle.setText(this.article.getTitle());
            this.articleContent.setText(this.article.getArticle());
            this.articleAuthor.setText(this.article.getAuthor());

            try {

                System.out.println(this.article.getImage());
                saveImage(this.article.getImage(), "file:temp-feedbuzz.jpg", this.articleImage);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public static void saveImage(String imageUrl, String destinationFile, ImageView imageView) throws IOException {
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

        Image image = new Image(destinationFile);
        imageView.setImage(image);
    }
}
