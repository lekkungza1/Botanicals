package mainairai.rhere.com.liveat500px.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import mainairai.rhere.com.liveat500px.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.InfoWindowAdapter {

    private GoogleMap mMap;
    //TreeItemDao item;
    Button btnChangeType;
    Double lat;
    Double lng;
    String url = "https://www.w3schools.com/css/img_fjords.jpg";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       // TreeItemDao dao = getIntent().getParcelableExtra("dao");
       // item= dao;
       // initInstance();
    }

    private void initInstance() {
        btnChangeType = (Button)findViewById(R.id.changeMapType);
        btnChangeType.setOnClickListener(onChangeMapTypeClicked);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
        //return prepareInfoView(marker);
    }

    @Override
    public View getInfoContents(Marker marker) {
        //return null;
        return prepareInfoView(marker);

    }

    private View prepareInfoView(Marker marker){
        //prepare InfoView programmatically
        LinearLayout infoView = new LinearLayout(MapsActivity.this);
        LinearLayout.LayoutParams infoViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        infoView.setOrientation(LinearLayout.HORIZONTAL);
        infoView.setLayoutParams(infoViewParams);

      // ImageView infoImageView = new ImageView(MapsActivity.this);


        ImageView infoImageView = new ImageView(MapsActivity.this);


            //TODO Load image from URL
           /* Glide.with(this)
                    .load(url)
                    .into(infoImageView);*/
       //TODO Load image from drawable
        Drawable drawable = getResources().getDrawable(android.R.drawable.ic_dialog_map);
        infoImageView.setImageDrawable(drawable);

        infoView.addView(infoImageView);



        LinearLayout subInfoView = new LinearLayout(MapsActivity.this);
        LinearLayout.LayoutParams subInfoViewParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        subInfoView.setOrientation(LinearLayout.VERTICAL);
        subInfoView.setLayoutParams(subInfoViewParams);

        TextView subInfoName = new TextView(MapsActivity.this);
        subInfoName.setText("Title"+marker.getId());
        TextView subInfoSciName = new TextView(MapsActivity.this);
        subInfoSciName.setText("Sub content");
        subInfoView.addView(subInfoName);
        subInfoView.addView(subInfoSciName);
        infoView.addView(subInfoView);

        return infoView;
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setInfoWindowAdapter(this);

        for (int i=0;i<10;i++) {
            // Location loc1 = item.getLocations().get(i1);
            Double lat = 1.1;
            Double lng = 1.1;
           // Log.e(">>>>>"+item.getLocations().size()+"  Lat"+lat1,"Long"+lng1+"<<<<<<<");
            MarkerOptions marker1 = new MarkerOptions().position(new LatLng(lat+i, lng+i));//*.title(item.getName());*//*
            mMap.addMarker(marker1);
        }

        /*for (int i=0;i<item.getLocations().size();i++) {
            // Location loc1 = item.getLocations().get(i1);
            Double lat1 = Double.parseDouble(item.getLocations().get(i).getLat());
            Double lng1 = Double.parseDouble(item.getLocations().get(i).getLng());
           // Log.e(">>>>>"+item.getLocations().size()+"  Lat"+lat1,"Long"+lng1+"<<<<<<<");
            MarkerOptions marker1 = new MarkerOptions().position(new LatLng(lat1, lng1));*//*.title(item.getName());*//*
            mMap.addMarker(marker1);
        }*/

    }




    View.OnClickListener onChangeMapTypeClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(mMap.getMapType()==1){
                //Hybrid Mode
                mMap.setMapType(4);
            }else{
                //Normal Mode
                mMap.setMapType(1);
            }


        }
    };


}
