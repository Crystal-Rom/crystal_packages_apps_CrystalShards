package it.eskilop.crystalshards.activities;

/*
     Copyright (C) 2016 Crystal Rom
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

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import it.eskilop.crystalshards.R;
import it.eskilop.crystalshards.adapters.CustomAdapter;

import static it.eskilop.crystalshards.utils.CrystalAPI.GET_ROM_UPDATES_URL;

public class UpdaterActivity extends AppCompatActivity
  {
    static String[] builds;
    private String TAG = "Crystal Updater";

    @Override
    protected void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updater);

        final ListView rom_builds = (ListView) findViewById(R.id.rom_builds);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, GET_ROM_UPDATES_URL,
                new Response.Listener<String>()
                  {
                    @Override
                    public void onResponse(String response)
                      {
                        builds = response.split(";");
                        for (int i = 0; i < builds.length; i++)
                          {
                            if (builds[i].trim().equals(""))
                              {
                              }
                          }
                        rom_builds.setAdapter(new CustomAdapter(getApplicationContext(), R.layout.custom_row, Arrays.asList(builds)));
                      }
                  }, new Response.ErrorListener()
          {
            @Override
            public void onErrorResponse(VolleyError error)
              {
                Log.e(TAG, "onErrorResponse: There's been an error");
              }
          })
          {
            @Override
            protected Map<String, String> getParams()
              {
                Map<String, String> params = new HashMap<>();
                params.put("device", Build.DEVICE);
                params.put("channel", "stable");

                return params;
              }
          };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
      }
  }
