package com.coolprimes.f1numbers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DriverListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list);
        final ListView listView = (ListView) findViewById(R.id.driver_list_view);
        Bundle b = getIntent().getExtras();
        final TeamDriver[] teamDrivers = new Gson().fromJson(
                b.getString("teamDrivers"), TeamDriver[].class);
        final DriverListAdapter adapter = new DriverListAdapter(
                this, android.R.layout.simple_list_item_1,
                new ArrayList<TeamDriver>(Arrays.asList(teamDrivers)));
        listView.setAdapter(adapter);
    }

    private class DriverListAdapter extends ArrayAdapter<TeamDriver> {
        private HashMap<Integer, String> driverNameMap = new HashMap<>();
        private HashMap<Integer, String> countryMap = new HashMap<>();
        private LayoutInflater inflater;

        public DriverListAdapter(Context context, int textViewResourceId,
                                 ArrayList<TeamDriver> teamDrivers) {
            super(context, textViewResourceId, teamDrivers);
            for (int i = 0; i < teamDrivers.size(); ++i) {
                String driverName = String.format("%s %s, %s", teamDrivers.get(i).getFirstName(),
                        teamDrivers.get(i).getLastName(), teamDrivers.get(i).getLastTeam());
                driverNameMap.put(i, driverName);
                countryMap.put(i, teamDrivers.get(i).getIso2code());
            }
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            View rowView =inflater.inflate(R.layout.driver_list_item, null);
            TextView driverNameView = (TextView) rowView.findViewById(R.id.driverNameView);
            ImageView flagImageView = (ImageView) rowView.findViewById(R.id.flagImageView);
            driverNameView.setText(driverNameMap.get(position));
            String imageUrl = String.format("https://coolprimes.com/f1/flags/%s.png",
                    countryMap.get(position));
            Picasso.with(DriverListActivity.this)
                    .load(imageUrl)
                    .into(flagImageView);
            rowView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v){
                       Toast.makeText(DriverListActivity.this, driverNameMap.get(position), Toast.LENGTH_SHORT).show();
                   }
               }
            );
            return rowView;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
