package com.example.yummy.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchRecipeItem {

    @SerializedName("social_rank")
    @Expose
    private double socialRank;

    @SerializedName("f2f_url")
    @Expose
    private String f2fUrl;

    @SerializedName("recipe_id")
    @Expose
    private String recipeId;

    @SerializedName("publisher_url")
    @Expose
    private String publisherUrl;

    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("source_url")
    @Expose
    private String sourceUrl;

    public void setSocialRank(int socialRank){
        this.socialRank = socialRank;
    }

    public double getSocialRank(){
        return socialRank;
    }

    public void setF2fUrl(String f2fUrl){
        this.f2fUrl = f2fUrl;
    }

    public String getF2fUrl(){
        return f2fUrl;
    }

    public void setRecipeId(String recipeId){
        this.recipeId = recipeId;
    }

    public String getRecipeId(){
        return recipeId;
    }

    public void setPublisherUrl(String publisherUrl){
        this.publisherUrl = publisherUrl;
    }

    public String getPublisherUrl(){
        return publisherUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setSourceUrl(String sourceUrl){
        this.sourceUrl = sourceUrl;
    }

    public String getSourceUrl(){
        return sourceUrl;
    }
}