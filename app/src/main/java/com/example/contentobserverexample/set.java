package com.example.contentobserverexample;

/**
 * Created by Suyono on 9/14/2015.
 * Copyright (c) 2015 by Suyono (ion).
 * All rights reserved.
 * This product is protected by copyright and distributed under
 * licenses restricting copying, distribution and decompilation.
 */

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.text.TextUtils;

@SuppressWarnings("ALL")
public class set extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{

    public int tujuanFolderRes(String NamaFile, String NamaFolder)
    {
        return getBaseContext().getResources().getIdentifier(NamaFile, NamaFolder, getBaseContext().getPackageName());
    }

    protected void onCreate(Bundle paramBundle)
    {
        super.onCreate(paramBundle);
        addPreferencesFromResource(tujuanFolderRes("pref", "xml"));
        //edit
        EditTextPreference edit_pref = (EditTextPreference)findPreference("gantiText");
        String isi_teks = Settings.System.getString(getContentResolver(), "example_observer_string");
        if(!TextUtils.isEmpty(isi_teks)){
            edit_pref.setSummary(isi_teks);
        }
        //edit
    }

    @Override
    public void onResume(){
        super.onResume();

        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences options, String key) {

        //tam
        if (key.equals("gantiText")){
            Preference pref_judul1= findPreference(key);
            String isikanJudul1 = "";
            if(pref_judul1 instanceof EditTextPreference){
                isikanJudul1 = ((EditTextPreference)pref_judul1).getText();
            }
            pref_judul1.setSummary(isikanJudul1);
            Settings.System.putString(getContentResolver(), "example_observer_string", isikanJudul1);
        }
        //tam
    }

}
