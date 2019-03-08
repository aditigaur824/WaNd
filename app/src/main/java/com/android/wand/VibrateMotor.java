package com.android.wand;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.util.Set;

public class VibrateMotor {
    BluetoothAdapter btAdapter;
    BluetoothSocket btSocket;


    VibrateMotor(Context context) {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if(!btAdapter.isEnabled()) {
            Toast.makeText(context, "Please enable your device's bluetooth.", Toast.LENGTH_LONG).show();
        }
        else {
            Set<BluetoothDevice> btDevices = btAdapter.getBondedDevices();
            BluetoothDevice arduino = null;
            for (BluetoothDevice btDevice : btDevices) {
                arduino = btDevice;
            }
            System.err.println("BT Device name - " + arduino.getName());
            /*
            BluetoothDevice dispositivo = btAdapter.getRemoteDevice(address);//connects to the device's address and checks if it's available
            btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
            btSocket.connect();//start connection
            */
        }
    }

    public void VibrateOn() {


    }

    public void VibrateOff() {

    }
}
