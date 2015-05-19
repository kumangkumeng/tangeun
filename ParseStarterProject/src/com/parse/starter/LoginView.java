package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class LoginView extends Activity {

    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);

        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtUsername = (EditText) findViewById(R.id.txtUsername);

        Button btnDaftar = (Button)findViewById(R.id.btn_daftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginView.this, DaftarView.class);
                startActivity(i);
            }
        });

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        String username = txtUsername.getEditableText().toString();
        String password = txtPassword.getEditableText().toString();

        if (username.equalsIgnoreCase("")) {
            Tools.alert(LoginView.this, "Username kosong");
            return;
        }

        if (password.equalsIgnoreCase("")) {
            Tools.alert(LoginView.this, "Password kosong");
            return;
        }

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                Intent i = new Intent(LoginView.this, HomeView.class);
                startActivity(i);
            }
        });
    }
}
