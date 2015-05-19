package com.parse.starter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class HutangBaru extends Activity {

    EditText txtUsernamePeminjam, txtJumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.hutangbaru);
        txtUsernamePeminjam = (EditText) findViewById(R.id.txtUsernamePeminjam);
        txtJumlah = (EditText) findViewById(R.id.txtJulmlah);

        Button btnDone = (Button) findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selesai();
            }
        });
    }

    public void selesai() {
        String username = txtUsernamePeminjam.getEditableText().toString();
        final String jumlah = txtJumlah.getEditableText().toString();
        final Number jn = Integer.parseInt(jumlah);

        if (username.equalsIgnoreCase("")) {
            Tools.alert(this, "Username Peminjam Tidak Boleh Kosong");return;
        }

        if (jumlah.equalsIgnoreCase("")) {
            Tools.alert(this, "Jumlah Peminjaman Tidak Boleh Kosong");return;
        }

        ParseQuery<ParseUser> queryUser = ParseQuery.getQuery(ParseUser.class);
        queryUser.whereEqualTo("username", username);
        queryUser.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> parseUsers, ParseException e) {
                if (parseUsers.size() == 0) {
                    Tools.alert(HutangBaru.this, "Peminjam tidak ditemukan");
                } else {
                    ParseUser peminjam = parseUsers.get(0);
                    ParseObject hutang = ParseObject.create("Hutang");
                    hutang.put("yangDipinjam", ParseUser.getCurrentUser());
                    hutang.put("yangMeminjam", peminjam);
                    hutang.put("jumlah", jn);

                    ParseACL acl = new ParseACL(ParseUser.getCurrentUser());
                    acl.setPublicReadAccess(true);
                    acl.setPublicWriteAccess(true);

                    hutang.setACL(acl);

                    hutang.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                setResult(RESULT_OK);
                                finish();
                            } else {
                                Tools.alert(HutangBaru.this, "Penyimpanan Gagal");
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }
}
