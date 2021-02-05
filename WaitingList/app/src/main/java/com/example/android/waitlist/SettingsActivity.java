package com.example.android.waitlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent intent=new Intent(SettingsActivity.this,MainActivity.class);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            String colorpref=sharedPreferences.getString("pref_color_key","");
            intent.putExtra("color",colorpref);
            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}
