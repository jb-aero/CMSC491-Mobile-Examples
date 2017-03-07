package io.github.jb_aero.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {

	public static final int MSG_ACTIVITY = 1;

	public MessengerService() {

	}

	final Messenger myMessenger = new Messenger(new myHandler());

	class myHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what)
			{
				case MSG_ACTIVITY:
					Toast.makeText(getApplicationContext(), "Hello from Activity", Toast.LENGTH_LONG).show();
					break;
				default:
					super.handleMessage(msg);
			}
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return myMessenger.getBinder();
	}
}
