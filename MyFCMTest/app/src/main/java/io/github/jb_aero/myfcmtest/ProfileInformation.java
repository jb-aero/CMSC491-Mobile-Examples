package io.github.jb_aero.myfcmtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInformation extends AppCompatActivity implements View.OnClickListener {

	private DatabaseReference mydb, mydbchildusers;
	Button saveToDatabase;
	EditText name, address, phoneNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile_information);

		name = (EditText) findViewById(R.id.proName);
		address = (EditText) findViewById(R.id.proAddress);
		phoneNumber = (EditText) findViewById(R.id.proPhone);
		saveToDatabase = (Button) findViewById(R.id.proSub);

		mydb = FirebaseDatabase.getInstance().getReference();
		mydbchildusers = mydb.child("users");

		saveToDatabase.setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
		ValueEventListener eventListener = new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				User tmp = dataSnapshot.getValue(User.class);
				if (tmp != null)
				{
					Log.d("REALTIMEDB", tmp.mAddress);
				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		};

		mydbchildusers.child("nilanb").addValueEventListener(eventListener);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId())
		{
			case R.id.proSub:
				User myUser = new User(name.getText().toString(), address.getText().toString(), phoneNumber.getText().toString());
				mydbchildusers.child(name.getText().toString()).setValue(myUser);
				break;
		}
	}
}
