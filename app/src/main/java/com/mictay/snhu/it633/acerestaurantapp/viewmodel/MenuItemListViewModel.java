package com.mictay.snhu.it633.acerestaurantapp.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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
    public void refresh() {
        Log.d("app", "refresh called");
        // TODO: MOCK THE DATA, GET THIS FROM A BACKEND

        MenuItem obj0 = new MenuItem("0", "item 0", "$4.99", "1", "description here 0", "");
        MenuItem obj1 = new MenuItem("1", "item 1", "$5.99", "1", "description here 1", "");
        MenuItem obj2 = new MenuItem("2", "item 2", "$6.99", "1", "description here 2", "");
        MenuItem obj3 = new MenuItem("3", "item 3", "$7.99", "1", "description here 3", "");
        MenuItem obj4 = new MenuItem("4", "item 4", "$8.99", "1", "description here 4", "");
        MenuItem obj5 = new MenuItem("5", "item 5", "$9.99", "1", "description here 5", "");
        MenuItem obj6 = new MenuItem("6", "item 6", "$10.99", "1", "description here 6", "");
        MenuItem obj7 = new MenuItem("7", "item 7", "$11.99", "1", "description here 7", "");
        MenuItem obj8 = new MenuItem("8", "item 8", "$12.99", "1", "description here 8", "");
        MenuItem obj9 = new MenuItem("9", "item 9", "$13.99", "1", "description here 9", "");

        MenuItem obj10 = new MenuItem("10", "item 10", "$4.99", "1", "description here 10", "");
        MenuItem obj11 = new MenuItem("11", "item 11", "$5.99", "1", "description here 11", "");
        MenuItem obj12 = new MenuItem("12", "item 12", "$6.99", "1", "description here 12", "");
        MenuItem obj13 = new MenuItem("13", "item 13", "$7.99", "1", "description here 13", "");
        MenuItem obj14 = new MenuItem("14", "item 14", "$8.99", "1", "description here 14", "");
        MenuItem obj15 = new MenuItem("15", "item 15", "$9.99", "1", "description here 15", "");
        MenuItem obj16 = new MenuItem("16", "item 16", "$10.99", "1", "description here 16", "");
        MenuItem obj17 = new MenuItem("17", "item 17", "$11.99", "1", "description here 17", "");
        MenuItem obj18 = new MenuItem("18", "item 18", "$12.99", "1", "description here 18", "");
        MenuItem obj19 = new MenuItem("19", "item 19", "$13.99", "1", "description here 19", "");

        MenuItem obj20 = new MenuItem("10", "item 20", "$4.99", "1", "description here 10", "");
        MenuItem obj21 = new MenuItem("11", "item 21", "$5.99", "1", "description here 11", "");
        MenuItem obj22 = new MenuItem("12", "item 22", "$6.99", "1", "description here 12", "");
        MenuItem obj23 = new MenuItem("13", "item 23", "$7.99", "1", "description here 13", "");
        MenuItem obj24 = new MenuItem("14", "item 24", "$8.99", "1", "description here 14", "");
        MenuItem obj25 = new MenuItem("15", "item 25", "$9.99", "1", "description here 15", "");
        MenuItem obj26 = new MenuItem("16", "item 26", "$10.99", "1", "description here 16", "");
        MenuItem obj27 = new MenuItem("17", "item 27", "$11.99", "1", "description here 17", "");
        MenuItem obj28 = new MenuItem("18", "item 28", "$12.99", "1", "description here 18", "");
        MenuItem obj29 = new MenuItem("19", "item 29", "$13.99", "1", "description here 19", "");


        ArrayList<MenuItem> arr = new ArrayList<>();
        arr.add(obj0);
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);
        arr.add(obj4);
        arr.add(obj5);
        arr.add(obj6);
        arr.add(obj7);
        arr.add(obj8);
        arr.add(obj9);

        arr.add(obj10);
        arr.add(obj11);
        arr.add(obj12);
        arr.add(obj13);
        arr.add(obj14);
        arr.add(obj15);
        arr.add(obj16);
        arr.add(obj17);
        arr.add(obj18);
        arr.add(obj19);

        arr.add(obj20);
        arr.add(obj21);
        arr.add(obj22);
        arr.add(obj23);
        arr.add(obj24);
        arr.add(obj25);
        arr.add(obj26);
        arr.add(obj27);
        arr.add(obj28);
        arr.add(obj29);

        // Let our watchers know somethings changed, and remove the error and spinner
        menuItemList.setValue(arr);
        menuItemLoadError.setValue(false);
        menuItemLoading.setValue(false);
    }

}
