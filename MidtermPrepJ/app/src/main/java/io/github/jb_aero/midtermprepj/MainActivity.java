package io.github.jb_aero.midtermprepj;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
		implements View.OnClickListener, SensorEventListener, LocationListener {

	final String KEY1 = "USERNAME";

;	TextView ax, ay, az, lat, lon, username;
	SensorManager sm;
	LocationManager lm;
	Handler handler;
	Sensor accel;
	SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		handler = new Handler();

		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);

		ax = (TextView) findViewById(R.id.accelx);
		ay = (TextView) findViewById(R.id.accely);
		az = (TextView) findViewById(R.id.accelz);
		lat = (TextView) findViewById(R.id.lat);
		lon = (TextView) findViewById(R.id.lon);
		username = (TextView) findViewById(R.id.username);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);

		accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		prefs = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(KEY1, "Jim is the best");
		editor.commit();
	}

	private class SensorWork implements Runnable {

		SensorEvent e;

		SensorWork(SensorEvent event) {
			e = event;
		}

		@Override
		public void run() {
			ax.setText(String.valueOf(e.values[0]));
			ay.setText(String.valueOf(e.values[1]));
			az.setText(String.valueOf(e.values[2]));
		}
	}

	private class LocationWork implements Runnable {

		Location l;

		LocationWork(Location loc) {
			l = loc;
		}

		@Override
		public void run() {
			lat.setText(String.valueOf(l.getLatitude()));
			lon.setText(String.valueOf(l.getLongitude()));
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1337);
			return;
		}
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sm.unregisterListener(this, accel);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1337);
			return;
		}
		lm.removeUpdates(this);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		handler.post(new SensorWork(event));
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onLocationChanged(Location location) {
		handler.post(new LocationWork(location));
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.button)
		{
			username.setText(prefs.getString(KEY1, ":("));
		}
	}
}
