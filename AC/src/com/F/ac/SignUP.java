package com.F.ac;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SignUP extends Activity {

	public void gostuff(View v)
	{
		
		EditText et1 = (EditText) findViewById(R.id.editText1);
		EditText et2 = (EditText) findViewById(R.id.editText2);
		EditText et3 = (EditText) findViewById(R.id.editText3);
		
		if (et1.getText().toString().matches("") || et2.getText().toString().matches("") || et3.getText().toString().matches(""))
		{
			Toast.makeText(v.getContext(),"Please Insert Your Informations...",Toast.LENGTH_LONG).show();
			
		}else{
			
			try {
		        Socket s = new Socket("localhost",12345);
		        OutputStream out = s.getOutputStream();
		        PrintWriter output = new PrintWriter(out);
		        output.println("signup new " + et1.getText().toString() + " " + et2.getText().toString() + "<|.|>");
		        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
		        String st = input.readLine();
		        s.close();
		        
		        if (st == "r_signup ok<|.|>"){
		        	Toast.makeText(v.getContext(),"You Are Known Now! Welcome To Our World!",Toast.LENGTH_LONG).show();
		        	Intent intent = new Intent(v.getContext(), MainActivity.class);
					startActivity(intent);
		        }else{
		        	Toast.makeText(v.getContext(),"Change Your UserName PlZ...",Toast.LENGTH_LONG).show();
		        }
		       
		       
		} catch (Exception e) {}
			
			
		
			
			
			
			
		}
	}
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.signup);
	    
	    // TODO Auto-generated method stub
	}

}
