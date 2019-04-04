package com.be.ac.ulb.g05;


import javafx.scene.image.ImageView;

import java.awt.*;

/**
 * @Author : @Mnrbn
 * @codereview @mouscb
 *
 * Class that allow us to give a cell to listView for the display
 */
public class PreviewThumbnailCell {

    /**
     * Little preview of the article
     */
    private String previewText;
    /**
     * Image
     */
    private ImageView image;

    /**
     * Thumbnail title
     */
    private String title;

    /**
     * Thumbnail date
     */
    private String date;

    /**
     * Thumbnail localisation
     */
    private String localisation;

    /**
     * Thumbnail source
     */
    private String source;

    /**
     * Constructor
     * @param image thumbnail
     * @param title thumbnail title
     * @param date thumbnail date
     * @param localisation thumbnail localisation
     * @param source thumbnail source
     */
    public PreviewThumbnailCell (ImageView image, String title, String date, String localisation, String source){
        this.image = image;
        this.image .setFitWidth(150);
        this.image .setPreserveRatio(true);
        this.image .setSmooth(true);
        this.image .setCache(true);
        this.title = title;
        this.date = date;
        this.localisation = localisation;
        this.source = source;
    }


    public PreviewThumbnailCell(String title,String previewText){
        this.title = title;
        this.previewText = previewText;
    }

    /**
     * Image getter
     * @return image
     */
    public ImageView getImage(){
        return image;
    }

    /**
     * Title getter
     * @return title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Thumbnail date getter
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Thumbnail localisation getter
     * @return localisation
     */
    public String getLocalisation() {
        return localisation;
    }

    /**
     * Thumbnail source getter
     * @return source
     */
    public String getSource() {
        return source;
    }

    public String getPreviewText() {
        return previewText;
    }
}

