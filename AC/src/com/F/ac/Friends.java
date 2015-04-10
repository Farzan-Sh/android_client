package com.F.ac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class Friends extends ActionBarActivity {

	//========================================================================================
	
	List<Map<String, String>> friendsList = new ArrayList<Map<String,String>>();
	 
	
	private void initList()
	{
		friendsList.add(createFriend("friend", "Farzan"));
		friendsList.add(createFriend("friend", "Kia"));
		friendsList.add(createFriend("friend", "Amin"));
		friendsList.add(createFriend("friend", "Aref"));
		friendsList.add(createFriend("friend", "Milad"));
	}
	 
	private HashMap<String, String> createFriend(String key, String name) {
	    HashMap<String, String> friend = new HashMap<String, String>();
	    friend.put(key, name);
	     
	    return friend;
	}
	
	public void addf()
	{
		friendsList.add(createFriend("friend", "A New Friend!"));
	}
	
	
	//===========================================================================================
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friends);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		SimpleAdapter simpleAdpt = new SimpleAdapter(this, friendsList, android.R.layout.simple_list_item_1, new String[] {"friend"}, new int[] {android.R.id.text1});

		initList();
	    ListView lv = (ListView) findViewById(R.id.listView1);
		simpleAdpt = new SimpleAdapter(this, friendsList, android.R.layout.simple_list_item_1, new String[] {"friend"}, new int[] {android.R.id.text1});
		lv.setAdapter(simpleAdpt);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			 
		     public void onItemClick(AdapterView<?> parentAdapter, View view, int position,long id) {
		              
		      
		         // We know the View is a TextView so we can cast it
		         TextView clickedView = (TextView) view;
		         
		         //Toast.makeText(Friends.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
		         //Toast.makeText(Friends.this, clickedView.getText(), Toast.LENGTH_SHORT).show();
		         
		         
		         Intent intent = new Intent(view.getContext(), Chatsc.class);
		         intent.putExtra("name",clickedView.getText().toString());
		         startActivity(intent);
		         
		     }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_friends,
					container, false);
			return rootView;
		}
	}
	
	
	public void addfriend(View v)
	{
		// ina badan bas tu oncreate qarar begire
		
	SimpleAdapter simpleAdpt = new SimpleAdapter(this, friendsList, android.R.layout.simple_list_item_1, new String[] {"friend"}, new int[] {android.R.id.text1});

	addf();
    ListView lv = (ListView) findViewById(R.id.listView1);
	simpleAdpt = new SimpleAdapter(this, friendsList, android.R.layout.simple_list_item_1, new String[] {"friend"}, new int[] {android.R.id.text1});
	lv.setAdapter(simpleAdpt);

	}
	
	
	
	
}
