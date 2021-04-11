package com.mictay.snhu.it633.acerestaurantapp.data;

import com.mictay.snhu.it633.acerestaurantapp.model.CartItem;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.util.Comparator;

public class CartItemComparatorItemName implements Comparator<CartItem> {

    @Override
    public int compare(CartItem o1, CartItem o2) {
        return o1.itemName.compareTo(o2.itemName);
    }

}
