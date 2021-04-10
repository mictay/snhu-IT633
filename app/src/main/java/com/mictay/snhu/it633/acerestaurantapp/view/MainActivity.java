package com.mictay.snhu.it633.acerestaurantapp.view;

import androidx.appcompat.app.AppCompatActivity;
import com.mictay.snhu.it633.acerestaurantapp.databinding.ActivityMainBinding;
import com.mictay.snhu.it633.acerestaurantapp.view.home.HomeActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/****************************************************************
 *
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    /******************************************************
     * Activity LifeCycle
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // Set up our Button click events  (we only have one on this activity)
        binding.buttonSplashSignin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("app", "view selected" + view.getId());
                gotoHome();
            }

        });

    }

    /************************************************
     *  There JetPack has Navigation but I'm not sure
     *  how to implement it going from Activity to Activity
     *  For now, lets just use the Intent
     */
    public void gotoHome() {
        Intent gotoHome = new Intent(this, HomeActivity.class);
        startActivity(gotoHome);
        finish();  // prevent the device back button coming back here.
    }

}