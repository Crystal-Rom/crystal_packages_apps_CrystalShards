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


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.telephony.TelephonyManager;

import it.eskilop.crystalshards.R;
import it.eskilop.crystalshards.utils.MD5;

public class StatisticInfosFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener
  {

    SharedPreferences sp = getActivity().getSharedPreferences("CrystalPrefs", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp.edit();

    TelephonyManager tm = (TelephonyManager) getActivity().getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    private String imei = tm.getDeviceId();

    // Keys
    private static final String KEY_STATS_SWITCH = "key_infos_stats_switch";
    private static final String KEY_CRYSTAL_UID = "key_infos_crystal_uid";
    private static final String KEY_CRYSTAL_DEVICE = "key_infos_crystal_device";
    private static final String KEY_CRYSTAL_VERSION = "key_infos_crystal_version";
    private static final String KEY_CRYSTAL_CODENAME = "key_infos_crystal_codename";
    private static final String KEY_CRYSTAL_BRANCH = "key_infos_crystal_branch";
    private static final String KEY_CRYSTAL_API_LEVEL = "key_infos_crystal_api_level";
    private static final String KEY_CRYSTAL_FLAVOUR = "key_infos_crystal_flavour";

    public StatisticInfosFragment()
      {
        // Required empty public constructor
      }

    public static StatisticInfosFragment newInstance()
      {
        return new StatisticInfosFragment();
      }

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.crystal_statistics_infos);

        findPreference(KEY_STATS_SWITCH).setOnPreferenceChangeListener(this);
        findPreference(KEY_CRYSTAL_UID).setSummary(new MD5(imei).getMD5());
        findPreference(KEY_CRYSTAL_DEVICE).setSummary(Build.DEVICE);
        findPreference(KEY_CRYSTAL_VERSION).setSummary(Build.CRYSTAL.VERSION);
        findPreference(KEY_CRYSTAL_CODENAME).setSummary(Build.CRYSTAL.CODENAME);
        findPreference(KEY_CRYSTAL_BRANCH).setSummary(Build.CRYSTAL.BRANCH);
        findPreference(KEY_CRYSTAL_API_LEVEL).setSummary(Build.CRYSTAL.API_LEVEL);
        findPreference(KEY_CRYSTAL_FLAVOUR).setSummary(Build.CRYSTAL.FLAVOUR);

        int can_send_stats = (sp.getBoolean("share_stats_infos", true)) ? 1 : 0;
        switch (can_send_stats)
          {
            case 0:
              findPreference(KEY_STATS_SWITCH).setSummary(getResources().getString(R.string.dont_send_stats));
              break;
            case 1:
              findPreference(KEY_STATS_SWITCH).setSummary(getResources().getString(R.string.send_stats));
              break;
            default:
              findPreference(KEY_STATS_SWITCH).setSummary(getResources().getString(R.string.switch_statistic_infos_default));
          }
      }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o)
      {
        if (preference.getKey().equals(KEY_STATS_SWITCH))
        {
          editor.putBoolean("share_stats_infos", (Boolean) o).apply();
        }
        return false;
      }
  }
