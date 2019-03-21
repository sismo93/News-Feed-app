package com.be.ac.ulb.g05.Model;

import java.util.ArrayList;

public class Article {

    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;
    private String article;
    private String image;
    private String defautlThumbnail;

    public Article(String title, String description, String link, String author, String guid, String article, String  image, String thumbnail) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.author = author;
        this.guid = guid;
        this.article = article;
        this.image = image;
        this.defautlThumbnail = thumbnail;
    }


    public String  getImage(){
        return image;
    }

    public void setImage(String  image){
        this.image=image;
    }

    public void setDefaultThumbnail(String image){
        this.defautlThumbnail = image;
    }
    public String getDefautlThumbnail(){
        return defautlThumbnail;
    }

    public String getTitle() {

        return title;
    }


    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getLink() {

        return link;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    @Override
    public String toString() {
        return "Article [title=" + title + ", description=" + description
                + ", link=" + link + ", author=" + author + ", guid=" + guid
                + "]";
    }

    public String getArticle(){
        return article;
    }
    public void setArticle(String article){
        this.article = article;
    }
}