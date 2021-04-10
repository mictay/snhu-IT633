package com.mictay.snhu.it633.acerestaurantapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mictay.snhu.it633.acerestaurantapp.model.MenuCategory;

import java.util.ArrayList;
import java.util.List;

/************************************************************************************
 * We could tie more than 1 model to this View-Model, but in practice this is
 * discouraged.  We will keep with the 1 model to 1 view-model
 *
 */
public class MenuCategoryListViewModel extends AndroidViewModel {

    // Mutable Live data means we can change the variable when needed
    public MutableLiveData<List<MenuCategory>> menuCategoryList = new MutableLiveData<List<MenuCategory>>();
    public MutableLiveData<Boolean> menuCategoryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> menuCategoryLoading = new MutableLiveData<Boolean>();

    /*******************************************************************
     * Constructor
     *
     * @param application
     */
    public MenuCategoryListViewModel(@NonNull Application application) {
        super(application);
    }

    /********************************************************************
     * This is called by the view to tell the view-model something has
     * changed.
     */
    public void refresh() {
        Log.d("app", "refresh called");
        // TODO: MOCK THE DATA, GET THIS FROM A BACKEND

        MenuCategory category1 = new MenuCategory("1", "Starters", "Great way to start your meal by sharing some delicious appetizers.", "");
        MenuCategory category2 = new MenuCategory("2", "Soups & Salads", "Farmed fresh every day.", "");
        MenuCategory category3 = new MenuCategory("3", "Entrees", "Large selection of rich flavored entrees.  Served with two sides.", "");
        MenuCategory category4 = new MenuCategory("4", "Beverages", "Hot or Cold, there is nothing better to quench a thirst", "");
        MenuCategory category5 = new MenuCategory("4", "Desserts", "All desserts are made by our award winning chefs", "");
        MenuCategory category6 = new MenuCategory("4", "Kids", "For guest under 10 and under.  All kids meals come with a side and a drink", "");
        ArrayList<MenuCategory> arr = new ArrayList<>();
        arr.add(category1);
        arr.add(category2);
        arr.add(category3);
        arr.add(category4);
        arr.add(category5);
        arr.add(category6);

        // Let our watchers know somethings changed, and remove the error and spinner
        menuCategoryList.setValue(arr);
        menuCategoryLoadError.setValue(false);
        menuCategoryLoading.setValue(false);
    }

}
