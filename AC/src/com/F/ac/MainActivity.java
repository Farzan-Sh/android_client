package com.F.ac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	
	public void gotosignup(View v)
	{
		
		Intent intent = new Intent(v.getContext(), SignUP.class);
        startActivity(intent); 	
		
	}
	
	public void gotosignin(View v)
	{
		
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);
		
		if (et1.getText().toString().matches("") || et2.getText().toString().matches(""))
		{
			Toast.makeText(v.getContext(),"Please Insert Your Informations...",Toast.LENGTH_LONG).show();
		}else{
		
			try {
		        Socket s = new Socket("localhost",12345);
		        OutputStream out = s.getOutputStream();
		        PrintWriter output = new PrintWriter(out);
		        output.println("login " + et1.getText().toString() + " " + et2.getText().toString() + "<|.|>");
		        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		        String st = input.readLine();
		        s.close();
		        
		        if (st == "r_login ok<|.|>"){
		        	Intent intent = new Intent(v.getContext(), Friends.class);
					intent.putExtra("userpass", et1.getText().toString() + et2.getText().toString());
			        startActivity(intent); 
		        }else{
		        	Toast.makeText(v.getContext(),"You Are Not Authenticated",Toast.LENGTH_LONG).show();
		        }
		       
		       
		} catch (Exception e) {}
			
			
			
			
	      
	        
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
