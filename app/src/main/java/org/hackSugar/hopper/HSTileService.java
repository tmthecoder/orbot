package org.hackSugar.hopper;

import android.annotation.TargetApi;
import android.os.Build;
import android.service.quicksettings.TileService;
import android.content.Intent;
import org.torproject.android.OrbotMainActivity;

@TargetApi(Build.VERSION_CODES.N)
public class HSTileService extends TileService {

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
    }

    @Override
    public void onStartListening() {
        super.onStartListening();

        //This is called when the tile becomes visible.
    }

    @Override
    public void onStopListening() {
        super.onStopListening();

        //This is called when the tile is no longer visible.
    }
}
