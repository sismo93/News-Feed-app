package be.ac.ulb.infof307.g05;

public class FeedMessage {

    String title;
    String description;
    String link;
    String author;
    String guid;
    String article;


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
        return "FeedMessage [title=" + title + ", description=" + description
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