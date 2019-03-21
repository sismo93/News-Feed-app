package com.be.ac.ulb.g05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebSite {
    private Map<String, String> categoryDict =new HashMap<>();
    protected List<String> urlList;
    protected List<String> categoryList;
    protected String defaultThumbnail;


    public boolean isCategoryExist(String category){
        return categoryList.contains(category);
    }

    public void initDico(){
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



}
