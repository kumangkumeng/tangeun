package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class HomeView extends Activity {

    TextView captionNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home);
        captionNama = (TextView) findViewById(R.id.captionNama);
        captionNama.setText(ParseUser.getCurrentUser().getString("fullname"));

        Button btnHutangFromMe = (Button)findViewById(R.id.btnHutangFromMe);
        btnHutangFromMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i = new Intent(HomeView.this, HutangFromMeView.class);
            startActivity(i);
            }
        });

        Button btnHutangToMe = (Button)findViewById(R.id.btnHutangToMe);
        btnHutangToMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeView.this, HutangToMeView.class);
                startActivity(i);
            }
        });

        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {
                        Intent i = new Intent(HomeView.this, LoginView.class);
                        startActivity(i);
                        finish();
                    }
                });
            }
        });
    }
}
