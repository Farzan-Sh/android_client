package com.F.ac;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class Chatsc extends ActionBarActivity {

	String name = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatsc);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    name = extras.getString("name");
		}
		
		EditText allmsg = (EditText) findViewById(R.id.allmesseges);
		allmsg.append("ElmosEram: Say Hello To " + name.toString() + "\n");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chatsc, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_chatsc,
					container, false);
			return rootView;
		}
	}
	
	public void Sendmsg(View v)
	{
		
		EditText et1 = (EditText) findViewById(R.id.message);
		
		if (et1.getText().toString().matches(""))
		{
			Toast.makeText(v.getContext(),"You Cannot Send Empty Messeges!",Toast.LENGTH_LONG).show();
		}else{
		
			EditText msg = (EditText) findViewById(R.id.message);
			EditText allmsg = (EditText) findViewById(R.id.allmesseges);
			String s = msg.getText().toString();
			msg.setText("");
			//allmsg.append(s);
			allmsg.append( "You: " + s.toString() + "\n");
			
		
		//Toast.makeText(v.getContext(), "Sent This To " + name.toString() ,Toast.LENGTH_LONG).show();
		}
	}
		
	}


