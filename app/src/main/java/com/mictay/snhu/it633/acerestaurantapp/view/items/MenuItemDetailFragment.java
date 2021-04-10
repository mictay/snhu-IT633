package com.mictay.snhu.it633.acerestaurantapp.view.items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mictay.snhu.it633.acerestaurantapp.databinding.FragmentMenuItemDetailBinding;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.MenuItemDetailViewModel;

/******************************************************************************
 *
 */
public class MenuItemDetailFragment extends Fragment {

    private int menuItemUuid;
    private MenuItemDetailViewModel viewModel;
    private FragmentMenuItemDetailBinding binding;

    /*********************************************************************
     * Constructor
     */
    public MenuItemDetailFragment() {
        // Required empty public constructor
    }

    /*********************************************************************
     * Method Lifecycle
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*********************************************************************
     * Method Lifecycle
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMenuItemDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    /*********************************************************************
     * Method Lifecycle
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Getting the View Model
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MenuItemDetailViewModel.class);
        viewModel.fetch();

        observeViewModel();
    }

    /**************************************************************************
     *
     */
    private void observeViewModel() {
        viewModel.menuItemLiveData.observe(getViewLifecycleOwner(), menuItem -> {

            // Populate all the information
            if (menuItem != null && menuItem instanceof MenuItem) {
                binding.menuItemDetailName.setText("hello world name");
                binding.menuItemDetailDescription.setText("hello again description");
            }

        });
    }
}