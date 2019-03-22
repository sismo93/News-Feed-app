package com.be.ac.ulb.g05;


import javafx.scene.image.ImageView;

/**
 * @Author : @Mnrbn
 * @codereview @mouscb
 *
 * Class that allow us to give a cell to listView for the display
 */
public class PreviewThumbnailCell {

    private ImageView image;
    private String title;
    private String date;
    private String localisation;
    private String source;

    public PreviewThumbnailCell (ImageView image, String title, String date, String loca, String source){

        this.image = image;
        this.image .setFitWidth(150);
        this.image .setPreserveRatio(true);
        this.image .setSmooth(true);
        this.image .setCache(true);

        this.title = title;
        this.date = date;
        this.localisation = loca;
        this.source = source;
    }

    public ImageView getImage(){
        return image;
    }

    public String getTitle(){
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getLocalisation() {
        return localisation;
    }

    public String getSource() {
        return source;
    }

}

