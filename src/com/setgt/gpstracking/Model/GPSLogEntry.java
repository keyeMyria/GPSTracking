package com.setgt.gpstracking.Model;

import java.sql.Timestamp;

public class GPSLogEntry {
	private int id;
	private String gpsLocation;
	private Timestamp timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(String gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return gpsLocation;
	}
}
