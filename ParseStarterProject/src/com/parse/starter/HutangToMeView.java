package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

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
public class HutangToMeView extends Activity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hutangfromme);
        listView = (ListView) findViewById(R.id.listView);
        final HutangAdapter hutangAdapter = new HutangAdapter(getLayoutInflater());
        hutangAdapter.setMode(2);
        hutangAdapter.setHutangs(new ArrayList<ParseObject>());
        listView.setAdapter(hutangAdapter);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Hutang");
        query.include("yangDipinjam");
        query.whereEqualTo("yangMeminjam", ParseUser.getCurrentUser());
        Tools.alert(this, "Loading");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e != null) {
                    e.printStackTrace();
                }

                if (parseObjects.size() == 0) {
                    Tools.alert(HutangToMeView.this, "Tidak ada hutang");
                }

                Tools.alert(HutangToMeView.this, "Loading Selesai");
                hutangAdapter.setHutangs(parseObjects);
            }
        });
    }
}
