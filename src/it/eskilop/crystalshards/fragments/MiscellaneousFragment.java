package it.eskilop.crystalshards.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.eskilop.crystalshards.R;
import it.eskilop.crystalshards.activities.CrystalInfoActivity;

public class MiscellaneousFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener
  {

    static String KEY_INFO = "crystal_info";
    static String kEY_REPORT = "report_bug";
    static String KEY_FEATURE = "feature_request";

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
        if(preference.getKey().equals(KEY_INFO))
              startActivity(new Intent(getActivity().getApplicationContext(), CrystalInfoActivity.class));
        return false;
      }
  }
