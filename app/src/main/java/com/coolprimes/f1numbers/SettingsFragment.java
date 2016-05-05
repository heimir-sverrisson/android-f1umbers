package com.coolprimes.f1numbers;
/**
 * Created by Heimir Sverrisson on 04/05/2016.
 */
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // Load our preferences from XML
        addPreferencesFromResource(R.xml.preferences);
    }
}
