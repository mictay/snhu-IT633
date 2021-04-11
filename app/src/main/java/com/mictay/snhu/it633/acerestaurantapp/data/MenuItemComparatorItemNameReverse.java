package com.mictay.snhu.it633.acerestaurantapp.data;

import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.util.Comparator;

public class MenuItemComparatorItemNameReverse  implements Comparator<MenuItem> {

    @Override
    public int compare(MenuItem o1, MenuItem o2) {
        return o2.itemName.compareTo(o1.itemName);
    }

}
