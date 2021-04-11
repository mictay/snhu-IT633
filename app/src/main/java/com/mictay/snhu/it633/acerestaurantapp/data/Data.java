package com.mictay.snhu.it633.acerestaurantapp.data;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuCategory;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Random;

/******************************************************************************
 * MOCK DATA, should come from a database or an online service
 *
 */
public abstract class Data {

    public static String lastSearchTerm = "";

    private static ArrayList<MenuCategory> categories;
    private static ArrayList<MenuItem> items;

    private static ArrayList<String> randomDelights = new ArrayList<>();

    private static String[] quantitySpinnerValues;

    /**************************************************************************
     * Simple list of 1-10 for spinner controls
     *
     * @return
     */
    public static String[] getQuantitySpinnerValues() {
        return quantitySpinnerValues;
    }

    /**************************************************************************
     * Returns a simple array of menu categories... no sorting needed,
     * no searching needed.
     * @return
     */
    public static ArrayList<MenuCategory> getMenuCategoryList() {
        return categories;
    }

    /**************************************************************************
     * Returns a new Array of Items based on the Category passed.
     *
     * @param categoryId If empty or null, then the entire list is returned.
     * @return
     */
    public static ArrayList<MenuItem> getMenuItemList(String categoryId) {

        if (categoryId == null || categoryId.length() == 0)
            return items;

        ArrayList<MenuItem> ret = new ArrayList<MenuItem>();

        for(MenuItem item :  items) {
            if (item.categoryId != null && item.categoryId.equalsIgnoreCase(categoryId))
                ret.add(item);
        }

        return ret;
    }

    /**************************************************************************
     * Find the menu item we're looking for
     *
     * BRUTE force find, mock up only, should use a data source
     *
     * @param menuItemId
     * @return
     */
    public static MenuItem getMenuItem(String menuItemId) {

        for (MenuItem item : items) {
            if (item.itemId != null && item.itemId.equalsIgnoreCase(menuItemId))
                return item;
        }

        String imageUrl = String.valueOf(R.drawable.ic_item);

        return new MenuItem("-1",
                "Item Not found",
                0d,
                "-1",
                "The item you are looking for is not here",
                imageUrl
                );
    }

    /**************************************************************************
     * Silly thing to do?
     *
     * @return
     */
    public static String getRandomDelightTitle() {
        return randomDelights.get((new Random()).nextInt(randomDelights.size()));
    }

    /**************************************************************************
     * Initialize our Mock up data
     *
     */
    static {

        // Lets populate all the Categories
        categories = new ArrayList<>();

        MenuCategory category1 = new MenuCategory("1", "Starters", "Great way to start your meal by sharing some delicious appetizers.", String.valueOf(R.mipmap.cat_starters_foreground));
        MenuCategory category2 = new MenuCategory("2", "Soups & Salads", "Farmed fresh every day.", String.valueOf(R.mipmap.cat_salads_foreground));
        MenuCategory category3 = new MenuCategory("3", "Entrees", "Large selection of rich flavored entrees.  Served with two sides.", String.valueOf(R.mipmap.cat_entrees_foreground));
        MenuCategory category4 = new MenuCategory("4", "Beverages", "Hot or Cold, there is nothing better to quench a thirst", String.valueOf(R.mipmap.cat_beverages_foreground));
        MenuCategory category5 = new MenuCategory("4", "Desserts", "All desserts are made by our award winning chefs", String.valueOf(R.mipmap.cat_desserts_foreground));
        MenuCategory category6 = new MenuCategory("4", "Kids", "For guest under 10 and under.  All kids meals come with a side and a drink", String.valueOf(R.mipmap.cat_kids_foreground));

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);

