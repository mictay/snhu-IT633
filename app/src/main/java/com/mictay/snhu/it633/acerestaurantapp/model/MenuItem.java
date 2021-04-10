package com.mictay.snhu.it633.acerestaurantapp.model;

/******************************************************************************
 *
 */
public class MenuItem {

    public String itemId;
    public String itemName;
    public String itemPrice;
    public String categoryId;
    public String itemDescription;
    public String imageUrl;
    public int uui;

    // Constructor
    public MenuItem(String itemId, String itemName, String itemPrice, String categoryId, String itemDescription, String imageUrl) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.categoryId = categoryId;
        this.itemDescription = itemDescription;
        this.imageUrl = imageUrl;
    }

}
