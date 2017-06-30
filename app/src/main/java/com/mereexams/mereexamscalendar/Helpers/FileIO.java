package com.mereexams.mereexamscalendar.Helpers;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Bilal on 30-Jun-17.
 */

public class FileIO {

    Context context;
    HashMap<String, String> vars;

    private final static String TAG = "FileIO";

    public FileIO(Context context) {
        this.context = context;
        vars = null;
    }

    public HashMap<String, String> getVars() {
        try {
            InputStream inputStream = context.getAssets().open("vars.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            vars = new HashMap<>();
            String line;
            while((line = br.readLine()) != null) {
                String[] keyValue = line.split(": ");
                vars.put(keyValue[0], keyValue[1]);
            }
            inputStream.close();

            // TODO: remove this after testing
            Set<String> keys = vars.keySet();
            for(String i : keys){
                Log.d(TAG, i + " : " + vars.get(i));
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return vars;
    }
}
