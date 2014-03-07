package com.twf.wtf.activities;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.net.wifi.p2p.WifiP2pManager.*;
import static android.os.Looper.getMainLooper;

public class WifiDevices {
    private final IntentFilter intentFilter = new IntentFilter();
    private Context wContext;
    private List<Object> activeDevices = new ArrayList<Object>();

    public WifiDevices(Context context) {
        WifiP2pManager wifiManager = (WifiP2pManager) context.getSystemService(context.WIFI_P2P_SERVICE);
        Channel channel = wifiManager.initialize(context, getMainLooper(), null);
        wContext = context;

        //discoverPeers initiates the discovery process; doesn't find any at this point!
        wifiManager.discoverPeers(channel, new ActionListener() {
            @Override
            public void onSuccess() {
                Log.e("Discover peers", "Success");
            }

            @Override
            public void onFailure(int i) {
                Log.e("Discover peers", "Failure");
            }
        });

        wifiManager.requestPeers(channel, new PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
                activeDevices.addAll(wifiP2pDeviceList.getDeviceList());
            }
        });
    }

    public List<Object> getActiveDevices() {
        if (activeDevices.size() != 0) {
            return activeDevices;
        } else {
            activeDevices.add("No Device Found");
            return activeDevices;
        }
    }
}