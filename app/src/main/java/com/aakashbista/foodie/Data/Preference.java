package com.aakashbista.foodie.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
private SharedPreferences sharedPreferences;
    public Preference(Context context){
        sharedPreferences=context.getSharedPreferences("Notes",Context.MODE_PRIVATE);

    }

    public String  getString(String id){
        return sharedPreferences.getString(id, "");
    }

    public void putString(String id, String value) {
            SharedPreferences.Editor e = sharedPreferences.edit();
        e.putString(id, value);
        e.apply();
    }

    public int getInt(String id){
        return sharedPreferences.getInt(id, -1);
    }

    public void putInt(String id, int value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putInt(id, value);
        e.apply();
    }

    public boolean getBool(String id){
        return sharedPreferences.getBoolean(id, false);
    }

    public void putBool(String id, Boolean value) {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.putBoolean(id, value);
        e.apply();
    }

    public void clearAll() {
        SharedPreferences.Editor e = sharedPreferences.edit();
        e.clear();
        e.apply();
    }
}
