package com.be.ac.ulb.g05;

import java.util.ArrayList;
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
    private Map<String, String> categoryDict =new HashMap<>();
    protected List<String> urlList;
    protected List<String> categoryList;
    protected String defaultThumbnail;
    protected String sourceArticle;
    protected String geolocation;


    public Website(List urls, List categories, String defaultThumbnail, String sourceArticle, String geolocation) {
        this.categoryList = categories;
        this.urlList = urls;
        this.defaultThumbnail = defaultThumbnail;
        this.sourceArticle = sourceArticle;
        this.geolocation = geolocation;
        this.initDico();
    }

    public boolean isCategoryExist(String category){
        return categoryList.contains(category);
    }

    /**
     * initialize the dictionnary
     * Dict :{Category : URL }
     */
    private void initDico(){
        for(int i=0;i<urlList.size();i++){
            categoryDict.put(categoryList.get(i),urlList.get(i));
        }
    }

    /**
     * @param category
     * @return the right link for the category
     */
    public String getLink(String category){
        return categoryDict.get(category);

    }

    public String getDefaultThumbnail(){
        return defaultThumbnail;
    }

    public String getSourceArticle(){
        return sourceArticle;
    }

    public String getGeolocation(){
        return geolocation;
    }



}
