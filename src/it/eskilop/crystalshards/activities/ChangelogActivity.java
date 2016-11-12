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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import it.eskilop.crystalshards.R;

import static it.eskilop.crystalshards.utils.CrystalAPI.CHANGELOG_URL;


public class ChangelogActivity extends AppCompatActivity
  {

    @Override
    protected void onCreate(Bundle savedInstanceState)
      {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelog);
        final TextView text = (TextView) findViewById(R.id.changelog_text);
        text.setMovementMethod(new ScrollingMovementMethod());

        new Thread(new Runnable()
          {
            @Override
            public void run()
              {

                URL u = null;
                try
                  {
                    u = new URL(CHANGELOG_URL);
                    HttpURLConnection c = (HttpURLConnection) u.openConnection();
                    c.setRequestMethod("GET");
                    c.connect();
                    InputStream in = c.getInputStream();
                    final ByteArrayOutputStream bo = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024000]; // Takes 1Mb of data
                    in.read(buffer); // Read from Buffer.
                    bo.write(buffer); // Write Into Buffer.

                    ChangelogActivity.this.runOnUiThread(new Runnable()
                      {
                        @Override
                        public void run()
                          {
                            text.setText(Html.fromHtml(bo.toString().trim()));
                          }
                      });

                    bo.close();

                  }
                catch (Exception e)
                  {

                  }
              }
          }).start();
      }
  }
