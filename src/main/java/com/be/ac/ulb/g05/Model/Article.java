package com.be.ac.ulb.g05.Model;

public class Article {

    private String title;
    private String description;
    private String link;
    private String author;
    private String guid;
    private String article;
    private String image;
    private String video;


    public String getImage(){
        return image;
    }

    public void setImage(String image){
        this.image=image;
    }

    public String getVideo(){
        return video;
    }
    public void setVideo(String video){
        this.video = video;
    }


    public String getTitle() {

        return title;
    }



    public void setTitle(String title) {
        this.title = title;
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

    public void setLink(String link) {

        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public void setGuid(String guid) {
        this.guid = guid;
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