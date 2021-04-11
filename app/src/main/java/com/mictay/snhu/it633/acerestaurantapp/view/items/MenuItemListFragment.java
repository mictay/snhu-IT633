package com.mictay.snhu.it633.acerestaurantapp.view.items;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.databinding.FragmentMenuItemListBinding;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.MenuItemListViewModel;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 *
 */
public class MenuItemListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TextView errorTextView;
    private ProgressBar processingProgressBar;

    private FragmentMenuItemListBinding binding;
    private MenuItemListViewModel viewModel;
    private MenuItemListAdapter menuItemListAdapter;

    private String specificCategoryId = null;

    /*************************************************************
     *
     */
    public static MenuItemListFragment newInstance() {
        MenuItemListFragment fragment = new MenuItemListFragment();
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
        binding = FragmentMenuItemListBinding.inflate(getLayoutInflater());
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

        String title = "Menu Items";
        String categoryId = "";

        if (getArguments() != null && getArguments().getString("categoryName") != null)
            title = getArguments().getString("categoryName");

        if (getArguments() != null && getArguments().getString("categoryId") != null)
            categoryId = getArguments().getString("categoryId");

        // change the toolbar title to reflect the category
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        // Log the Parameters from the Menu Category List
        if (getArguments() != null) {
            Log.d("app", getArguments().getString("categoryId"));
            Log.d("app", getArguments().getString("categoryName"));
        }

        recyclerView = binding.recyclerItemList;
        errorTextView = binding.recyclerItemListError;
        processingProgressBar = binding.recyclerItemListLoading;

        // Gives us a List in a Linear fashion
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Create the Adapter
        menuItemListAdapter = new MenuItemListAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(menuItemListAdapter);

        // Getting the View Model
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MenuItemListViewModel.class); //TODO:HERE

        viewModel.refresh(categoryId);

        // Set up the observer to populate the viewModel
        observeViewModel();
    }

    /*************************************************************
     *
     */
    private void observeViewModel() {

        // The mutable changes will be felt here in the observe method
        viewModel.menuItemList.observe(getViewLifecycleOwner(), list -> {
            Log.d("app", "observeViewModel observe called");

            if (list != null &&  list instanceof List) {
                Log.d("app", "menu category list contains " + list.size());

                menuItemListAdapter.updateList(list);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        // Turn on/off Error Message
        viewModel.menuItemLoadError.observe(getViewLifecycleOwner(), isError -> {

            if (isError != null && isError instanceof Boolean) {
                Log.d("app", "menu category error is " + isError);
                errorTextView.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        // Turn on/off Loading circle
        viewModel.menuItemLoading.observe(getViewLifecycleOwner(), isLoading -> {
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