package io.github.jb_aero.simplelogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @authors James Bilbrey, Kately Seitz
 */
public class ProfileActivity extends AppCompatActivity {

	FirebaseAuth mauth;
	DatabaseReference mydb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		mauth = FirebaseAuth.getInstance();
		mydb = FirebaseDatabase.getInstance().getReference().child("icaUsers");

		mydb.child(User.emailToDB(mauth.getCurrentUser().getEmail())).addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				User user = dataSnapshot.getValue(User.class);
				TextView name = (TextView) findViewById(R.id.textView);
				name.setText(user.name);
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}
}
