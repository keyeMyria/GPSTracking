package com.setgt.gpstracking.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLCreateLog extends SQLiteOpenHelper {
	  public static final String TABLE_GPSLOG = "gpslog";
	  public static final String COLUMN_ID = "id";
	  public static final String COLUMN_DESCRIPTION = "description";
	  public static final String COLUMN_TIMESTAMP = "timestamp";

	  private static final String DATABASE_NAME = "gpslogger.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_GPSLOG + "(" +
			COLUMN_ID + " integer primary key autoincrement, " +
	        COLUMN_DESCRIPTION + " String, " +
	        COLUMN_TIMESTAMP + " timestamp not null);";

	  public SQLCreateLog(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SQLCreateLog.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_GPSLOG);
	    onCreate(db);
	  }
}
