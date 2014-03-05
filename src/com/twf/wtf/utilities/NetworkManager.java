package com.twf.wtf.utilities;

import android.content.Context;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

public class NetworkManager {
    private WifiManager wifiManager;

    public NetworkManager(Context context) {
        wifiManager = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public List<String> listNetworkDevices() {
        List<String> list = new ArrayList<String>();
        list.add("Device 1");
        list.add("Device 2");
        list.add("Device 3");
        System.out.println((wifiManager.getScanResults()));
        return list;
    }

    public boolean isConnected() {
        boolean WifiStatus = wifiManager.isWifiEnabled() && wifiManager.getConnectionInfo().getSSID() != null;
        return WifiStatus;
    }


}
