package com.twf.wtf.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.twf.wtf.R;
import com.twf.wtf.utilities.NetworkManager;

public class HomeActivity extends Activity {

    private NetworkManager networkManager;
    private WifiDevices wifiDevices;
    private ListView deviceListView;
    private Button createHotSpotButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        deviceListView = (ListView) findViewById(R.id.devicesList);
        createHotSpotButton = (Button) findViewById(R.id.hotSpotButton);

        wifiDevices = new WifiDevices(this);
        networkManager = new NetworkManager(this);
        if (!networkManager.isConnected()) createHotSpot();
        listDevices();
    }

    private void createHotSpot() {
        createHotSpotButton.setVisibility(View.VISIBLE);
        createHotSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wifiDevices.toggleHotSpot();
            }
        });
    }

    private void listDevices() {
        ListAdapter listAdapter = new ArrayAdapter<String>(this, R.layout.device_list_view, networkManager.listNetworkDevices());
        deviceListView.setAdapter(listAdapter);
        Toast.makeText(this, "Inside list devices", Toast.LENGTH_LONG).show();
        deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AudioSenderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        if (!networkManager.isConnected()) createHotSpot();
        listDevices();
        super.onRestart();
    }
}
