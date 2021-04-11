package com.mictay.snhu.it633.acerestaurantapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.data.Data;
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
        ArrayList<MenuCategory> arr = Data.getMenuCategoryList();

        // Let our watchers know somethings changed, and remove the error and spinner
        menuCategoryList.setValue(arr);
        menuCategoryLoadError.setValue(false);
        menuCategoryLoading.setValue(false);
    }

}
