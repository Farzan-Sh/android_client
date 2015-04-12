package com.F.ac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class BR extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		
		try {
	        Socket s = new Socket("localhost",12345);
	        OutputStream out = s.getOutputStream();
	        PrintWriter output = new PrintWriter(out);
	        output.println("get_pv_msgs " + System.currentTimeMillis() + " 500" + "<|.|>");
	        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        String st = input.readLine();
	        s.close();
	        
	        String[] mesgs = st.split("<|.|>");
	        // it should be added to database here later...
	       
	       
	} catch (Exception e) {}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
