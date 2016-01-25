package com.example.contactapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactsDatabase {
	
	// ------------------------------------ DBADapter.java ---------------------------------------------

	// TODO: Change the package to match your project.



	// TO USE:
	// Change the package (at top) to match your project.
	// Search for "TODO", and make the appropriate changes.
	

		/////////////////////////////////////////////////////////////////////
		//	Constants & Data
		/////////////////////////////////////////////////////////////////////
		// For logging:
	
		private static final String TAG = "DBAdapter";
		
		// DB Fields
		public static final String KEY_ROWID = "_id";
		public static final int COL_ROWID = 0;
		/*
		 * CHANGE 1:
		 */
		// TODO: Setup your fields here:
		public static final String KEY_FIRSTNAME = "firstName";
		public static final String KEY_SECONDNAME= "secondName";
		public static final String KEY_NUMBER = "mobileNumber";
		public static final String KEY_DOB = "dob";
		public static final String KEY_EMAIL = "email";
		public static final String KEY_ADDRESSLINE1 = "addressLine1";
		public static final String KEY_TOWN = "town";
		public static final String KEY_COUNTY = "county";
		public static final String KEY_POSTCODE = "postcode";
		public static final String KEY_CATEGORY = "category";
		
		// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
		public static final int COL_FIRSTNAME = 1;
		public static final int COL_SECONDNAME = 2;
		public static final int COL_NUMBER = 3;
		public static final int COL_DOB = 4;
		public static final int COL_EMAIL = 5;
		public static final int COL_ADDRESSLINE1 = 6;
		public static final int COL_TOWN = 7;
		public static final int COL_COUNTY = 8;
		public static final int COL_POSTCODE = 9;
		public static final int COL_CATEGORY = 10;
		

		public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_FIRSTNAME, KEY_SECONDNAME, KEY_NUMBER, KEY_DOB, KEY_EMAIL, KEY_ADDRESSLINE1, KEY_TOWN, KEY_COUNTY, KEY_POSTCODE, KEY_CATEGORY};
		
		// DB info: it's name, and the table we are using (just one).
		public static final String DATABASE_NAME = "contactsDatabase";
		public static final String DATABASE_TABLE = "contactsTable";
		// Track DB version if a new version of your app changes the format.
		public static final int DATABASE_VERSION = 2;	
		
		private static final String DATABASE_CREATE_SQL = 
				"create table " + DATABASE_TABLE 
				+ " (" + KEY_ROWID + " integer primary key autoincrement, "
				
				/*
				 * CHANGE 2:
				 */
				// TODO: Place your fields here!
				// + KEY_{...} + " {type} not null"
				//	- Key is the column name you created above.
				//	- {type} is one of: text, integer, real, blob
				//		(http://www.sqlite.org/datatype3.html)
				//  - "not null" means it is a required field (must be given a value).
				// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!
				+ KEY_FIRSTNAME + " text not null, "
				+ KEY_SECONDNAME + " text not null, "
				+ KEY_NUMBER + " text not null, "
				+ KEY_DOB + " text, "
				+ KEY_EMAIL + " text, "
				+ KEY_ADDRESSLINE1 + " text, "
				+ KEY_TOWN + " text, "
				+ KEY_COUNTY + " text, "
				+ KEY_POSTCODE + " text, "
				+ KEY_CATEGORY + " text "
				
				// Rest  of creation:
				+ ");";
		
		// Context of application who uses us.
		private final Context context;
		
		private DatabaseHelper myDBHelper;
		private SQLiteDatabase database;

		/////////////////////////////////////////////////////////////////////
		//	Public methods:
		/////////////////////////////////////////////////////////////////////
		
		public ContactsDatabase(Context ctx) {
			this.context = ctx;
			myDBHelper = new DatabaseHelper(context);
		}
		
		// Open the database connection.
		public ContactsDatabase open() {
			database = myDBHelper.getWritableDatabase();
			return this;
		}
		
		// Close the database connection.
		public void close() {
			myDBHelper.close();
		}
		
		// Add a new set of values to the database.
		public long insertRow(String firstName, String secondName, String mobileNumber, String dob, String email, String addressLine1, String town, String county, String postcode, String category) {
			/*
			 * CHANGE 3:
			 */		
			// TODO: Update data in the row with new fields.
			// TODO: Also change the function's arguments to be what you need!
			// Create row's data:
			ContentValues initialValues = new ContentValues();
			initialValues.put(KEY_FIRSTNAME, firstName);
			initialValues.put(KEY_SECONDNAME, secondName);
			initialValues.put(KEY_NUMBER, mobileNumber);
			initialValues.put(KEY_DOB, dob);
			initialValues.put(KEY_EMAIL, email);
			initialValues.put(KEY_ADDRESSLINE1, addressLine1);
			initialValues.put(KEY_TOWN, town);
			initialValues.put(KEY_COUNTY, county);
			initialValues.put(KEY_POSTCODE, postcode);
			initialValues.put(KEY_CATEGORY, category);
			
			// Insert it into the database.
			return database.insert(DATABASE_TABLE, null, initialValues);
		}
		
		// Delete a row from the database, by rowId (primary key)
		public boolean deleteRow(long rowId) {
			String where = KEY_ROWID + "=" + rowId;
			return database.delete(DATABASE_TABLE, where, null) != 0;
		}
		
		public void deleteAll() {
			Cursor c = getAllRows();
			long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
			if (c.moveToFirst()) {
				do {
					deleteRow(c.getLong((int) rowId));				
				} while (c.moveToNext());
			}
			c.close();
		}
		
		// Return all data in the database.
		public Cursor getAllRows() {
			String where = null;
			Cursor c = 	database.query(true, DATABASE_TABLE, ALL_KEYS, 
								where, null, null, null, null, null);
			if (c != null) {
				c.moveToFirst();
			}
			return c;
		}

		// Get a specific row (by rowId)
		public Cursor getRow(long rowId) {
			String where = KEY_ROWID + "=" + rowId;
			Cursor c = 	database.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
			if (c != null) {
				c.moveToFirst();
			}
			return c;
		}
		
		// Change an existing row to be equal to new data.
		public boolean updateRow(long rowId, String firstName, String secondName, String mobileNumber, String dob, String email, String addressLine1, String town, String county, String postcode, String category) {
			String where = KEY_ROWID + "=" + rowId;

			/*
			 * CHANGE 4:
			 */
			// TODO: Update data in the row with new fields.
			// TODO: Also change the function's arguments to be what you need!
			// Create row's data:
			ContentValues newValues = new ContentValues();
			newValues.put(KEY_FIRSTNAME, firstName);
			newValues.put(KEY_SECONDNAME, secondName);
			newValues.put(KEY_NUMBER, mobileNumber);
			newValues.put(KEY_DOB, dob);
			newValues.put(KEY_ADDRESSLINE1, addressLine1);
			newValues.put(KEY_TOWN, town);
			newValues.put(KEY_COUNTY, county);
			newValues.put(KEY_POSTCODE, postcode);
			newValues.put(KEY_CATEGORY, category);
			
			// Insert it into the database.
			return database.update(DATABASE_TABLE, newValues, where, null) != 0;
		}
		
		
		
		/////////////////////////////////////////////////////////////////////
		//	Private Helper Classes:
		/////////////////////////////////////////////////////////////////////
		
		/**
		 * Private class which handles database creation and upgrading.
		 * Used to handle low-level database access.
		 */
		private static class DatabaseHelper extends SQLiteOpenHelper
		{
			DatabaseHelper(Context context) {
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}

			@Override
			public void onCreate(SQLiteDatabase _db) {
				_db.execSQL(DATABASE_CREATE_SQL);			
			}

			@Override
			public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
				Log.w(TAG, "Upgrading application's database from version " + oldVersion
						+ " to " + newVersion + ", which will destroy all old data!");
				
				// Destroy old database:
				_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
				
				// Recreate new database:
				onCreate(_db);
			}
		}
	}
