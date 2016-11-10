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
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.provider.Settings;

import it.eskilop.crystalshards.R;


public class PhysicalKeysModsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener
  {

    private static final String KEY_VOLUME_SWITCH_TRACK = "volume_switch_track";

    public PhysicalKeysModsFragment()
      {
        // Required empty public constructor
      }

    public static PhysicalKeysModsFragment newInstance()
      {
        return new PhysicalKeysModsFragment();
      }

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.crystal_mod_physical);

        SwitchPreference volume_switch_track = (SwitchPreference) findPreference(KEY_VOLUME_SWITCH_TRACK);
        volume_switch_track.setOnPreferenceChangeListener(this);

      }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o)
      {
        ContentResolver resolver = getActivity().getContentResolver();

        // Let's check what preference is it
        switch (preference.getKey())
          {
            case KEY_VOLUME_SWITCH_TRACK:
              //Settings.System.putString(resolver, Settings.System.CRYSTAL_PHYSICAL_VOLUME_SWITCH_TRACK, (int) o);
              break;
          }
        return false;
      }
  }
