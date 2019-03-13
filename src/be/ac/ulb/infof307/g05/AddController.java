package be.ac.ulb.infof307.g05;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
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


    /**
     * Called after scene loading
     *
     * Initialize the various controls on the screen
     */
    @FXML
    public void initialize() {
        ObservableList<String> sourceArticle =
                FXCollections.observableArrayList(
                        "7sur7.be",
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
        if (source == "7sur7.be"){
            return webSite = new SeptSurSept();
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
     * @param actionEvent
     * Add the article to the feed
     */
    public void OnButtonPressed(ActionEvent actionEvent) {

        String url = "Mok";//UrlBox.getText();


        //ArrayList<String[]> article = new ArrayList<>();


        if(CategoryBox.getSelectionModel().isEmpty() ||
                ArticleNumberBox.getSelectionModel().isEmpty()
                || SourceArticleBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "You have to choose", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        createObjectSource((String) SourceArticleBox.getSelectionModel().getSelectedItem());

        if (!webSite.isCategoryExist(CategoryBox.getSelectionModel().getSelectedItem().toString())){
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "This caterogy doesnt exist on this website", ButtonType.OK);
            alert.showAndWait();
            return;
        }


        RSSFeedParser parser = new RSSFeedParser(webSite.getLink(CategoryBox.getSelectionModel().getSelectedItem().toString()));
        Feed feed = parser.readFeed();
        int i = 0;

        System.out.println(feed);

        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
            System.out.println("AUTHOOOR ;" + message.author);
            System.out.println("DESCRIPTION : " + message.description);
            break;
        }

/*
        try {

            ArrayList<String> urlsList =  Homepage("https://www.bbc.com/",
                    CategoryBox.getSelectionModel().getSelectedItem().toString(),
                    (int) ArticleNumberBox.getSelectionModel().getSelectedItem());

            for (String s : urlsList) {

                url = s;

                // fetch the correponding article content from the url

                try {
                    article.add(Article(url).get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(article.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Importation failed", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        for (int i = 0; i < article.size(); i++) {
            //Popup dialog window
            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    article.get(i)[1].substring(0, 241) + " ... ",
                    ButtonType.OK, ButtonType.CANCEL);
            //Setup dialog window controls
            ButtonType importButton = new ButtonType("Import");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(importButton, cancelButton);
            alert.setTitle("Article Preview ");
            alert.setHeaderText(article.get(i)[0]);

            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == importButton){
                // ... user chose OK
                ArticleData.Instance().addArticle(article.get(i));

            } else if(result.get() == cancelButton){
                // ... user chose CANCEL or closed the dialog
            }
        }*/

    }


    /**
     * @param actionEvent
     * Open the Menu View
     */
    public void OpenMenuView(ActionEvent actionEvent) {
        Router.Instance().changeView(Router.Views.Menu);
    }

}
