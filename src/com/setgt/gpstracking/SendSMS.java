package com.setgt.gpstracking;
import android.telephony.SmsManager;

public class SendSMS {
	public SendSMS(String mobileNumber, double latitude, double longitude) {
		String message = "http://maps.google.com/maps?q=" + latitude + "+" + longitude;
		
	    SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage(mobileNumber, null, message, null, null);
	}

	public void sendSMS(String mobileNumber, String message) {

	    SmsManager smsManager = SmsManager.getDefault();
	    smsManager.sendTextMessage(mobileNumber, null, message, null, null);
	}
}
