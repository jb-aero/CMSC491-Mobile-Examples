package io.github.jb_aero.webservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String latitude = "49.5",
				longitude = "-72.5",
				timestamp = "2017-04-13 13:25:00",
				id = "tom";

		String[] input = new String[4];
		input[0] = latitude;
		input[1] = longitude;
		input[2] = timestamp;
		input[3] = id;

		InvokeWebservice webservice = new InvokeWebservice();
		webservice.execute(input);
	}

	private class InvokeWebservice extends AsyncTask<String,Integer,String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected String doInBackground(String... params) {

			URL url;
			String response = "";
			String requestURL = "http://68.134.23.96/uploaddata.php";

			try
			{
				url = new URL(requestURL);
				HttpURLConnection myconnection = (HttpURLConnection) url.openConnection();
				myconnection.setReadTimeout(15000);
				myconnection.setConnectTimeout(15000);
				myconnection.setRequestMethod("POST");
				myconnection.setDoInput(true);
				myconnection.setDoOutput(true);

				OutputStream os = myconnection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
				StringBuilder str = new StringBuilder();

				str.append("latitude="+params[0]+"&");
				str.append("longitude="+params[1]+"&");
				str.append("timestamp="+params[2]+"&");
				str.append("id="+params[3]);

				String urstr = str.toString();

				writer.write(urstr);
				writer.flush();
				writer.close();

				int responseCode = myconnection.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK)
				{
					String line;
					BufferedReader br = new BufferedReader(new InputStreamReader(myconnection.getInputStream()));

					line = br.readLine();
					while (line != null)
					{
						response += line;
						line = br.readLine();
					}
				}

				myconnection.disconnect();

			} catch (Exception e) {
				e.printStackTrace();
			}

			return null;
		}
	}
}
