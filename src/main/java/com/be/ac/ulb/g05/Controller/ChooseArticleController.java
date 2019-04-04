package com.be.ac.ulb.g05.Controller;

import com.be.ac.ulb.g05.Model.Article;
import com.be.ac.ulb.g05.Model.ArticleService;
import com.be.ac.ulb.g05.ParserWebSite;
import com.be.ac.ulb.g05.PreviewThumbnailCell;
import com.be.ac.ulb.g05.Website;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import static com.be.ac.ulb.g05.Controller.AddController.*;
import static com.be.ac.ulb.g05.Controller.AddController.WebsiteSource.Guardian;
import static com.be.ac.ulb.g05.Controller.AddController.WebsiteSource.RTL;
import static com.be.ac.ulb.g05.Controller.FeedController.showCell;

public class ChooseArticleController extends Controller implements Observer {


    public VBox articleContainer;


    private Website website;
    private ParserWebSite parserWebsite;

    @Override
    public void setupView(){
        articleService.addObserver(this);
        pushAvailableArticleToView(getAvailableArticle());

    }

    @Override
    public void update(Observable o, Object arg) {
        pushAvailableArticleToView(getAvailableArticle());
    }


    /**
     * @param articlesList
     * Show Title + preview + button to let the user choose if he want to take the
     * article or not
     */
    private void pushAvailableArticleToView(ArrayList<Article> articlesList){
        System.out.println(articlesList.size());
        setWebsite(getWebsite());
        setParserWebsite(getParserWebsite());

        articleContainer.getChildren().clear();
        ObservableList<PreviewThumbnailCell> previewArticlesList = FXCollections.observableArrayList();
        articlesList.forEach(article -> {

            PreviewThumbnailCell previewArticleCell = new PreviewThumbnailCell(article.getTitle(),article.getDescription());
            previewArticlesList.add(previewArticleCell);
        });

        ListView<PreviewThumbnailCell> listView = showCell (previewArticlesList,100,articleContainer,false); //call


        listView.getSelectionModel().selectedItemProperty().addListener((ov, oldValue, newValue) -> {
            int selectedArticleIndex = listView.getSelectionModel().getSelectedIndex();
            Article article = articlesList.get(selectedArticleIndex);



            try {
                AddArticleForFeed(article);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    /**
     * Add the article that the use choose to the List
     * Set different information about the added article
     */
    private void AddArticleForFeed(Article article) throws IOException {
        article.setContent(parserWebsite.ParserArticle(article.getLink())); // Call the parser
        //FixDescriptionError(article.getContent(), article); // Change the description


        article.setImage(parserWebsite.ParserImage(article.getLink())); //Add Picture
        article.setDefaultThumbnail(website.getDefaultThumbnail()); // add thumbnail
        article.setSource(website.getSourceArticle()); // set the Source
        article.setGeolocation(website.getGeolocation());
        article.setVideo(parserWebsite.ParserVideo(article.getLink()));

        articleService.addArticle(article);
        showAlert("Article Added","Information");
    }



    /**
     * @param article       the article source
     * @param articleObject the article object
     *                      change the description if its TheGuardian or RTLINFO
     *                      because these 2 websites doesnt handle the description for an article
     */
    /***private void FixDescriptionError(String article, Article articleObject) {
        String source = (String) SourceArticleBox.getSelectionModel().getSelectedItem();
        if ((source.equals(Guardian.source)) || (source.equals(RTL.source))) {
            articleObject.setDescription(article.substring(0, (article.length() < 100) ? article.length() : 100));
        }
    }**/

    @Override
    public void setArticleService(ArticleService articleService) {
        super.setArticleService(articleService);
    }


    public void setWebsite(Website website) {
        this.website = website;
    }

    public void setParserWebsite(ParserWebSite parserWebsite) {
        this.parserWebsite = parserWebsite;
    }
}
