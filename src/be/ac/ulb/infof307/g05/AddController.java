package be.ac.ulb.infof307.g05;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static be.ac.ulb.infof307.g05.URLReader.Article;
import static be.ac.ulb.infof307.g05.URLReader.Homepage;


/**
 * Controller of the Add View
 * @author @MnrBn
 * @codereview @borsalinoK
 */

public class AddController {

    /**
     * Controls elements displayed on screen
     */

    @FXML
    public ChoiceBox ArticleNumberBox;
    @FXML
    public ChoiceBox CategoryBox;
    @FXML
    public Button AddButton;
    @FXML
    public ChoiceBox SourceArticleBox;

    private WebSite webSite;

    private ArrayList<FeedMessage> ArticleList;
    private ArrayList<FeedMessage> CurrentArticleList;
    private ParserWebSite parserWebsite;


    /**
     * Called after scene loading
     *
     * Initialize the various controls on the screen
     */
    @FXML
    public void initialize() {
        ArticleList = new ArrayList<>();
        CurrentArticleList = new ArrayList<>();
        parserWebsite = new ParserWebSite();

        ObservableList<String> sourceArticle =
                FXCollections.observableArrayList(
                        "TheGuardian.co",
                        "Lepoint.fr",
                        "LeFigaro.fr",
                        "RTLinfo.be",
                        "LeMonde.fr"
                );
        ObservableList<String> categoryList =
                FXCollections.observableArrayList(
                        "Actualite",
                        "Politique",
                        "Environnement",
                        "Belgique",
                        "International",
                        "Culture",
                        "Sante",
                        "Economie",
                        "Sport",
                        "Technologies"
                );


        ObservableList<Integer> articleNumberList =
                FXCollections.observableArrayList(
                        1,
                        2,
                        3
                );

        ArticleNumberBox.setItems(articleNumberList);

        CategoryBox.setItems(categoryList);


        SourceArticleBox.setItems(sourceArticle);

    }

    public WebSite createObjectSource(String source){
        if (source == "TheGuardian.co"){
            return webSite = new TheGuardian();
        }
        else if (source == "Lepoint.fr"){
            return webSite = new LePoint();
        }
        else if (source == "RTLinfo.be"){
            return webSite = new RTL();
        }
        else if (source == "LeMonde.fr"){
            return webSite = new LeMonde();
        }
        else{
            return webSite = new LeFigaro();
        }
    }

    /**
     * Add Article to the list
     * Take the same number of article that the user want
     */
    public void addCurrentArticleList(){
        RSSFeedParser parser = new RSSFeedParser(webSite.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));
        Feed feed = parser.readFeed();

        for (int i=0;i<(int)ArticleNumberBox.getSelectionModel().getSelectedItem();i++){ // add Article to the list
            CurrentArticleList.add(feed.getMessages().get(i));

        }
    }

    /**
     * @param actionEvent
     * Add the article to the feed
     */
    public void OnButtonPressed(ActionEvent actionEvent) throws Exception {
        int ArticleCount = ArticleList.size();
        if(CategoryBox.getSelectionModel().isEmpty() ||
                ArticleNumberBox.getSelectionModel().isEmpty()
                || SourceArticleBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have to choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        createObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!webSite.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This category doesnt exist on this website", ButtonType.OK);
            alert.showAndWait();
            return;
        }


        addCurrentArticleList(); // Add Article to the list


        for (int i = 0; i < CurrentArticleList.size(); i++) {
            //Popup dialog window
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    CurrentArticleList.get(i).getDescription(), //.substring(0, 241) + " ... "
                    ButtonType.OK, ButtonType.CANCEL);
            //Setup dialog window controls
            ButtonType importButton = new ButtonType("Import");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(importButton, cancelButton);
            alert.setTitle("Article Preview ");
            alert.setHeaderText(CurrentArticleList.get(i).getTitle());

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == importButton){
                // ... user chose OK
                // we set the right article to the object FeedMessage
                ArticleList.add(CurrentArticleList.get(i));
                ArticleList.get(ArticleList.size()-1).setArticle(parserWebsite.ParserArticle(ArticleList.get(ArticleList.size()-1).getLink()));

               System.out.println(ArticleList.get(ArticleList.size()-1).getArticle());

               System.out.println("-----------------------");


            } else if(result.get() == cancelButton){
                // ... user chose CANCEL or closed the dialog
            }
        }
        CurrentArticleList.clear();

    }


    /**
     * @param actionEvent
     * Open the Menu View
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

}
