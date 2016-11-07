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

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannedString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.eskilop.crystalshards.R;

public class AboutDevFragment extends Fragment
  {
    public AboutDevFragment()
      {
        // Required empty public constructor
      }

    public static AboutDevFragment newInstance()
      {
        return new AboutDevFragment();
      }

    @Override
    public void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);

      }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
      {
        View rootView = inflater.inflate(R.layout.fragment_about_dev, container, false);
        TextView desc = (TextView) rootView.findViewById(R.id.tv_dev_desc);
        desc.setText(Html.fromHtml(Html.toHtml((SpannedString) getResources().getText(R.string.about_dev_desc))));
        return rootView;
      }

  }
