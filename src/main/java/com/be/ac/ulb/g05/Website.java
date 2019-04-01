package com.be.ac.ulb.g05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author @MnrBn
 * @codereview  @Mouscb
 *
 * Class that represent a website.
 * will be used for inheritance
 */
public class Website {

    /**
     * Category dictionary
     */
    private Map<String, String> categoryDict =new HashMap<>();

    /**
     * List of URLs
     */
    private List<String> urlList;

    /**
     * List of categories
     */
    private List<String> categoryList;

    /**
     * Default thumbnail URL
     */
    private String defaultThumbnail;

    /**
     * Source article URL
     */
    private String sourceArticle;

    /**
     * Geolocation
     */
    private String geolocation;


    /**
     * Constructor
     * @param urls website URL
     * @param categories category
     * @param defaultThumbnail website thumbnail
     * @param sourceArticle source article
     * @param geolocation geolocation
     */
    public Website(List urls, List categories, String defaultThumbnail, String sourceArticle, String geolocation) {
        this.categoryList = categories;
        this.urlList = urls;
        this.defaultThumbnail = defaultThumbnail;
        this.sourceArticle = sourceArticle;
        this.geolocation = geolocation;
        this.initDico();
    }

    /**
     * @param category website category
     * @return true if category exist, otherwise false
     */
    public boolean isCategoryExist(String category){
        return categoryList.contains(category);
    }

    /**
     * initialize the dictionary
     * Dict :{Category : URL }
     */
    private void initDico(){
        for(int i=0;i<urlList.size();i++){
            categoryDict.put(categoryList.get(i),urlList.get(i));
        }
    }

    /**
     * @param category website category
     * @return the right link for the category
     */
    public String getLink(String category){
        return categoryDict.get(category);

    }

    /**
     * thumbnail getter
     * @return thumbnail
     */
    public String getDefaultThumbnail(){
        return defaultThumbnail;
    }

    /**
     * article source getter
     * @return source article
     */
    public String getSourceArticle(){
        return sourceArticle;
    }

    /**
     * geolocation getter
     * @return geolocation
     */
    public String getGeolocation(){
        return geolocation;
    }
}
