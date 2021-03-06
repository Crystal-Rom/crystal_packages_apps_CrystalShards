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


import android.os.Bundle;
import android.preference.PreferenceFragment;

import it.eskilop.crystalshards.R;

public class RecentsModsFragment extends PreferenceFragment
  {

    public RecentsModsFragment()
      {
        // Required empty public constructor
      }

    public static RecentsModsFragment newInstance()
      {
        return new RecentsModsFragment();
      }

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.crystal_mod_recents);
      }
  }