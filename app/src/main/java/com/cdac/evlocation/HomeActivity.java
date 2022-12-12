package com.cdac.evlocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;




public class HomeActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button logout;
    Button Maps;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        Maps=findViewById(R.id.Maps);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });


        mAuth=FirebaseAuth.getInstance();
        logout=findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
                finish();

            }
        });

    }





}