package it.eskilop.crystalshards.adapters;

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

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import it.eskilop.crystalshards.R;
import it.eskilop.crystalshards.utils.DLManager;

/**
 * Created by eskilop on 12/06/16.
 */
public class CustomAdapter extends ArrayAdapter
  {
    private String base_url = "http://www.eskilop.it/CRYSTALROM/ROM/BUILDS/";


    private Context ctx;
    private View row = null;
    private int mResource;
    private List<String> mObjects;

    public CustomAdapter(Context context, int resource, List<String> objects)
      {
        super(context, resource, objects);
        ctx = context;
        mResource = resource;
        mObjects = objects;
      }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
      {

        LayoutInflater inflater = LayoutInflater.from(ctx);
        if (!mObjects.get(position).trim().equals(""))
          {
            row = inflater.inflate(mResource, parent, false);
            final TextView build_name = (TextView) row.findViewById(R.id.build_name);
            build_name.setText(mObjects.get(position));
            ImageButton download = (ImageButton) row.findViewById(R.id.download_build);
            download.setOnClickListener(new View.OnClickListener()
              {
                @Override
                public void onClick(View view)
                  {
                    new DLManager().download(base_url + Build.DEVICE + "/" + "stable/" + build_name.getText().toString(), ctx, "Crystal Updater:", "Scaricando: " + build_name.getText().toString(), build_name.getText().toString());
                  }
              });
          } else
          {

          }
        return row;
      }
  }
