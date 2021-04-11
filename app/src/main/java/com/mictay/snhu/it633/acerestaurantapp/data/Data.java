package com.mictay.snhu.it633.acerestaurantapp.data;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuCategory;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.lang.reflect.Array;
import java.util.ArrayList;

/******************************************************************************
 * MOCK DATA, should come from a database or an online service
 *
 */
public abstract class Data {

    private static ArrayList<MenuCategory> categories;
    private static ArrayList<MenuItem> items;

    /**************************************************************************
     *
     * @return
     */
    public static ArrayList<MenuCategory> getMenuCategoryList() {
        return categories;
    }

    /**************************************************************************
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
        MenuItem obj0 = new MenuItem("0", "Air Fryers", "$4.99", "1", "Air fried Cauliflower and Broccoli Bites", String.valueOf(R.mipmap.item_air_fryer_cauliflower_broccoli_bites_foreground));
        MenuItem obj1 = new MenuItem("1", "Apple Cheddar Skewers", "$5.99", "1", "Apple on a skewer, followed by cube of cheese", String.valueOf(R.mipmap.item_apple_cheddar_skewers_foreground));
        MenuItem obj2 = new MenuItem("2", "Cajun King Hushpuppies", "$6.99", "1", "Spicy fried golden brown hush puppies",  String.valueOf(R.mipmap.item_cajun_king_hushpuppies_foreground));
        MenuItem obj3 = new MenuItem("3", "Deep fried shrimp", "$7.99", "1", "These fried shrimp are jumbo shrimp coated in seasoned breadcrumbs.", String.valueOf(R.mipmap.item_deep_fried_shrip_foreground));
        MenuItem obj4 = new MenuItem("4", "Endive", "$8.99", "1", "Roasted endive with a hearty texture and tart buttery flavor", String.valueOf(R.mipmap.item_endive_foreground));
        MenuItem obj5 = new MenuItem("5", "Fusion Arepas", "$9.99", "1", "We combine the traditional Venezuelan arepas with american and international flavors", String.valueOf(R.mipmap.item_fusion_arepas_foreground));
        MenuItem obj6 = new MenuItem("6", "High Fiber Hummus", "$10.99", "1", "Hummus is a great source of dietary fiber, which can improve digestive health", String.valueOf(R.mipmap.item_high_fiber_hummus_foreground));
        MenuItem obj7 = new MenuItem("7", "Mint Chutney", "$11.99", "1", "Packed with cilantro with stems dried and chopped.", String.valueOf(R.mipmap.item_mint_chutney_foreground));
        MenuItem obj8 = new MenuItem("8", "Olive Cheese Balls", "$12.99", "1", "Made with cheese, butter, flour and cayenne", String.valueOf(R.mipmap.item_olive_cheese_balls_foreground));
        MenuItem obj9 = new MenuItem("9", "Pomegranate Cheese Balls", "$13.99", "1", "Pomegranate Jeweled White Cheddar, Toasted Almond and Crispy Sage", String.valueOf(R.mipmap.item_pomegranate_cheese_ball_foreground));
        MenuItem obj10 = new MenuItem("10", "Potato Skins", "$4.99", "1", "There is nothing like a plate full of crispy potato skins, filled with melty cheddar cheese, and topped with bacon bits, sour cream and green onions", String.valueOf(R.mipmap.item_potato_skins_foreground));
        MenuItem obj11 = new MenuItem("11", "Pie Dip Cinnamon Chips", "$5.99", "1", "Apple Pie Dip with Cinnamon Sugar Chips is a super fun twist on the classic flavors of apple pie", String.valueOf(R.mipmap.item_pumpkin_pie_dip_cinnamon_chips_foreground));
        MenuItem obj12 = new MenuItem("12", "Shrimp toast", "$6.99", "1", "Shrimp toast is the latest addition to our compendium of retro Chinese takeout recipes", String.valueOf(R.mipmap.item_shrimp_toast_foreground));
        MenuItem obj13 = new MenuItem("13", "Mystery Item 13", "$7.99", "1", "Description of Mystery Item 13", "");
        MenuItem obj14 = new MenuItem("14", "Mystery Item 14", "$8.99", "1", "Description of Mystery Item 14", "");
        MenuItem obj15 = new MenuItem("15", "Mystery Item 15", "$9.99", "1", "Description of Mystery Item 15", "");
        MenuItem obj16 = new MenuItem("16", "Mystery Item 16", "$10.99", "1", "Description of Mystery Item 16", "");
        MenuItem obj17 = new MenuItem("17", "Mystery Item 17", "$11.99", "1", "Description of Mystery Item 17", "");
        MenuItem obj18 = new MenuItem("18", "Mystery Item 18", "$12.99", "1", "Description of Mystery Item 18", "");
        MenuItem obj19 = new MenuItem("19", "Mystery Item 19", "$13.99", "1", "Description of Mystery Item 19", "");
        MenuItem obj20 = new MenuItem("20", "Mystery Item 20", "$4.99", "1", "Description of Mystery Item 20", "");
        MenuItem obj21 = new MenuItem("21", "Mystery Item 21", "$5.99", "1", "Description of Mystery Item 21", "");
        MenuItem obj22 = new MenuItem("22", "Mystery Item 22", "$6.99", "1", "Description of Mystery Item 22", "");
        MenuItem obj23 = new MenuItem("23", "Mystery Item 23", "$7.99", "1", "Description of Mystery Item 23", "");
        MenuItem obj24 = new MenuItem("24", "Mystery Item 24", "$8.99", "1", "Description of Mystery Item 24", "");
        MenuItem obj25 = new MenuItem("25", "Mystery Item 25", "$9.99", "1", "Description of Mystery Item 25", "");
        MenuItem obj26 = new MenuItem("26", "Mystery Item 26", "$10.99", "1", "Description of Mystery Item 26", "");
        MenuItem obj27 = new MenuItem("27", "Mystery Item 27", "$11.99", "1", "Description of Mystery Item 27", "");
        MenuItem obj28 = new MenuItem("28", "Mystery Item 28", "$12.99", "1", "Description of Mystery Item 28", "");
        MenuItem obj29 = new MenuItem("29", "Mystery Item 29", "$13.99", "1", "Description of Mystery Item 29", "");

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
        items.add(obj21);
        items.add(obj22);
        items.add(obj23);
        items.add(obj24);
        items.add(obj25);
        items.add(obj26);
        items.add(obj27);
        items.add(obj28);
        items.add(obj29);
    }

}
