package com.setgt.gpstracking;

import com.setgt.gpstracking.SendSMS;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AndroidGPSTrackingActivity extends Activity implements CompoundButton.OnCheckedChangeListener {

	Button btnShowLocation;
	String mobileNumber = "";
	long waitPeriod = 0;
	long iterations = 0;
	GPSTracker gps;
	TextView textview;
	boolean switchSendSMSState = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState	);

		setContentView(R.layout.activity_android_gpstracking);

		Switch s = (Switch) findViewById(R.id.switchSendSMS);
		if (s != null) {
			s.setOnCheckedChangeListener(this);
		}

		// Default the Mobile stuff to disabled
		setTextViewEnabled(R.id.textMobileNumber, false);
		setTextViewEnabled(R.id.textWaitPeriod, false);
		setTextViewEnabled(R.id.textIterations, false);

		btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
		btnShowLocation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				if (switchSendSMSState) {
					textview = (TextView)findViewById(R.id.textMobileNumber);
					mobileNumber = textview.getText().toString();

					textview = (TextView)findViewById(R.id.textWaitPeriod);
					waitPeriod = Long.valueOf((String) textview.getText().toString());

					textview = (TextView)findViewById(R.id.textIterations);
					iterations = Long.valueOf((String) textview.getText().toString());
				}

				for (long i = 1; i<=iterations; i++) {

					// create class object
					gps = new GPSTracker(AndroidGPSTrackingActivity.this);

					// check if GPS enabled     
					if(gps.canGetLocation()){

						double latitude = gps.getLatitude();
						double longitude = gps.getLongitude();

						// \n is for new line
						Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

						
						
						if (switchSendSMSState) {
							new SendSMS(mobileNumber, latitude, longitude);
						}

					}else{
						// can't get location
						// GPS or Network is not enabled
						// Ask user to enable GPS/network in settings
						gps.showSettingsAlert();
					}

					try {
						Thread.sleep(waitPeriod * 1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		});
	}

	private void setTextViewEnabled(int fieldId, boolean b) {
		textview = (TextView)findViewById(fieldId);
		textview.setEnabled(b);	
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switchSendSMSState = isChecked;
		setTextViewEnabled(R.id.textMobileNumber, isChecked);
		setTextViewEnabled(R.id.textWaitPeriod, isChecked);
		setTextViewEnabled(R.id.textIterations, isChecked);
		if (!isChecked) {
			((EditText)findViewById(R.id.textMobileNumber)).setText("");	
			((EditText)findViewById(R.id.textWaitPeriod)).setText("");
			((EditText)findViewById(R.id.textIterations)).setText("");
		}
	}
}