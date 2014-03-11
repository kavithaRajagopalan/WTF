package com.twf.wtf.activities;

import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static android.net.wifi.p2p.WifiP2pManager.*;
import static android.os.Looper.getMainLooper;

public class WifiDevices {
    private final IntentFilter intentFilter = new IntentFilter();
    private Context context;
    private List<Object> activeDevices = new ArrayList<Object>();

    public WifiDevices(Context context) {
        WifiP2pManager wifiP2pManager = (WifiP2pManager) context.getSystemService(context.WIFI_P2P_SERVICE);
        Channel channel = wifiP2pManager.initialize(context, getMainLooper(), null);
        this.context = context;

        wifiP2pManager.discoverPeers(channel, new ActionListener() {
            @Override
            public void onSuccess() {
                Log.e("Discover peers", "Success");
            }

            @Override
            public void onFailure(int i) {
                Log.e("Discover peers", "Failure");
            }
        });

        wifiP2pManager.requestPeers(channel, new PeerListListener() {
            @Override
            public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {
                activeDevices.addAll(wifiP2pDeviceList.getDeviceList());
            }
        });
    }

    public void toggleHotSpot() {
        WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        Method[] methods = wifiManager.getClass().getDeclaredMethods();
        boolean enabled = false;
        for (Method method : methods) {
            if (method.getName().equals("isWifiApEnabled")) {
                try {
                    enabled = (Boolean) method.invoke(wifiManager);
                } catch (Exception ex) {
                }
                break;
            }
        }
        for (Method method : methods) {
            if (method.getName().equals("setWifiApEnabled")) {
                try {
                    method.invoke(wifiManager, null, !enabled);
                } catch (Exception ex) {
                }
                break;
            }
        }

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