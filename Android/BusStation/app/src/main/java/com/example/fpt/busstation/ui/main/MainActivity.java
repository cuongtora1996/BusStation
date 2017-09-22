package com.example.fpt.busstation.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.fpt.busstation.Manifest;
import com.example.fpt.busstation.R;
import com.example.fpt.busstation.data.conn.RestClient;
import com.example.fpt.busstation.ui.base.BaseActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends BaseActivity implements
        MainMvpView, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    private MainMvpPresenter<MainMvpView> mPresenter;
    private Button btTest;
    private long mLastClickTime = 0;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    private static final int PERMISSION_LOCATION = 1;
    private static final int PERMISSION_AUDIO = 2;
    private static final int REQUEST_LOCATION = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("OnCreate","Fire");
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentViewResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInit() {
        Log.d("OnInit","Fire");
        mPresenter = new MainPresenter<>();
        mPresenter.onAttach(this);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btTest = (Button) findViewById(R.id.btTest);
        btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MediaPlayer player = new MediaPlayer();
                    player.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    player.setDataSource("https://s3-ap-southeast-1.amazonaws.com/text2speech-v4/female.0.pro.0e960997780b92b07ac3820b197178c5.mp3");
                    player.prepare();
                    player.start();
                }
                catch (Exception e){

                }
            }
        });
        /*btTest.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    mLastClickTime = SystemClock.elapsedRealtime();

                    mPresenter.startRecordAudio();

                    return true;
                }
                if(event.getAction()==MotionEvent.ACTION_UP){
                    //handle mutiple click make media crash
                    if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                        btTest.setEnabled(false);
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                // Actions to do after 10 seconds
                                mPresenter.stopRecordAudio();
                                btTest.setEnabled(true);
                            }
                        }, 1000);
                    }
                    else {
                        mPresenter.stopRecordAudio();
                        return true;
                    }
                }
                return false;
            }
        });*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("OnMapReady","Fire");
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d("===========>Prepare","BuildGoogleApiClient");
            buildGoogleApiClient();
        } else {
            Log.d("===========>Prepare","RequestPermissionSafely");
            requestPermissionsSafely(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d("OnRequestPermission","Fire");
        switch (requestCode){
            case PERMISSION_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay!
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Log.d("=======>RequestLocation","Granted");
                        Log.d("=============>Prepare","BuildGoogleApiClient");
                        buildGoogleApiClient();
                    }
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    showMessage("Permission Denied");
                    Log.d("=======>RequestLocation","Denied");
                    finish();
                }
                break;
            case PERMISSION_AUDIO:
                if(grantResults.length>0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    // permission was granted, yay!
                    Log.d("=======>RequestAudio","Granted");
                    showMessage("Vui lòng ấn nút để ghi âm");
                }
                else{
                    showMessage("Permission Denied");
                    Log.d("=======>RequestAudio","Denied");
                }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        Log.d("BuildGoogleApiClient","Fire");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    protected void startLocationUpdate(){
        Log.d("StartLocationUpdate","Fire");
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            Log.d("======>","RequestLocationUpdates");
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }
    protected void stopLocationUpdate(){
        Log.d("StopLocationUpdate","Fire");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("OnStart","Fire");
        if(mGoogleApiClient!=null){
            Log.d("======>","PrepareConnectGoogleAPIClient");
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("OnStop","Fire");
        if(mGoogleApiClient!=null){
            Log.d("======>","PrepareDisconnectGoogleAPIClient");
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("OnPause","Fire");
        if(mGoogleApiClient!=null)
            if(mGoogleApiClient.isConnected()) {
                Log.d("======>", "PrepareStopLocationUpdate");
                stopLocationUpdate();
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("OnResume","Fire");
        if(mGoogleApiClient!=null)
            if(mGoogleApiClient.isConnected()){
                Log.d("======>", "PrepareStartLocationUpdate");
                startLocationUpdate();

            }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("OnConnected","Fire");
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);


        //request user to open gps
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.d("======>", "Enabled GPS");
                        Log.d("===========>", "PrepareStartLocationUpdate");
                        startLocationUpdate();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.d("======>", "Not enable GPS. Request GPS");
                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, REQUEST_LOCATION);

                        } catch (IntentSender.SendIntentException e) {
                            Log.d("======>", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.d("======>", "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                        break;
                }
            }
        });

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        Log.d("onActivityResult()", Integer.toString(resultCode));

        //final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode)
        {
            case REQUEST_LOCATION:
                switch (resultCode)
                {
                    case Activity.RESULT_OK:
                    {
                        // All required changes were successfully made
                        break;
                    }
                    case Activity.RESULT_CANCELED:
                    {
                        // The user was asked to change settings, but chose not to
                        finish();
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                break;
        }
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("OnLocationChanged","Fire");
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_bus));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }
}
