package com.be.ac.ulb.g05.Model;

public class Article {

    private String title;
    private String description;
    private String link;
    private String article;
    private String image;
    private String defautlThumbnail;
    private String source;
    private String geolocation;
    private String pubdate;

    public Article(String title, String description, String link,String pubdate,String image , String thumbnail,String source,String geolocation) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.image = image;
        this.defautlThumbnail = thumbnail;
        this.source = source;
        this.geolocation = geolocation;
        this.pubdate = pubdate;
    }


    public String  getImage(){
        return this.image;
    }

    public void setImage(String  image){

        this.image=image;
    }
    public String  getSource(){

        return this.source;
    }

    public void setSource(String  source){

        this.source=source;
    }

    public String getPubdate(){
        return pubdate;
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


    @Override
    public String toString() {
        return "Article [title=" + title + ", description=" + description
                + ", link=" + link + " ]";
    }

    public String getArticle(){
        return article;
    }
    public void setArticle(String article){
        this.article = article;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }
}