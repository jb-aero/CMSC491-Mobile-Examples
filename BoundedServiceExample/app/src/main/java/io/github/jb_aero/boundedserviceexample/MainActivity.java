package io.github.jb_aero.boundedserviceexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	private MyService myService;
	private boolean bound;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bound = false;
		Intent intent = new Intent(this, MyService.class);
		bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
	}

	private ServiceConnection myConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MyService.MyBinder binder_ = (MyService.MyBinder) service;
			myService = binder_.getService();
			bound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	};
}
