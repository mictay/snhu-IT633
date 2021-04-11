package com.mictay.snhu.it633.acerestaurantapp.view.home;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mictay.snhu.it633.acerestaurantapp.R;

/*************************************************************************
 *
 */
public class HomeFragment extends Fragment {

    /*************************************************************************
     * LifeCycle Method
     */
    public HomeFragment() {
        // Required empty public constructor
    }

    /*************************************************************************
     * LifeCycle Method
     */
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    /*************************************************************************
     *  LifeCycle Method
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*************************************************************************
     * LifeCycle Method
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    /*************************************************************************
     * LifeCycle Method
     *
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
                    .navigate(R.id.action_homeFragment_to_searchFragment, null);
        });

        // CART BUTTON NAVIGATION
        View cartView = getActivity().findViewById(R.id.cart_view_layout);
        cartView.setOnClickListener(v -> {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment)
                    .navigate(R.id.action_homeFragment_to_cartFragment, null);
        });

    }
}