package com.mictay.snhu.it633.acerestaurantapp.model;

/******************************************************************************
 *
 */
public class MenuCategory {

    public String categoryId;
    public String categoryName;
    public String categoryDescription;
    public String imageUrl;
    public int uui;

    // Constructor
    public MenuCategory(String categoryId, String categoryName, String categoryDescription, String imageUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.imageUrl = imageUrl;
    }

}
