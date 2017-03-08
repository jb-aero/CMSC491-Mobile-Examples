package io.github.jb_aero.storageexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by jb_ae on 3/7/2017.
 */

public class DbOperations {

	private DbHelper dbHelper;
	private SQLiteDatabase db;

	public DbOperations(Context context) {
		dbHelper = new DbHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public void insertData(float latitude, float longitude, String timestamp) {
		ContentValues values = new ContentValues();
		values.put("timestamp", timestamp);
		values.put("latitude", latitude);
		values.put("longitude", longitude);
		db.insert("location", null, values);
	}

	public List<Float> returnLatitudes() {
		Cursor mycursor =  db.query("location", new String[]{"timestamp", "latitude", "longitude"}, null, null, null, null, null, null);

		List<Float> ret = new LinkedList<>();
		do {
			ret.add(mycursor.getFloat(1));
		} while (mycursor.moveToNext());

		return ret;
	}
}
