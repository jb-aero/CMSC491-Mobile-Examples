package io.github.jb_aero.midtermprepk;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener, LocationListener, View.OnClickListener {

	EditText accx, accy, accz, lat, lon, username;
	Button login;
	float x, y, z, flat, flon;
	public static String name = "Username";
	Handler handler;
	Sensor accelerometer;
	LocationManager lm;
	SensorManager sm;
	SharedPreferences sp;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		accx = (EditText) findViewById(R.id.editText);
		accy = (EditText) findViewById(R.id.editText2);
		accz = (EditText) findViewById(R.id.editText3);
		lat = (EditText) findViewById(R.id.editText4);
		lon = (EditText) findViewById(R.id.editText5);
		username = (EditText) findViewById(R.id.editText6);
		login = (Button) findViewById(R.id.button);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		sm = (SensorManager) getSystemService(SENSOR_SERVICE);
		if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			// TODO: Consider calling
			//    ActivityCompat#requestPermissions
			// here to request the missing permissions, and then overriding
			//   public void onRequestPermissionsResult(int requestCode, String[] permissions,
			//                                          int[] grantResults)
			// to handle the case where the user grants the permission. See the documentation
			// for ActivityCompat#requestPermissions for more details.
			return;
		}
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		handler = new Handler();
		accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		sp = getPreferences(Context.MODE_PRIVATE);
		editor = sp.edit();
		editor.putString(name, "kate14");
		editor.commit();
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.button:
				String temp = sp.getString(name, ":(;");
				username.setText(temp);
		}
	}

	class AccelWork implements Runnable{

		@Override
		public void run() {
			accx.setText(String.valueOf(x));
			accy.setText(String.valueOf(y));
			accz.setText(String.valueOf(z));
		}
	}

	class LocWork implements Runnable{

		@Override
		public void run() {
			lat.setText(String.valueOf(flat));
			lon.setText(String.valueOf(flon));
		}
	}
	@Override
	public void onSensorChanged(SensorEvent event) {
		x = event.values[0];
		y = event.values[1];
		z = event.values[2];
		handler.post(new AccelWork());
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onLocationChanged(Location location) {
		flat = (float) location.getLatitude();
		flon = (float) location.getLongitude();
		handler.post(new LocWork());
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
}
