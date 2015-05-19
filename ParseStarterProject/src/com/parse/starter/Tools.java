package com.parse.starter;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by rahadiankumang on 5/19/15.
 */
public class Tools {

    public static void alert(Context c, String message) {
        Toast.makeText(c, message, Toast.LENGTH_LONG).show();
    }
}
