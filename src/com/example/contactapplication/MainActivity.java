package com.example.contactapplication;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.os.Build;

public class MainActivity extends Activity {

	ContactsDatabase database = new ContactsDatabase(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listContacts();

	}
	
	public void listContacts(){
		openDatabase();
		 //database.insertRow("Dummy", "la", "0256", "dob", "email", "addressLine1", "town", "county", "postCode", "group");
		Cursor databasecursor = database.getAllRows();
	
		String[] stringNames = new String[]{
				ContactsDatabase.KEY_FIRSTNAME
		};
		
		int[] stringNamesID = new int[] {
				android.R.id.text1 // CHANGE
		};
		
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
				this,
				android.R.layout.simple_list_item_1, //CHANGE
				databasecursor,
				stringNames,
				stringNamesID
				);
	
		ListView myList = (ListView) findViewById(R.id.listviewFirst);
		myList.setAdapter(cursorAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void openDatabase(){
		 database.open();
	}

	private void closeDatabase(){
		database.close();
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
	} */

}
