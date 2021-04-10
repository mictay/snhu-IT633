package com.mictay.snhu.it633.acerestaurantapp.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}