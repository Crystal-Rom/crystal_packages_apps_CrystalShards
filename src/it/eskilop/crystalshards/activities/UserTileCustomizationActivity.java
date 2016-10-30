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

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.preference.SwitchPreference;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import it.eskilop.crystalshards.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UserTileCustomizationActivity extends PreferenceActivity
{

  private static final int REQUEST_CHOOSE_IMAGE = 1;
  private static final String USER_IMAGE_KEY = "user_image";
  private static final String USER_NAME_KEY = "user_name";
  private static final String REPLACE_WITH_LOGO_KEY = "replace_with_logo";
  private PreferenceScreen user_image;
  private EditTextPreference user_name;
  private SwitchPreference replace_icon;
  private SharedPreferences crystalprefs;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.user_tile_prefs);

    crystalprefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

    user_image = (PreferenceScreen) findPreference(USER_IMAGE_KEY);
    user_name = (EditTextPreference) findPreference(USER_NAME_KEY);
    replace_icon = (SwitchPreference) findPreference(REPLACE_WITH_LOGO_KEY);

    user_image.setSummary(crystalprefs.getString("user_image_path", "Scegli un'immagine"));
    user_name.setSummary(crystalprefs.getString("user_name_value", "Scegli un nome"));

    user_image.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
    {
      @Override
      public boolean onPreferenceClick(Preference preference)
      {
        startActivityForResult(Intent.createChooser(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), "Choose an image"), REQUEST_CHOOSE_IMAGE);
        user_image.setSummary(crystalprefs.getString("user_image_path", "Scegli un'immagine"));
        return false;
      }
    });

    user_name.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
    {
      @Override
      public boolean onPreferenceChange(Preference preference, Object o)
      {
        crystalprefs.edit().putString("user_name_value", (String) o).apply();
        user_name.setSummary(crystalprefs.getString("user_name_value", "Scegli un nome"));
        return false;
      }
    });

    replace_icon.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
    {
      @Override
      public boolean onPreferenceChange(Preference preference, Object o)
      {
        crystalprefs.edit().putBoolean("user_replace_icon", (Boolean) o).apply();
        replace_icon.setChecked((Boolean) o);
        if((Boolean) o)
        {
          replace_icon.setSummary("L'immagine è stata cambiata con il logo di @crystalrom");
        }
        else
        {
          replace_icon.setSummary("L'immagine non è stata cambiata con il logo di @crystalrom");
        }
        return false;
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data)
  {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == REQUEST_CHOOSE_IMAGE)
    {
      if (resultCode == RESULT_OK)
      {
        String path;
        Uri selectedImage = data.getData();
        path = getPath(selectedImage);
        if (path != null)
          crystalprefs.edit().putString("user_image_path", path).apply();
        else
        {
          try
          {
            InputStream is = getContentResolver().openInputStream(selectedImage);
            path = selectedImage.getPath();
          } catch (FileNotFoundException e)
          {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }
  }

  @SuppressLint("NewApi")
  private String getPath(Uri uri)
  {
    if (uri == null)
    {
      return null;
    }

    String[] projection = {MediaStore.Images.Media.DATA};

    Cursor cursor;
    if (Build.VERSION.SDK_INT > 19)
    {
      String wholeID = DocumentsContract.getDocumentId(uri);
      String id = wholeID.split(":")[1];
      String sel = MediaStore.Images.Media._ID + "=?";

      cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
              projection, sel, new String[]{id}, null);
    } else
    {
      cursor = getContentResolver().query(uri, projection, null, null, null);
    }
    String path = null;
    try
    {
      int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      path = cursor.getString(column_index).toString();
      cursor.close();
    } catch (NullPointerException e)
    {

    }
    return path;
  }
}
