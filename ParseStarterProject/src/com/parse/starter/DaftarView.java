package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class DaftarView extends Activity {

    EditText txtFullname, txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.register);
        Button btnDaftar = (Button)findViewById(R.id.btn_daftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        txtFullname = (EditText) findViewById(R.id.txtFullname);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    private void register() {
        final String fullname = txtFullname.getEditableText().toString();
        String username = txtUsername.getEditableText().toString();
        String password = txtPassword.getEditableText().toString();

        if (fullname.equalsIgnoreCase("")) {
            Tools.alert(this, "Isi nama dulu");
            return;
        }

        if (username.equalsIgnoreCase("")) {
            Tools.alert(this, "Isi username dulu");
            return;
        }

        if (password.equalsIgnoreCase("")) {
            Tools.alert(this, "Isi password dulu");
            return;
        }

        ParseUser parseUser = new ParseUser();
        parseUser.setUsername(username);
        parseUser.setPassword(password);
        parseUser.put("fullname", fullname);

        parseUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Tools.alert(DaftarView.this, "Halo " + fullname);
                    Intent i = new Intent(DaftarView.this, HomeView.class);
                    startActivity(i);
                } else {
                    e.printStackTrace();
                    Log.e("parse error", e.toString());
                    Tools.alert(DaftarView.this, "Gagal :(");
                }
            }
        });
    }
}
