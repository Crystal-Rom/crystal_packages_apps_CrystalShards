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

package it.eskilop.crystalshards.tiles;

import android.content.Intent;
import android.os.Build;
import android.service.quicksettings.TileService;
import android.support.annotation.RequiresApi;

import it.eskilop.crystalshards.activities.UserTileCustomizationActivity;

/**
 * Created by eskilop on 22/09/16.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
public class ScreenshotTile extends TileService
  {

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
        startActivity(new Intent(ScreenshotTile.this, UserTileCustomizationActivity.class));
      }


  }
