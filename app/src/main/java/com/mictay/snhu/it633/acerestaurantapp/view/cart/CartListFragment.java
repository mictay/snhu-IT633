package com.mictay.snhu.it633.acerestaurantapp.view.cart;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.databinding.FragmentCartListBinding;
import com.mictay.snhu.it633.acerestaurantapp.view.home.HomeActivity;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.CartListViewModel;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 *
 */
public class CartListFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView errorTextView;
    private ProgressBar processingProgressBar;
    private LinearLayout noDataMessage;
    private Button recyclerCartListNoDataButton;
    private Space spacer;

    private FragmentCartListBinding binding;
    private CartListViewModel viewModel;
    private CartListAdapter cartListAdapter;

    /*************************************************************
     * Convenient method auto generated
     */
    public static CartListFragment newInstance() {
        CartListFragment fragment = new CartListFragment();
        return fragment;
    }

    /*************************************************************
     * LifeCycle method
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*************************************************************
     * LifeCycle method
     */
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartListBinding.inflate(getLayoutInflater());
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

        // SEARCH BUTTON NAVIGATION
        Button searchButton = getActivity().findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_cartFragment_to_searchFragment, null);
        });

        // CART BUTTON NAVIGATION
        View cartView = getActivity().findViewById(R.id.cart_view_layout);
        cartView.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_cartFragment_self, null);
        });

        swipeRefreshLayout = binding.refreshCartListLayout;
        recyclerView = binding.recyclerCartList;
        errorTextView = binding.recyclerCartListError;
        processingProgressBar = binding.recyclerCartListLoading;
        noDataMessage = binding.recyclerCartListNoData;
        recyclerCartListNoDataButton = binding.recyclerCartListNoDataButton;
        spacer = binding.recyclerCartListSpacer;

        // Setup Force Refresh request
        swipeRefreshLayout.setOnRefreshListener(() -> {
            recyclerView.setVisibility(View.GONE);
            errorTextView.setVisibility(View.GONE);
            processingProgressBar.setVisibility(View.VISIBLE);
            viewModel.refreshFromRemote();
            swipeRefreshLayout.setRefreshing(false);
        });

        // Set up the No Data return to Menu Button
        recyclerCartListNoDataButton.setOnClickListener(v -> {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_cartFragment_to_menuCategoryList, null);
        });

        // Gives us a List in a Linear fashion
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Gives us a List in a Linear fashion
        if ( ((HomeActivity)getActivity()).isCartVisible() ) {
            spacer.setVisibility(View.VISIBLE);
        } else {
            spacer.setVisibility(View.GONE);
        }

        // Create the Adapter
        cartListAdapter = new CartListAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(cartListAdapter);

        // Getting the View Model
        viewModel = new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(CartListViewModel.class); //TODO:HERE

        viewModel.refresh();

        // Set up the observer to populate the viewModel
        observeViewModel();
    }

    /*************************************************************
     *  Watch for changes to our list
     */
    /*************************************************************
     *
     */
    private void observeViewModel() {

        // The mutable changes will be felt here in the observe method
        viewModel.cartItemList.observe(getViewLifecycleOwner(), list -> {
            Log.d("app", "Cart observeViewModel observe called");

            if (list != null &&  list instanceof List) {
                Log.d("app", "Cart list contains " + list.size());
                    cartListAdapter.updateList(list);
                    recyclerView.setVisibility(View.VISIBLE);

                    if (list.size() == 0) {
                        noDataMessage.setVisibility( View.VISIBLE );
                    } else {
                        noDataMessage.setVisibility( View.GONE );
                    }

            }
        });

        // Turn on/off Error Message
        viewModel.cartItemLoadError.observe(getViewLifecycleOwner(), isError -> {

            if (isError != null && isError instanceof Boolean) {
                Log.d("app", "menu category error is " + isError);
                errorTextView.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        // Turn on/off Loading circle
        viewModel.cartItemLoading.observe(getViewLifecycleOwner(), isLoading -> {
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
     * Clean up our Object
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}