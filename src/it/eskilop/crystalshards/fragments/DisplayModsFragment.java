/*
 *    Copyright 2016 The Crystal Rom Project
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package it.eskilop.crystalshards.fragments;


import android.content.ContentResolver;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import java.lang.NullPointerException;
import android.provider.Settings;

import it.eskilop.crystalshards.R;

public class DisplayModsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener
  {

    private static final String KEY_SCREENSHOT_TYPE = "screenshot_type";

    public DisplayModsFragment()
      {
        // Required empty public constructor
      }

    public static DisplayModsFragment newInstance()
      {
        return new DisplayModsFragment();
      }

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.crystal_mod_display);

        ListPreference scrot_preference = (ListPreference) findPreference(KEY_SCREENSHOT_TYPE);
        scrot_preference.setOnPreferenceChangeListener(this);
        
        try
          {
            if (!Settings.System.getString(getActivity().getContentResolver(), Settings.System.CRYSTAL_DISPLAY_SCREENSHOT_TYPE).equals(null))
              {
                // Set summaries and values to the ones picked by the user
                switch (Settings.System.getString(getActivity().getContentResolver(), Settings.System.CRYSTAL_DISPLAY_SCREENSHOT_TYPE))
                  {
                    case "1":
                      scrot_preference.setSummary(getResources().getString(R.string.scrot_type_default_summary));
                      break;
                    case "2":
                      scrot_preference.setSummary(getResources().getString(R.string.scrot_type_partial_summary));
                      break;
                  }

              }
          } 
          catch (NullPointerException e)
            {
              scrot_preference.setSummary(getResources().getString(R.string.scrot_type_description));
            }
      }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o)
      {
        ContentResolver resolver = getActivity().getContentResolver();

        // Let's check what preference is it
        switch (preference.getKey())
          {
            case KEY_SCREENSHOT_TYPE:
              Settings.System.putString(resolver, Settings.System.CRYSTAL_DISPLAY_SCREENSHOT_TYPE, (String) o);
              switch ((String) o)
                {
                  case "1":
                    preference.setSummary(getResources().getString(R.string.scrot_type_default_summary));
                    break;
                  case "2":
                    preference.setSummary(getResources().getString(R.string.scrot_type_partial_summary));
                    break;
                }
              break;
          }
        return false;
      }
  }
