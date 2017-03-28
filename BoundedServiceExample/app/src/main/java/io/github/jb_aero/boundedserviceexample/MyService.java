package io.github.jb_aero.boundedserviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

	MyBinder myBinder = new MyBinder();

	public MyService() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		return myBinder;
	}

	public class MyBinder extends Binder {

		MyService getService() {
			return MyService.this;
		}
	}
}
