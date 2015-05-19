package com.parse.starter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class HutangFromMeView extends Activity {

    ListView listView;
    HutangAdapter hutangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hutangfromme);
        listView = (ListView) findViewById(R.id.listView);
        hutangAdapter = new HutangAdapter(getLayoutInflater());
        hutangAdapter.setHutangs(new ArrayList<ParseObject>());
        listView.setAdapter(hutangAdapter);

        loadDataHutang();
    }

    public void loadDataHutang() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hutang");
        query.include("yangMeminjam");
        query.whereEqualTo("yangDipinjam", ParseUser.getCurrentUser());
        Tools.alert(this, "Loading");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Tools.alert(HutangFromMeView.this, "Loading Selesai");
                hutangAdapter.setHutangs(parseObjects);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add("Hutang Baru");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getTitle().equals("Hutang Baru")) {
            Intent i = new Intent(this, HutangBaru.class);
            startActivityForResult(i, 1);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                loadDataHutang();
            }
        }
    }
}
