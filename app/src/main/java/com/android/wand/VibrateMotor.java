package com.android.wand;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class VibrateMotor {
    BluetoothAdapter btAdapter;
    BluetoothSocket btSocket;

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");


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

            BluetoothDevice dispositivo = btAdapter.getRemoteDevice(arduino.getAddress());//connects to the device's address and checks if it's available
            try {
                btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
            } catch (IOException e) {
                System.err.println("ERROR!!! Could not start connection!!!");
                e.printStackTrace();
            }
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
            try {
                btSocket.connect();//start connection
            } catch (IOException e) {
                System.err.println("ERROR!!! Could not connect!!!");
                e.printStackTrace();
            }
        }
    }

    public void VibrateOn() {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("ON".getBytes());
            }
            catch (IOException e)
            {
                System.err.println("Error!!! Could not write to output stream!!!");
            }
        }
    }

    public void VibrateOff() {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("OFF".getBytes());
            }
            catch (IOException e)
            {
                System.err.println("Error!!! Could not write to output stream!!!");
            }
        }
    }
}
