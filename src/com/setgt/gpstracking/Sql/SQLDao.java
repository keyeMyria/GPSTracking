package com.setgt.gpstracking.Sql;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.setgt.gpstracking.Model.GPSLogEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLDao {

	// Database fields
	private SQLiteDatabase database;
	private SQLCreateLogEntry dbHelper;
	private String[] logEntryColumns = {
			SQLCreateLogEntry.COLUMN_ID,
			SQLCreateLogEntry.COLUMN_GPSLOCATION,
			SQLCreateLogEntry.COLUMN_TIMESTAMP 
		};

	public SQLDao(Context context) {
		dbHelper = new SQLCreateLogEntry(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public GPSLogEntry createLogEntry(int id, String gpsLocation) {
		
		// Add all the fields in a record into a ContentValues, and insert the record
		ContentValues values = new ContentValues();
		values.put(SQLCreateLogEntry.COLUMN_ID, id);
		values.put(SQLCreateLogEntry.COLUMN_GPSLOCATION, gpsLocation);
		values.put(SQLCreateLogEntry.COLUMN_TIMESTAMP, new Timestamp(new java.util.Date().getTime()).toString());
		long insertId = database.insert(SQLCreateLogEntry.TABLE_GPSLOGENTRY, null,
				values);
		
		// Query the table for the entry just inserted and return it
		Cursor cursor = database.query(SQLCreateLogEntry.TABLE_GPSLOGENTRY,
				logEntryColumns, SQLCreateLogEntry.COLUMN_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		GPSLogEntry newLogEntry = cursorLogEntry(cursor);
		cursor.close();
		return newLogEntry;
	}

	public void deleteComment(GPSLogEntry comment) {
		String gpsLocation = comment.getGpsLocation();
		System.out.println("Comment deleted with gpsLocation: " + gpsLocation);
		database.delete(SQLCreateLogEntry.TABLE_GPSLOGENTRY, SQLCreateLogEntry.COLUMN_ID
				+ " = " + gpsLocation, null);
	}

	public List<GPSLogEntry> getAllLogEntries(int id) {
		List<GPSLogEntry> logEntries = new ArrayList<GPSLogEntry>();

		String whereClause = "where id = ?";
		String[] whereArg = new String[] {
				String.valueOf(id)
		};
		String orderBy = "timestamp";
		
		Cursor cursor = database.query(SQLCreateLogEntry.TABLE_GPSLOGENTRY,
				logEntryColumns, whereClause, whereArg, null, null, orderBy);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			GPSLogEntry logEntry = cursorLogEntry(cursor);
			logEntries.add(logEntry);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return logEntries;
	}

	public GPSLogEntry cursorLogEntry(Cursor cursor) {
		GPSLogEntry logEntry = new GPSLogEntry();
		logEntry.setId(cursor.getInt(0));
		logEntry.setGpsLocation(cursor.getString(1));
		logEntry.setTimestamp(Timestamp.valueOf(cursor.getString(2)));
		return logEntry;
	}
}
