package com.setgt.gpstracking.Model;

import java.sql.Timestamp;

public class GPSLog {
	private int id;
	String description;
	private Timestamp timestamp;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		return description;
	}
}
