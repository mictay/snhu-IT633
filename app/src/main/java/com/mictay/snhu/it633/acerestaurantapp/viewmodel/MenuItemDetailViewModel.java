package com.mictay.snhu.it633.acerestaurantapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mictay.snhu.it633.acerestaurantapp.data.Data;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

/******************************************************************************
 * Provides the MenuItem details to who ever is listening
 */
public class MenuItemDetailViewModel extends ViewModel {

    public MutableLiveData<MenuItem> menuItemLiveData = new MutableLiveData<MenuItem>();

    // we need a view to call this function
    public void fetch(String menuItemId) {
        menuItemLiveData.setValue(Data.getMenuItem(menuItemId));
    }

}
