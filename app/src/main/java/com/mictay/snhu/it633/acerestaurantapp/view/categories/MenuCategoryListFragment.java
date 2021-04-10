package com.mictay.snhu.it633.acerestaurantapp.view.categories;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.databinding.FragmentMenuCategoryListBinding;
import com.mictay.snhu.it633.acerestaurantapp.view.home.HomeActivity;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.MenuCategoryListViewModel;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 *
 */
public class MenuCategoryListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView errorTextView;
    private ProgressBar processingProgressBar;

    private FragmentMenuCategoryListBinding binding;
    private MenuCategoryListViewModel viewModel;
    private MenuCategoryListAdapter menuCategoryListAdapter;

    /*************************************************************
     *
     */
    public static MenuCategoryListFragment newInstance() {
        MenuCategoryListFragment fragment = new MenuCategoryListFragment();
        return fragment;
    }

    /*************************************************************
     *
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*************************************************************
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuCategoryListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    /*************************************************************
     *  This doesn't care how the viewModel gets populated ie:
     *  mock data, online data, or a local database,
     *  this fragment displays what is given.
     *
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("app", "onViewCreated called");

        // *********************************************
        // START: TEMP CODE TO WORK WITH SPECIFIC FRAGMENT

//        HomeActivity activity = (HomeActivity) getActivity();
//        //activity.getNavController().navigate(R.id.action_menuCategoryList_to_menuItemListFragment);
//        activity.getNavController().navigate(R.id.action_menuCategoryList_to_menuItemDetailFragment);
//
//        if (!!true)
//            return;

        // END: TEMP CODE TO WORK WITH SPECIFIC FRAGMENT
        // *********************************************

        recyclerView = binding.recyclerCategoryList;
        errorTextView = binding.recyclerCategoryListError;
        processingProgressBar = binding.recyclerCategoryListLoading;

        // Gives us a List in a Linear fashion
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create the Adapter
        menuCategoryListAdapter = new MenuCategoryListAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(menuCategoryListAdapter);

        // Getting the View Model
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MenuCategoryListViewModel.class); //TODO:HERE

        viewModel.refresh();

        // Set up the observer to populate the viewModel
        observeViewModel();
    }

    /*************************************************************
     *
     */
    private void observeViewModel() {

        // The mutable changes will be felt here in the observe method
        viewModel.menuCategoryList.observe(getViewLifecycleOwner(), list -> {
            Log.d("app", "observeViewModel observe called");

            if (list != null &&  list instanceof List) {
                Log.d("app", "menu category list contains " + list.size());

                menuCategoryListAdapter.updateList(list);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        // Turn on/off Error Message
        viewModel.menuCategoryLoadError.observe(getViewLifecycleOwner(), isError -> {

            if (isError != null && isError instanceof Boolean) {
                Log.d("app", "menu category error is " + isError);
                errorTextView.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        // Turn on/off Loading circle
        viewModel.menuCategoryLoading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null && isLoading instanceof Boolean) {
                Log.d("app", "menu category loading is " + isLoading);
                processingProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);

                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.GONE);
                }
            }
        });

    }

    /*************************************************************
     *
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}