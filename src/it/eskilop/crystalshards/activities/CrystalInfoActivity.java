package it.eskilop.crystalshards.activities;

/*
     Copyright (C) 2016 The Crystal Rom Project
     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at
          http://www.apache.org/licenses/LICENSE-2.0
     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
*/

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import it.eskilop.crystalshards.R;

public class CrystalInfoActivity extends PreferenceActivity
  {


    private static final String KEY_CRYSTAL_VERSION = "crystal_version";
    private static final String KEY_CRYSTAL_CODENAME = "crystal_codename";
    private static final String KEY_CRYSTAL_BRANCH = "crystal_branch";
    private static final String KEY_CRYSTAL_API = "crystal_api";
    private static final String KEY_CRYSTAL_FLAVOUR = "crystal_flavour";

    long[] mHits = new long[3];
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.crystal_info_prefs);

        findPreference(KEY_CRYSTAL_VERSION).setSummary(Build.CRYSTAL.VERSION);
        findPreference(KEY_CRYSTAL_VERSION).setEnabled(true);
        findPreference(KEY_CRYSTAL_VERSION).setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
          {

            @Override
            public boolean onPreferenceClick(Preference preference)
              {
                if (i % 2 == 0)
                  {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    //intent.setClassName("android", com.android.internal.app.CrystalEggActivity.class.getName());
                    try
                      {
                        startActivity(intent);
                      }
                    catch (Exception e)
                      {
                        Log.e("CrystalInfoActivity", "Unable to start activity " + intent.toString());
                      }
                  } else
                  {
                    Toast.makeText(getApplicationContext(), "¯\\_(ツ)_/¯", Toast.LENGTH_SHORT).show();
                  }
                i++;
                return true;
              }
          });

        findPreference(KEY_CRYSTAL_CODENAME).setSummary(Build.CRYSTAL.CODENAME);
        findPreference(KEY_CRYSTAL_CODENAME).setEnabled(true);
        findPreference(KEY_CRYSTAL_BRANCH).setSummary(Build.CRYSTAL.BRANCH);
        findPreference(KEY_CRYSTAL_BRANCH).setEnabled(true);
        findPreference(KEY_CRYSTAL_API).setSummary(Build.CRYSTAL.API_LEVEL);
        findPreference(KEY_CRYSTAL_API).setEnabled(true);
        findPreference(KEY_CRYSTAL_FLAVOUR).setSummary(Build.CRYSTAL.FLAVOUR);
        findPreference(KEY_CRYSTAL_FLAVOUR).setEnabled(true);


      }

  }
