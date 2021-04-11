package com.mictay.snhu.it633.acerestaurantapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mictay.snhu.it633.acerestaurantapp.data.Data;
import com.mictay.snhu.it633.acerestaurantapp.model.CartItem;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;
import com.mictay.snhu.it633.acerestaurantapp.view.cart.CartData;

import java.util.ArrayList;
import java.util.List;

/************************************************************************************
 * We could tie more than 1 model to this View-Model, but in practice this is
 * discouraged.  We will keep with the 1 model to 1 view-model
 *
 */
public class CartListViewModel extends AndroidViewModel {

    // Mutable Live data means we can change the variable when needed
    public MutableLiveData<List<CartItem>> cartItemList = new MutableLiveData<List<CartItem>>();
    public MutableLiveData<Boolean> cartItemLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> cartItemLoading = new MutableLiveData<Boolean>();

    /*******************************************************************
     * Constructor
     *
     * @param application
     */
    public CartListViewModel(@NonNull Application application) {
        super(application);
    }

    /********************************************************************
     * This is called by the view to tell the view-model something has
     * changed.
     */
    public void refresh() {
        Log.d("app", "refresh called");

        // TODO: MOCK THE DATA, GET THIS FROM A BACKEND
        ArrayList<CartItem> arr = CartData.fetch();

        // Let our watchers know somethings changed, and remove the error and spinner
        cartItemList.setValue(arr);
        cartItemLoadError.setValue(false);
        cartItemLoading.setValue(false);
    }

}
