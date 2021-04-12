package com.mictay.snhu.it633.acerestaurantapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mictay.snhu.it633.acerestaurantapp.data.Data;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;

import java.util.ArrayList;
import java.util.List;

/************************************************************************************
 * We could tie more than 1 model to this View-Model, but in practice this is
 * discouraged.  We will keep with the 1 model to 1 view-model
 *
 */
public class MenuItemListViewModel extends AndroidViewModel {

    // Mutable Live data means we can change the variable when needed
    public MutableLiveData<List<MenuItem>> menuItemList = new MutableLiveData<List<MenuItem>>();
    public MutableLiveData<Boolean> menuItemLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> menuItemLoading = new MutableLiveData<Boolean>();

    /*******************************************************************
     * Constructor
     *
     * @param application
     */
    public MenuItemListViewModel(@NonNull Application application) {
        super(application);
    }

    /********************************************************************
     * This is called by the view to tell the view-model something has
     * changed.
     */
    public void refresh(String categoryId) {
        Log.d("app", "refresh called");

        // TODO: MOCK THE DATA, GET THIS FROM A BACKEND
        ArrayList<MenuItem> arr = Data.getMenuItemList(categoryId);

        // Let our watchers know somethings changed, and remove the error and spinner
        menuItemList.setValue(arr);
        menuItemLoadError.setValue(false);
        menuItemLoading.setValue(false);
    }

    /***********************************************************************
     *
     */
    public void refreshFromRemote(String categoryId) {
        Log.d("app", "refreshFromRemote called");
        //TODO: cache busting, go get from the real system of record
        refresh(categoryId);
    }

}
