package com.coolprimes.f1numbers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String LOG_TAG = "f1numbers.MainActivity";
    private TextView serverResponse;
    private String jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Email not supported yet!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        Button btnGetDrivers = (Button) findViewById(R.id.btnGetDrivers);
        if (btnGetDrivers != null) {
            btnGetDrivers.setOnClickListener(this);
        }
        serverResponse = (TextView) findViewById(R.id.textViewServerResponse);
        JwtProvider jwtProvider = new JwtProvider("leyndarmal");
        jwt = jwtProvider.getJwtString("heimir");
        Log.d(LOG_TAG,"JWT: " + jwt);
    }

    @Override
    public void onClick(View v) {
        RetrofitREST retrofitREST = new RetrofitREST(serverResponse);
        switch (v.getId()) {
            case R.id.btnGetDrivers:
                retrofitREST.getDriver(String.format("Bearer %s", jwt), 2);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
