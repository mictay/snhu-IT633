package com.mictay.snhu.it633.acerestaurantapp.view.cart;

import com.mictay.snhu.it633.acerestaurantapp.data.CartItemComparatorItemName;
import com.mictay.snhu.it633.acerestaurantapp.data.Data;
import com.mictay.snhu.it633.acerestaurantapp.data.MenuItemComparatorItemName;
import com.mictay.snhu.it633.acerestaurantapp.model.CartItem;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;
import com.mictay.snhu.it633.acerestaurantapp.util.AceUtils;

import java.util.ArrayList;
import java.util.Collections;

/**************************************************************************
 * Simple Shopping Cart functionality
 *
 */
public abstract class CartData {

    private static ArrayList<CartItem> shoppingCart = new ArrayList<>();

    /**************************************************************************
     * Convient method for see if there is anything in our shopping cart
     *
     * @return
     */
    public static boolean hasCartItems() {
        return shoppingCart.size() == 0;
    }

    /**************************************************************************
     *
     * @return
     */
    public static ArrayList<CartItem> fetch() {
        return shoppingCart;
    }

    /**************************************************************************
     *
     * @param menuItemId
     */
    public static void addMenuItem(String menuItemId, int quantity) {

        MenuItem menuItem = Data.getMenuItem(menuItemId);

        // We don't want to support adding null or the not-found menuItem
        if (menuItem.itemId != null && !menuItem.itemId.equalsIgnoreCase("-1")) {
            boolean existing = true;
            CartItem cartItem = getCartItem(menuItemId);

            if (cartItem == null) {
                cartItem = new CartItem();
                existing = false;
            }

            cartItem.imageUrl = menuItem.imageUrl;
            cartItem.itemId = menuItem.itemId;
            cartItem.quantity = cartItem.quantity + quantity;  //add to the existing quantity
            cartItem.itemPrice = menuItem.itemPrice;
            cartItem.itemName = menuItem.itemName;

            if (!existing)
                shoppingCart.add(cartItem);

            Collections.sort(shoppingCart, new CartItemComparatorItemName());
        }

    }

    /**************************************************************************
     *
     * @param menuItemId
     */
    public static CartItem getCartItem(String menuItemId) {
       for (CartItem item: shoppingCart) {
           if (item.itemId != null && item.itemId.equalsIgnoreCase(menuItemId))
               return item;
       }
       return null;
    }

    /**************************************************************************
     *
     * @param menuItemId
     */
    public static void removeCartItem(String menuItemId) {

        for(int i = 0; i < shoppingCart.size(); i++) {
            CartItem item = shoppingCart.get(i);

            if (item.itemId != null && item.itemId.equalsIgnoreCase(menuItemId)) {
                shoppingCart.remove(i);
                break;
            }

        }

        Collections.sort(shoppingCart, new CartItemComparatorItemName());
    }

    /**************************************************************************
     *
     * @return
     */
    public static String getTotal() {
        double total = 0d;

        for (CartItem item : shoppingCart) {
            total += (item.itemPrice * item.quantity);
        }

        return AceUtils.formatCurrency(total);
    }

}
