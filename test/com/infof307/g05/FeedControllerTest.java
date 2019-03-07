package com.infof307.g05;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeedControllerTest {

    public ListView ArticleViewField;


    @org.junit.jupiter.api.Test
    void initialize() {
        // TODO: 3/5/19 get the news thru the parser module
        /// Dummy list
        ObservableList<String> articleList =
                FXCollections.observableArrayList(
                        "Article 1, Dz ioaj dizajoi djazoi djozai jdi za",
                        "Article 2, Doza jiodjza djazio jdoza jodza joida jz ",
                        "Article 3, Tizj iijdjzaij dzai jdazij doa zdo zajod jzaoijd"
                );

        ArticleViewField.setItems(articleList);
    }

    @Test
    void getEmailTest() {
        assertEquals(ArticleViewField.getItems(), null);
    }

}