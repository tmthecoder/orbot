/* Copyright (c) 2019 Tejas Mehta, Frank Gomes, Connor McDermid.
* Licensed under the GNU General Public License version 3.0
* */

package org.hackSugar.hopper;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;
import android.content.Intent;
import org.torproject.android.OrbotMainActivity;
import org.torproject.android.service.TorService;


/**
 * @author Connor McDermid
 * Contact me with issues at connor.mcdermid@mcvts.org
 * This class builds a Quick Settings tile for use on the Android Quick Settings menu.
 * The tile is set to the "Unavailable" state when the device is not unlocked, for security purposes,
 * although this will not disable the Tor VPN.
 */
@TargetApi(Build.VERSION_CODES.N)
public class HSTileService extends TileService {


    Tile tile;


    @Override
    public void onClick() {
        super.onClick();

        //Called when tile clicked

        Intent torStartIntent = new Intent(HSTileService.this, OrbotMainActivity.class);
        torStartIntent.setAction("org.torproject.android.START_TOR");
        startActivity(torStartIntent);
    }

    @Override
    public void onTileRemoved() {
        super.onTileRemoved();

        //Called when tile is removed
    }

    @Override
    public void onTileAdded() {
        super.onTileAdded();

        //While I'm sure you get the point, for documentation purposes I must state that this is called when the tile
        // is added.

        tile = getQsTile();
    }

    @Override
    public void onStartListening() {
        super.onStartListening();


        //This is called when the tile becomes visible.

        if (isTorServiceRunning()) {
            tile.setLabel("Tor");
            tile.setContentDescription("Activates the Tor VPN");
            if (isSecure()) {
                tile.setState(Tile.STATE_UNAVAILABLE);
                tile.updateTile();
                return;
            }
            if (isTorServiceRunning()) {
                tile.setState(Tile.STATE_ACTIVE);
            } else {
                tile.setState(Tile.STATE_INACTIVE);
            }
            tile.updateTile();
        }


    }

    @Override
    public void onStopListening() {
        super.onStopListening();

        //This is called when the tile is no longer visible.
    }

    private boolean isTorServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (TorService.class.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
