package com.mictay.snhu.it633.acerestaurantapp.view.items;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.data.Data;
import com.mictay.snhu.it633.acerestaurantapp.databinding.FragmentMenuItemDetailBinding;
import com.mictay.snhu.it633.acerestaurantapp.model.MenuItem;
import com.mictay.snhu.it633.acerestaurantapp.util.AceUtils;
import com.mictay.snhu.it633.acerestaurantapp.view.cart.CartData;
import com.mictay.snhu.it633.acerestaurantapp.view.home.HomeActivity;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.MenuItemDetailViewModel;

/******************************************************************************
 *
 */
public class MenuItemDetailFragment extends Fragment {

    private String menuItemId;
    private MenuItemDetailViewModel viewModel;
    private FragmentMenuItemDetailBinding binding;
    private int quantity = 1;

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

        // SEARCH BUTTON NAVIGATION
        Button searchButton = getActivity().findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_menuItemDetailFragment_to_searchFragment, null);
        });

        // CART BUTTON NAVIGATION
        View cartView = getActivity().findViewById(R.id.cart_view_layout);
        cartView.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_menuItemDetailFragment_to_cartFragment, null);
        });

        // change the toolbar title to reflect the category
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(Data.getRandomDelightTitle() );

        // Grab the inbound values
        menuItemId = "";

        if (getArguments() != null && getArguments().getString("menuItemId") != null)
            menuItemId = getArguments().getString("menuItemId");

        if (getArguments() != null && getArguments().get("quantity") != null)
            quantity = getArguments().getInt("quantity");

        // Populate the Quantity Spinner and set its listener
        ArrayAdapter<String> aa = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, Data.getQuantitySpinnerValues());
        binding.menuItemDetailQuantitySpinner.setAdapter(aa);

        // Can we prepopulate the quantity in the Spinner?
        binding.menuItemDetailQuantitySpinner.setSelection(quantity - 1);

        binding.menuItemDetailQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quantity = Integer.valueOf( Data.getQuantitySpinnerValues()[position] );
                Log.d("app", "quantity selected " + quantity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        // setting the Add Button Listener
        binding.menuItemAddButton.setOnClickListener(v -> {
            CartData.addMenuItem(menuItemId, quantity);
            ((HomeActivity)getActivity()).updateCartTotal();
        });

        // Getting the View Model
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(MenuItemDetailViewModel.class);
        viewModel.fetch(menuItemId);

        observeViewModel();
    }

    /**************************************************************************
     *
     */
    private void observeViewModel() {
        viewModel.menuItemLiveData.observe(getViewLifecycleOwner(), menuItem -> {

            // Populate all the information
            if (menuItem != null && menuItem instanceof MenuItem) {
                binding.menuItemDetailName.setText(menuItem.itemName);
                binding.menuItemDetailDescription.setText(menuItem.itemDescription);
                binding.menuItemDetailPrice.setText(AceUtils.formatCurrency(menuItem.itemPrice));
                binding.menuItemDetailImageView.setImageResource( Integer.valueOf(menuItem.imageUrl) );
            }

        });
    }
}