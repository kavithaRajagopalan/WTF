package com.twf.wtf.utilities;

import android.content.Context;
import android.net.wifi.WifiManager;
import com.twf.wtf.activities.WifiDevices;

import java.util.List;

public class NetworkManager {
    private WifiManager wifiManager;
    private Context context;

    public NetworkManager(Context context) {
        this.context = context;
        wifiManager = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    }

    public List listNetworkDevices() {
        WifiDevices wifiDevices = new WifiDevices(context);
        return wifiDevices.getActiveDevices();
    }

    public boolean isConnected() {
        boolean WifiStatus = wifiManager.isWifiEnabled() && wifiManager.getConnectionInfo().getSSID() != null;
        return WifiStatus;
    }


}
