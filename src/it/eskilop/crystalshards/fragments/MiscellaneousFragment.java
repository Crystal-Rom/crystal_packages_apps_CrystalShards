package it.eskilop.crystalshards.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import it.eskilop.crystalshards.R;
import it.eskilop.crystalshards.activities.CrystalInfoActivity;
import it.eskilop.crystalshards.activities.StatisticInfosActivity;

public class MiscellaneousFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener
  {

    String KEY_INFO = "crystal_info";
    String kEY_REPORT = "report_bug";
    String KEY_FEATURE = "feature_request";
    String KEY_STATISTIC_INFOS = "key_info_stats";

    public static MiscellaneousFragment newInstance()
      {
        return new MiscellaneousFragment();
      }

    public MiscellaneousFragment()
      {
        // Required empty public constructor
      }

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.crystal_miscellaneous);

        findPreference(KEY_INFO).setOnPreferenceClickListener(this);
      }

    @Override
    public boolean onPreferenceClick(Preference preference)
      {
        if (preference.getKey().equals(KEY_INFO))
          {
            startActivity(new Intent(getActivity().getApplicationContext(), CrystalInfoActivity.class));
          }
        return false;
      }
  }
