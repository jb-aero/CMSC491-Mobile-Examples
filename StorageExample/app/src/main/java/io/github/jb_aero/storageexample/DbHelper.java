package io.github.jb_aero.storageexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jb_ae on 3/7/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "locationDB";
	private static final int DATABASE_VERSION = 1;
	private String CREATE_TABLE = "create table location(ID integer autoincrement, timestamp text, latitude real, longitude real, primary key (ID))";

	public DbHelper (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABL IF EXISTS location");
		onCreate(db);
	}
}
