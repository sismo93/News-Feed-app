package com.be.ac.ulb.g05.Model;

/**
 * Article Object
 * @author @Mouscb
 * @codereview @MnrBn
 */
public class Article {

    /**
     * Article title
     */
    private String title;

    /**
     * Article description
     */
    private String description;

    /**
     * Article link
     */
    private String link;

    /**
     * Article content
     */
    private String content;

    /**
     * Article image
     */
    private String image;

    /**
     * Article default thumbnail
     */
    private String defaultThumbnail;

    /**
     * Article source
     */
    private String source;

    /**
     * Article geolocation
     */
    private String geolocation;

    /**
     * Article publication date
     */
    private String pubDate;

    /**
     * Article video link
     */
    private String video;

    /**
     * Image getter
     * @return image URL
     */
    public String getImage(){
        return this.image;
    }

    /**
     * Image setter
     * @param image article image
     */
    public void setImage(String  image){
        this.image = image;
    }

    /**
     * Source getter
     * @return article source URL
     */
    public String  getSource(){
        return this.source;
    }

    /**
     * Source setter
     * @param source article source
     */
    public void setSource(String  source){
        this.source = source;
    }

    /**
     * Publication date getter
     * @return publication date
     */
    public String getPubDate(){
        return this.pubDate;
    }

    /**
     * Default thumbnail setter
     * @param image article image URL
     */
    public void setDefaultThumbnail(String image){
        this.defaultThumbnail = image;
    }

    /**
     * Default thumbnail getter
     * @return default thumbnail
     */
    public String getDefaultThumbnail(){
        return this.defaultThumbnail;
    }

    /**
     * Article title getter
     * @return article title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Article description
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Article description setter
     * @param description article description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Article URL
     * @return article URL
     */
    public String getLink() {
        return this.link;
    }

    /**
     * Article content
     * @return article content
     */
    public String getContent(){
        return this.content;
    }

    /**
     * Article content setter
     * @param content article content
     */
    public void setContent(String content){
        this.content = content;
    }

    /**
     * Article geolocation getter
     * @return article geolocation
     */
    public String getGeolocation() {
        return this.geolocation;
    }

    /**
     * Article geolocation setter
     * @param geolocation article geolocation
     */
    public void setGeolocation(String geolocation) {
        this.geolocation = geolocation;
    }

    public void setTitle(String title) {
        this.title= title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setPubDate(String pubdate) {
        this.pubDate = pubdate;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getVideo() {
        return video;
    }
}