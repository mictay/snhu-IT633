package com.mictay.snhu.it633.acerestaurantapp.view.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mictay.snhu.it633.acerestaurantapp.R;
import com.mictay.snhu.it633.acerestaurantapp.view.cart.CartData;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/******************************************************************************
 *
 */
public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;

    public NavController getNavController() {
        return navController;
    }

    /**************************************************************************
     * LifeCycle Method
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.cartFragment, R.id.menuCategoryList)
                .setOpenableLayout(drawer)
                .build();

        // https://www.youtube.com/watch?v=3xb4t51sJZ0&t=1836s
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    /**************************************************************************
     * LifeCycle Method
     *
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**************************************************************************
     *
     */
    public void updateCartTotal() {
        View cartView = findViewById(R.id.cart_view_layout);
        TextView cartTotalText = findViewById(R.id.cart_view_total);
        String total = CartData.getTotal();

        Button checkout = findViewById(R.id.cart_view_check_out);

        if (total.equalsIgnoreCase("$0.00"))
            checkout.setVisibility(View.GONE);
        else {
            checkout.setVisibility(View.VISIBLE);
            checkout.setOnClickListener(v -> Toast.makeText(getApplicationContext(), "Not Implemented", Toast.LENGTH_LONG).show());
        }

        cartTotalText.setText(total);
        cartView.setVisibility(View.VISIBLE);
    }

    /**************************************************************************
     *
     */
    public boolean isCartVisible() {
        View cartView = findViewById(R.id.cart_view_layout);
        return (cartView.getVisibility() == View.VISIBLE);
    }

}