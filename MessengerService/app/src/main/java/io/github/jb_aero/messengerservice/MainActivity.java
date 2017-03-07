package io.github.jb_aero.messengerservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	public static String key1 = "Username";
	Messenger myMessenger;
	private boolean bound;
	Button button, button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bound = false;

		SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key1, "jb_aero");
		editor.commit();

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(this);
		button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(this);

		Intent intent = new Intent(this, Messenger.class);
		bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

		Intent intent1 = new Intent(this, MyIntentService.class);
		startService(intent1);
	}

	private ServiceConnection myConnection = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			myMessenger = new Messenger(service);
			bound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			bound = false;
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.button:
				if (bound)
				{
					Message msg = Message.obtain(null, MessengerService.MSG_ACTIVITY, 0, 0);
					try {
						myMessenger.send(msg);
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				break;
			case R.id.button2:
				SharedPreferences sharedPreferences_ = getPreferences(Context.MODE_PRIVATE);
				String username = sharedPreferences_.getString(key1, "Anon");
				Toast.makeText(getApplicationContext(), "Username is " + username, Toast.LENGTH_LONG).show();
				break;
		}
	}
}
