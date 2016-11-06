package it.eskilop.crystalshards.tiles;

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

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.preference.PreferenceManager;
import android.service.quicksettings.TileService;
import android.support.annotation.RequiresApi;

import it.eskilop.crystalshards.R;
import it.eskilop.crystalshards.activities.UserTileCustomizationActivity;

import java.util.Objects;

/**
 * Created by eskilop on 22/09/16.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class UserTile extends TileService
{
  SharedPreferences crystalprefs;

  @Override
  public void onDestroy()
  {
    super.onDestroy();
  }

  @Override
  public void onTileAdded()
  {
    super.onTileAdded();
  }

  @Override
  public void onTileRemoved()
  {
    super.onTileRemoved();
  }

  @Override
  public void onStartListening()
  {
    super.onStartListening();
    crystalprefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    getQsTile().setLabel(crystalprefs.getString("user_name_value", "Proprietario"));
    if (!Objects.equals(crystalprefs.getString("user_image_path", ""), ""))
    {
      getQsTile().setIcon(Icon.createWithBitmap(BitmapFactory.decodeFile(crystalprefs.getString("user_image_path", ""))));
    }
    if (crystalprefs.getBoolean("user_replace_icon", false))
    {
      getQsTile().setIcon(Icon.createWithBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.crystal_rom)));
    } else
    {
      getQsTile().setIcon(Icon.createWithBitmap(BitmapFactory.decodeResource(getResources()
              , R.drawable.ic_account_circle_white_48dp)));
    }
    getQsTile().updateTile();
  }

  @Override
  public void onStopListening()
  {
    super.onStopListening();
  }

  @Override
  public void onClick()
  {
    super.onClick();
    startActivity(new Intent(UserTile.this, UserTileCustomizationActivity.class));
  }
}