        // Lets populate all the Items for Starters
        items = new ArrayList<MenuItem>();
        MenuItem obj0 = new MenuItem("0", "Air Fryers", 4.99, "1", "Air fried Cauliflower and Broccoli Bites", String.valueOf(R.mipmap.item_air_fryer_cauliflower_broccoli_bites_foreground));
        MenuItem obj1 = new MenuItem("1", "Apple Cheddar Skewers", 5.99, "1", "Apple on a skewer, followed by cube of cheese", String.valueOf(R.mipmap.item_apple_cheddar_skewers_foreground));
        MenuItem obj2 = new MenuItem("2", "Cajun King Hushpuppies", 6.99, "1", "Spicy fried golden brown hush puppies",  String.valueOf(R.mipmap.item_cajun_king_hushpuppies_foreground));
        MenuItem obj3 = new MenuItem("3", "Deep fried shrimp", 7.99, "1", "These fried shrimp are jumbo shrimp coated in seasoned breadcrumbs.", String.valueOf(R.mipmap.item_deep_fried_shrip_foreground));
        MenuItem obj4 = new MenuItem("4", "Endive", 8.99, "1", "Roasted endive with a hearty texture and tart buttery flavor", String.valueOf(R.mipmap.item_endive_foreground));
        MenuItem obj5 = new MenuItem("5", "Fusion Arepas", 9.99, "1", "We combine the traditional Venezuelan arepas with american and international flavors", String.valueOf(R.mipmap.item_fusion_arepas_foreground));
        MenuItem obj6 = new MenuItem("6", "High Fiber Hummus", 10.99, "1", "Hummus is a great source of dietary fiber, which can improve digestive health", String.valueOf(R.mipmap.item_high_fiber_hummus_foreground));
        MenuItem obj7 = new MenuItem("7", "Mint Chutney", 11.99, "1", "Packed with cilantro with stems dried and chopped.", String.valueOf(R.mipmap.item_mint_chutney_foreground));
        MenuItem obj8 = new MenuItem("8", "Olive Cheese Balls", 12.99, "1", "Made with cheese, butter, flour and cayenne", String.valueOf(R.mipmap.item_olive_cheese_balls_foreground));
        MenuItem obj9 = new MenuItem("9", "Pomegranate Cheese Balls", 13.99, "1", "Pomegranate Jeweled White Cheddar, Toasted Almond and Crispy Sage", String.valueOf(R.mipmap.item_pomegranate_cheese_ball_foreground));
        MenuItem obj10 = new MenuItem("10", "Potato Skins", 4.99, "1", "There is nothing like a plate full of crispy potato skins, filled with melty cheddar cheese, and topped with bacon bits, sour cream and green onions", String.valueOf(R.mipmap.item_potato_skins_foreground));
        MenuItem obj11 = new MenuItem("11", "Pie Dip Cinnamon Chips", 5.99, "1", "Apple Pie Dip with Cinnamon Sugar Chips is a super fun twist on the classic flavors of apple pie", String.valueOf(R.mipmap.item_pumpkin_pie_dip_cinnamon_chips_foreground));
        MenuItem obj12 = new MenuItem("12", "Shrimp toast", 6.99, "1", "Shrimp toast is the latest addition to our compendium of retro Chinese takeout recipes", String.valueOf(R.mipmap.item_shrimp_toast_foreground));
        MenuItem obj13 = new MenuItem("13", "Bruschetta", 7.99, "1", "Nothing screams summer quite like bruschetta",  String.valueOf(R.mipmap.item_bruschetta_foreground));
        MenuItem obj14 = new MenuItem("14", "Cucumber Stuffed Tomatoes", 8.99, "1", "Cherry tomatoes stuffed with cucumber, onion, dill, and cream cheese.",  String.valueOf(R.mipmap.item_cucumber_tomatoes_foreground));
        MenuItem obj15 = new MenuItem("15", "Tamales", 9.99, "1", "Tamales are wrapped and cooked in corn husks or banana leaves",  String.valueOf(R.mipmap.item_tamales_foreground));
        MenuItem obj16 = new MenuItem("16", "Uthappam", 10.99, "1", "Indian sharable pancakes made with lentils, rice, onions, herbs & spices",  String.valueOf(R.mipmap.item_uthappam_foreground));
        MenuItem obj17 = new MenuItem("17", "Taco Dip", 11.99, "1", "Taco Dip from the deepest part of Mexico",  String.valueOf(R.mipmap.item_taco_dip_foreground));
        MenuItem obj18 = new MenuItem("18", "Sausage Bread Rolls", 12.99, "1", "Guess whats in the sausage stuffed in bread rolls.",  String.valueOf(R.mipmap.item_sausage_roll_bread_foreground));
        MenuItem obj19 = new MenuItem("19", "Meatballs", 13.99, "1", "Tiny little meatballs for everyone's pleasure.",  String.valueOf(R.mipmap.item_meatball_bites_foreground));
        MenuItem obj20 = new MenuItem("20", "Bourbon Cocktail Sausages", 4.99, "1", "These little delights pack punch", String.valueOf(R.mipmap.item_bourban_sausages_foreground));

        items.add(obj0);
        items.add(obj1);
        items.add(obj2);
        items.add(obj3);
        items.add(obj4);
        items.add(obj5);
        items.add(obj6);
        items.add(obj7);
        items.add(obj8);
        items.add(obj9);
        items.add(obj10);
        items.add(obj11);
        items.add(obj12);
        items.add(obj13);
        items.add(obj14);
        items.add(obj15);
        items.add(obj16);
        items.add(obj17);
        items.add(obj18);
        items.add(obj19);
        items.add(obj20);
        Collections.sort(items, new MenuItemComparatorItemName());

        // tiny fun-ification verbiage in the title bar
        randomDelights.add("Amazing Food");
        randomDelights.add("Yummmmy");
        randomDelights.add("You Starving?");
        randomDelights.add("Get in my Tummy");
        randomDelights.add("Loving it?");
        randomDelights.add("Someone say food?");
        randomDelights.add("Always room for more");
        randomDelights.add("Delicious");
        randomDelights.add("Be American, Eat");
        randomDelights.add("You want this?");
        randomDelights.add("Try it");
        randomDelights.add("You got Dibs");
        randomDelights.add("Having fun yet?");
        randomDelights.add("I love you");
        randomDelights.add("Legendary");
        randomDelights.add("Your Idea?");
        randomDelights.add("Eat something...");
        randomDelights.add("Chose wisely");
        randomDelights.add("Marry me?");
        randomDelights.add("Tah Dah!");
        randomDelights.add("Smell it?");

        // Quantity Spinner values
        quantitySpinnerValues = new String[10];
        for (int i = 0; i < 10; i++) {
            quantitySpinnerValues[i] = String.valueOf( (i+1) );
        }

    }

}
