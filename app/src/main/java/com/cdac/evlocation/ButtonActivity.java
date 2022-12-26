package com.cdac.evlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class ButtonActivity extends AppCompatActivity {

    Button START,STOP,STATUS,start1,stop1,status1,start2,stop2,status2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        START=findViewById(R.id.START);
        STOP=findViewById(R.id.STOP);
        STATUS=findViewById(R.id.STATUS);
        start1=findViewById(R.id.start1);
        stop1=findViewById(R.id.stop1);
        status1=findViewById(R.id.status1);
        start2=findViewById(R.id.start2);
        stop2=findViewById(R.id.stop2);
        status2=findViewById(R.id.status2);
        START.setOnClickListener(new View.OnClickListener() {






            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=RemoteStartTransaction&ConnectorID=1&idTag=abc123");

            }

        });
        STOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=RemoteStopTransaction&ConnectorID=1&idTag=abc123");
            }
        });
        STATUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=getConnectorState&ConnectorID=1");
            }
        });
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=RemoteStartTransaction&ConnectorID=2&idTag=abc123");

            }
        });
        stop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=RemoteStopTransaction&ConnectorID=2&idTag=abc123");
            }
        });
        status1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=getConnectorState&ConnectorID=2");
            }
        });
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=RemoteStartTransaction&ConnectorID=3&idTag=abc123");

            }
        });
        stop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=RemoteStopTransaction&ConnectorID=3&idTag=abc123");
            }
        });
        status2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLink("http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=DC001&cmd=getConnectorState&ConnectorID=3");
            }
        });



    }




    private void goLink(String s) {
        Uri uri=Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}