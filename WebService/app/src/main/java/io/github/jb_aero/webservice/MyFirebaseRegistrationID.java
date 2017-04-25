package io.github.jb_aero.webservice;

import android.os.AsyncTask;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.net.HttpURLConnection;

/**
 * Created by jb_ae on 4/18/2017.
 */

public class MyFirebaseRegistrationID extends FirebaseInstanceIdService {

	@Override
	public void onTokenRefresh() {
		super.onTokenRefresh();
		String token = FirebaseInstanceId.getInstance().getToken();
		MainActivity.sendRegistrationToServer(token);
	}

	static class UploadTask extends AsyncTask<String,Integer,String> {

		@Override
		protected String doInBackground(String... params) {
			String response = "";
			String token = params[0];
			HttpURLConnection connection = null;

			try {

			} catch (Exception e) {
				e.printStackTrace();
			}

			return response;
		}
	}
}
