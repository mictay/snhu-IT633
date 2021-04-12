package com.mictay.snhu.it633.acerestaurantapp.view.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.data.Data;
import com.mictay.snhu.it633.acerestaurantapp.databinding.FragmentSearchMenuItemListBinding;
import com.mictay.snhu.it633.acerestaurantapp.view.home.HomeActivity;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.MenuItemListViewModel;
import com.mictay.snhu.it633.acerestaurantapp.viewmodel.SearchMenuItemListViewModel;

import java.util.ArrayList;
import java.util.List;

/******************************************************************************
 *
 *
 */
public class SearchMenuItemListFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView errorTextView;
    private ProgressBar processingProgressBar;

    private FragmentSearchMenuItemListBinding binding;
    private SearchMenuItemListViewModel viewModel;
    private SearchMenuItemListAdapter searchMenuItemListAdapter;
    private EditText searchEditText;
    private Button searchButton;
    private Space spacer;

    /*************************************************************
     *
     */
    public static SearchMenuItemListFragment newInstance() {
        SearchMenuItemListFragment fragment = new SearchMenuItemListFragment();
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
        binding = FragmentSearchMenuItemListBinding.inflate(getLayoutInflater());
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

        // SEARCH BUTTON NAVIGATION
        Button searchButton = getActivity().findViewById(R.id.search_button);
        searchButton.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_searchFragment_self, null);
        });

        // CART BUTTON NAVIGATION
        View cartView = getActivity().findViewById(R.id.cart_view_layout);
        cartView.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_searchFragment_to_cartFragment, null);
        });

        String title = "Find Tasty Food";

        // change the toolbar title to reflect the category
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle(title);

        // Log the Parameters from the Menu Category List
        if (getArguments() != null) {
            Log.d("app", getArguments().getString("searchParams"));
            Log.d("app", getArguments().getString("searchParams"));
        }

        swipeRefreshLayout = binding.refreshMenuItemListLayout;
        recyclerView = binding.recyclerSearchMenuItemList;
        errorTextView = binding.recyclerSearchMenuItemListError;
        processingProgressBar = binding.recyclerSearchMenuItemListLoading;
        searchEditText = binding.searchMenuItemQueryText;
        searchButton = binding.searchMenuItemQueryButton;
        spacer = binding.recyclerSearchMenuItemListSpacer;

        // Setup Force Refresh request
        swipeRefreshLayout.setOnRefreshListener(() -> {
            recyclerView.setVisibility(View.GONE);
            errorTextView.setVisibility(View.GONE);
            processingProgressBar.setVisibility(View.VISIBLE);
            Data.lastSearchTerm = searchEditText.getText().toString();
            viewModel.refreshFromRemote(Data.lastSearchTerm);
            swipeRefreshLayout.setRefreshing(false);
        });

        // Remember searches
        searchEditText.setText(Data.lastSearchTerm);

        // Gives us a List in a Linear fashion
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Gives us a List in a Linear fashion
        if ( ((HomeActivity)getActivity()).isCartVisible() ) {
            spacer.setVisibility(View.VISIBLE);
        } else {
            spacer.setVisibility(View.GONE);
        }

        // Create the Adapter
        searchMenuItemListAdapter = new SearchMenuItemListAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(searchMenuItemListAdapter);

        // Getting the View Model
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(SearchMenuItemListViewModel.class); //TODO:HERE

        // Search Default (might be empty, might not, lets remember it anyhow)
        viewModel.refresh(Data.lastSearchTerm);

        // Setup that search button
        searchButton.setOnClickListener(v -> {
            Data.lastSearchTerm = searchEditText.getText().toString();

            Log.d("app", "searchButton clicked searching for " + Data.lastSearchTerm);

            viewModel.refresh(Data.lastSearchTerm);
        });


        // Set up the observer to populate the viewModel
        observeViewModel();
    }

    /*************************************************************
     *
     */
    private void observeViewModel() {

        // The mutable changes will be felt here in the observe method
        viewModel.searchMenuItemList.observe(getViewLifecycleOwner(), list -> {
            Log.d("app", "observeViewModel observe called");

            if (list != null &&  list instanceof List) {
                Log.d("app", "menu category list contains " + list.size());

                searchMenuItemListAdapter.updateList(list);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });

        // Turn on/off Error Message
        viewModel.searchMenuItemLoadError.observe(getViewLifecycleOwner(), isError -> {

            if (isError != null && isError instanceof Boolean) {
                Log.d("app", "menu category error is " + isError);
                errorTextView.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });

        // Turn on/off Loading circle
        viewModel.searchMenuItemLoading.observe(getViewLifecycleOwner(), isLoading -> {
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