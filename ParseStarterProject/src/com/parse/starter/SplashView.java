package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.ParseUser;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class SplashView extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseUser currentUser = ParseUser.getCurrentUser();

        if (currentUser.getSessionToken() == null) {
            Intent i = new Intent(this, LoginView.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(this, HomeView.class);
            startActivity(i);
            Toast.makeText(this, "Halo " + currentUser.get("fullname"), Toast.LENGTH_SHORT).show();
        }
    }
}
