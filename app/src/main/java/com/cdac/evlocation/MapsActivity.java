package com.cdac.evlocation;

import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN;
import static com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cdac.evlocation.DetailsActvity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
class evch {
    String chId;
    String name;
    double lat;
    double lon;

    //EV chargers  class constructor
    evch(String Id, String loc, double lt, double ln) {
        chId = Id;
        name = loc;
        lat = lt;
        lon = ln;

    }
};
public class MapsActivity<input, jsonObjectRequest> extends FragmentActivity implements OnMapReadyCallback {
    public  GoogleMap mMap;
    evch[] obj;
    int kk;
    int ii;
    int flag;
    // below are the latitude and longitude
    // of 4 different locations.

    static LatLng mit = new LatLng(26.840979, 75.567548);
    LatLng hawamal = new LatLng(26.924163, 75.826776);
    LatLng sikar = new LatLng(27.609239, 75.141090);
    LatLng jantarmantar = new LatLng(25.925008, 75.824635);
    static  String result =new String();
    // two array list for our lat long and location Name;
    private static ArrayList<LatLng> latLngArrayList;
    private static ArrayList<String> locationNameArraylist;
    private Object response;
    private InputStream in;
    private HttpURLConnection connection;
    public MapsActivity() throws IOException {
    }
    Context cnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        cnt = this;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // initializing our array lists.
        latLngArrayList = new ArrayList<>();
        locationNameArraylist = new ArrayList<>();
        // on below line we are adding
        // data to our array list.
        latLngArrayList.add(mit);
        locationNameArraylist.add("mit");
        latLngArrayList.add(hawamal);
        locationNameArraylist.add("hawamal");
        latLngArrayList.add(sikar);
        locationNameArraylist.add("sikar");
        latLngArrayList.add(jantarmantar);
        locationNameArraylist.add("jantarmantar");

        obj =new evch[4];
        obj[0] = new evch("DC001","mit",26.840979, 75.567548 );
        obj[1] = new evch("DC002","hawamal", 26.924163, 75.826776);
        obj[2] = new evch("DC003","sikar",27.609239, 75.141090);
        obj[3] = new evch("DC004","jantarmantar",25.925008, 75.824635 );
        //display the EV Charger object data


        //getResponse();

    }


    private void getResponse() {
        RequestQueue queue = Volley.newRequestQueue(cnt);


        StringRequest[] request;
        request=new StringRequest[12];
        for(evch temp:obj) {
            flag=-2;
            for( kk=1;kk<=3;kk++) {
                String URL = "http://server.dsreddyconsultancy.co.in:8095/main.php?key=dsreddy&ChargeBoxID=" + temp.chId + "&cmd=getConnectorState&ConnectorID=" + kk;
                //String URL ="https://www.google.com";

                request[ii] = new StringRequest(Request.Method.POST, URL,
                        new com.android.volley.Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                //latLngArrayList.add(name);
                                Toast.makeText(MapsActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                                locationNameArraylist.add(temp.name);

                                mMap.moveCamera(CameraUpdateFactory.newLatLng(mit));
                                if (response.toString().contains("Available")) {
                                    flag=(ii)/3;

                                    Marker mit1 = mMap.addMarker(
                                            new MarkerOptions()
                                                    .position(new LatLng(temp.lat, temp.lon))
                                                    .title(temp.name)
                                                    .snippet("Available")
                                                    .icon(BitmapDescriptorFactory.defaultMarker(HUE_GREEN)));
                                } else {
                                    if(flag!=((ii)/3)) {
                                        Marker mit1 = mMap.addMarker(
                                                new MarkerOptions()
                                                        .position(new LatLng(temp.lat, temp.lon))
                                                        .title(temp.name)
                                                        .snippet("Not Available")
                                                        .icon(BitmapDescriptorFactory.defaultMarker(HUE_RED)));
                                    }}
                            }
                        },

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("Check Error", "Error");
                                Toast.makeText(MapsActivity.this, error.toString(), Toast.LENGTH_LONG).show();

                            }
                        }
                ) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {
                        return new byte[]{};

                    }
                };
                ii++;
            }
        }
        for(int jj=0;jj<12;jj++) {
            request[jj].setRetryPolicy(new DefaultRetryPolicy(15000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_MAX_RETRIES));

            queue.add(request[jj]);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // textChanger.setText("After some delay, it changed to new text");
                }
            }, 2000);
        }

    }


    @Override
    public void  onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String makertitle = marker.getTitle();

                //  Intent i = new Intent(MapsActivity.this, DetailsActvity.class);
                //we are passing  title to new activity

                if (marker.getSnippet().equals("Available"))
                {
                    Intent intent = new Intent(MapsActivity.this, DetailsActvity.class);
                    intent.putExtra("title", makertitle);


                    startActivity(intent);
                }
                else {
                    Toast.makeText(MapsActivity.this,"charging are not available",Toast .LENGTH_SHORT);
                }





                return false;
            }
        });

        // below line is use to move camera.
        getResponse();


        //final LatLng srkrLatLng = new LatLng(16.542688, 81.496886);
        /*final LatLng tanukuLatLng = new LatLng(16.753369, 81.67791);
        Marker tanuku = mMap.addMarker(
                new MarkerOptions()
                        .position(tanukuLatLng)
                        .title("tanuku")
                        .icon(BitmapDescriptorFactory.defaultMarker(HUE_GREEN)));
        final LatLng undiLatLng = new LatLng(17.098794, 81.737195);
        Marker undi = mMap.addMarker(
                new MarkerOptions()
                        .position(undiLatLng)
                        .title("undi"));
        final LatLng kaikaluruLatLng = new LatLng(16.556444, 81.211778);
        Marker kaikaluru = mMap.addMarker(
                new MarkerOptions()
                        .position(kaikaluruLatLng)
                        .title("kaikaluru")
                        .icon(BitmapDescriptorFactory.defaultMarker(HUE_GREEN)));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                String makertitle = marker.getTitle();

                Intent i = new Intent(MapsActivity.this, DetailsActvity.class);

                //we are passing  title to new activity
                i.putExtra("title", makertitle);
                startActivity(i);


                return false;
            }
        });*/
    }
}