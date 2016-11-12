/*
 * Copyright 2016 The Crystal Rom Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.eskilop.crystalshards;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import it.eskilop.crystalshards.fragments.AboutDevFragment;
import it.eskilop.crystalshards.fragments.AboutRomFragment;
import it.eskilop.crystalshards.fragments.DisplayModsFragment;
import it.eskilop.crystalshards.fragments.GeneralModsFragment;
import it.eskilop.crystalshards.fragments.MiscellaneousFragment;
import it.eskilop.crystalshards.fragments.PhysicalKeysModsFragment;
import it.eskilop.crystalshards.fragments.RecentsModsFragment;
import it.eskilop.crystalshards.fragments.StatusbarModsFragment;
import it.eskilop.crystalshards.utils.MD5;

import static it.eskilop.crystalshards.utils.CrystalAPI.REGISTER_STATISTICS_URL;

public class CrystalShardsActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
  {

    // Todo: add permission request

    SharedPreferences sp = getSharedPreferences("CrystalPrefs", MODE_PRIVATE);

    TelephonyManager tm = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
    private String imei = tm.getDeviceId();
    private final String CRYSTAL_UID = new MD5(imei).getMD5();

    @Override
    protected void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crystal_shards);

        if (sp.getBoolean("share_stats_infos", true))
          {
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_STATISTICS_URL,
                    new Response.Listener<String>()
                      {
                        @Override
                        public void onResponse(String response)
                          {

                          }
                      }, new Response.ErrorListener()
              {
                @Override
                public void onErrorResponse(VolleyError error)
                  {
                  }
              })
              {
                @Override
                protected Map<String, String> getParams()
                  {
                    Map<String, String> params = new HashMap<>();
                    params.put("crystal_uid", CRYSTAL_UID);
                    params.put("crystal_device", Build.DEVICE);
                    params.put("crystal_version", Build.CRYSTAL.VERSION);
                    params.put("crystal_codename", Build.CRYSTAL.CODENAME);
                    params.put("crystal_branch", Build.CRYSTAL.BRANCH);
                    params.put("crystal_api_level", Build.CRYSTAL.API_LEVEL);
                    params.put("crystal_flavour", Build.CRYSTAL.FLAVOUR);

                    return params;
                  }
              };
            // Add the request to the RequestQueue.
            queue.add(stringRequest);
          }

        if (savedInstanceState == null)
          {
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, MiscellaneousFragment.newInstance())
                    .commit();
          }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
      }

    @Override
    public void onBackPressed()
      {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
          {
            drawer.closeDrawer(GravityCompat.START);
          } else
          {
            super.onBackPressed();
          }
      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
      {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.crystal_shards, menu);
        return true;
      }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
      {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
          {

          }

        return super.onOptionsItemSelected(item);
      }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
      {
        // Handle navigation view item clicks here.
        switch (item.getItemId())
          {
            case R.id.nav_general:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, GeneralModsFragment.newInstance())
                      .commit();
              break;
            case R.id.nav_display:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, DisplayModsFragment.newInstance())
                      .commit();
              break;
            case R.id.nav_statusbar:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, StatusbarModsFragment.newInstance())
                      .commit();
              break;
            case R.id.nav_recents:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, new RecentsModsFragment())
                      .commit();
              break;
            case R.id.nav_physical_keys:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, PhysicalKeysModsFragment.newInstance())
                      .commit();
              break;
            case R.id.nav_miscellaneous:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, MiscellaneousFragment.newInstance())
                      .commit();
              break;
            case R.id.nav_about_rom:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, AboutRomFragment.newInstance())
                      .commit();
              break;
            case R.id.nav_about_developer:
              getFragmentManager().beginTransaction()
                      .replace(R.id.container, AboutDevFragment.newInstance())
                      .commit();
              break;
          }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
      }
  }
