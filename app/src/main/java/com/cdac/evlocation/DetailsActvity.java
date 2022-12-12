package com.cdac.evlocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DetailsActvity extends AppCompatActivity {
    TextView markertxt,power,current,desions;
    DatabaseReference mydb;
    Button feasible_to_charge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_actvity);
        markertxt = findViewById(R.id.marker);
        //we will get data from our maps activity
        String title = getIntent().getStringExtra("title");
        markertxt.setText(title);
        //add internet permission in manifest


        Button charge = (Button) findViewById(R.id.charge);

        charge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                charge();

            }
        });





        power=(TextView)findViewById(R.id.power);
        current=(TextView)findViewById(R.id.curent);






        mydb= FirebaseDatabase.getInstance().getReference().child("FirebaseIOT");

        try {

            mydb.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    String powerdata = dataSnapshot.child("power").getValue().toString();
                    String curentdata = dataSnapshot.child("current").getValue().toString();
                    power.setText(powerdata);
                    current.setText(curentdata);



                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value

                }
            });
        }

        catch(Exception e)
        {



        }




    }

    private void charge() {

        Intent intent= new Intent(DetailsActvity.this,ButtonActivity.class);

        startActivity(intent);

    }

}