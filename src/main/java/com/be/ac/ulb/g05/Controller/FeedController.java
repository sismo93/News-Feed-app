package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Controller of the ArticleService View
 *
 * @author @MnrBn
 * @codereview @borsalinoK
 */
public class FeedController extends Controller implements Observer {

    /**
     * ArticleViewField displays the content on the page
     */
    public VBox articleContainer;




    private void pushToArticleView(ArrayList<Article> articles){

        articleContainer.getChildren().clear();

        ObservableList<String> names = FXCollections.observableArrayList();
        articles.forEach(article -> {
            names.add(article.getImage()+"///"+article.getTitle() + "\n\n" + article.getAuthor());
        });
        ListView<String> listView = new ListView<String>(names);
        listView.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String name,  boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    String[] temp = name.split("///");
                    Image image = null;
                    try {
                        URL url = new URL(temp[0]);
                        image = SwingFXUtils.toFXImage(ImageIO.read(url), null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImage(image);
                    setText(temp[1]);
                    setGraphic(imageView);
                }
            }
        });
        listView.setFixedCellSize(100);

        articleContainer.getChildren().add(listView);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, final String oldvalue, final String newvalue) {
                int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
                Article article = articles.get(selectedArticleIndex);

                try {
                    displayArticlePreview(article);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * @param actionEvent Back to menu
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        RootController.Instance().changeView(RootController.Views.Menu);
    }

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }

    /**
     * Called after scene loading
     * <p>
     * Init GUI
     * -
     * - fetch the content
     * - display the title of each article
     * -
     */
    @Override
    public void setupView() {
        System.out.println("add");
        articleService.addObserver(this);
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }

    @Override
    public void update(Observable observable, Object o) {
        ArrayList<Article> articles = articleService.getArticles();
        pushToArticleView(articles);
    }

    public void displayArticlePreview(Article article) throws IOException {
        articleService.setArticle(article);
        RootController.Instance().changeView(RootController.Views.Preview);
    }
}
