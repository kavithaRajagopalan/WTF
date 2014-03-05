package com.twf.wtf.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.twf.wtf.R;
import com.twf.wtf.utilities.NetworkManager;

public class HomeActivity extends Activity {

    private NetworkManager networkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        networkManager = new NetworkManager(this);
        if (networkManager.isConnected()) {
            ListView deviceListView = (ListView) findViewById(R.id.devicesList);
            ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.device_list_view, networkManager.listNetworkDevices());
            deviceListView.setAdapter(listAdapter);
        } else {
            new AlertDialog.Builder(this).setTitle("Error").show();
        }
    }


}
