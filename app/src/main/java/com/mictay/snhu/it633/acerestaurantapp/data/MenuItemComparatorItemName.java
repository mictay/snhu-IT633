package com.mictay.snhu.it633.acerestaurantapp.data;

import com.mictay.snhu.it633.acerestaurantapp.model.CartItem;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.util.Comparator;

public class MenuItemComparatorItemName implements Comparator<MenuItem> {

    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        return o1.itemName.compareTo(o2.itemName);
    }

}
