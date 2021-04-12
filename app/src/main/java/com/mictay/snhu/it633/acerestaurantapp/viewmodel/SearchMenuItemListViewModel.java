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
public class SearchMenuItemListViewModel extends AndroidViewModel {

    // Mutable Live data means we can change the variable when needed
    public MutableLiveData<List<MenuItem>> searchMenuItemList = new MutableLiveData<List<MenuItem>>();
    public MutableLiveData<Boolean> searchMenuItemLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> searchMenuItemLoading = new MutableLiveData<Boolean>();

    /*******************************************************************
     * Constructor
     *
     * @param application
     */
    public SearchMenuItemListViewModel(@NonNull Application application) {
        super(application);
    }

    /********************************************************************
     * This is called by the view to tell the view-model something has
     * changed.
     */
    public void refresh(String searchParam) {
        Log.d("app", "refresh called");

        // TODO: MOCK THE DATA, GET THIS FROM A BACKEND
        ArrayList<MenuItem> arr = Data.getSearchListResults(searchParam);

        // Let our watchers know somethings changed, and remove the error and spinner
        searchMenuItemList.setValue(arr);
        searchMenuItemLoadError.setValue(false);
        searchMenuItemLoading.setValue(false);
    }

    /***********************************************************************
     *
     */
    public void refreshFromRemote(String searchParam) {
        Log.d("app", "refreshFromRemote called");
        //TODO: cache busting, go get from the real system of record
        refresh(searchParam);
    }

}
