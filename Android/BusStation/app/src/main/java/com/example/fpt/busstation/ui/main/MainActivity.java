package com.example.fpt.busstation.ui.main;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.fpt.busstation.R;
import com.example.fpt.busstation.service.AnchorSheetBehavior;
import com.example.fpt.busstation.ui.base.BaseActivity;
import com.example.fpt.busstation.ui.behaviorbottom.ParentViewPagerFragment;
import com.example.fpt.busstation.ui.behaviorbottom.TestFragment.TestFirstFragment;
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

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends BaseActivity implements
        MainMvpView, OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {


    private AnchorSheetBehavior mBottomSheetBehavior;
    private MainMvpPresenter<MainMvpView> mPresenter;
    private Button btTest;
    private EditText etTest;
    private long mLastClickTime = 0;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private LocationRequest mLocationRequest;
    private static final int PERMISSION_LOCATION = 1;
    private static final int PERMISSION_AUDIO = 2;
    private static final int REQUEST_LOCATION = 1;
    private static final int REQUEST_SPEECH_INPUT = 2;
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
                startRecognizeSpeech();
            }
        });
        /*btTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("sendrequest");
                mPresenter.sendRequest(mLastLocation);
            }
        });*/
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .add(R.id.bottom_sheet, new ParentViewPagerFragment() )
                .commit();
        mBottomSheetBehavior = AnchorSheetBehavior.from(findViewById(R.id.bottom_sheet));
        mBottomSheetBehavior.setState(AnchorSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehavior.setHideable(true);

        mBottomSheetBehavior.setState(AnchorSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setAnchorSheetCallback(new AnchorSheetBehavior.AnchorSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case AnchorSheetBehavior.STATE_COLLAPSED:
                        Log.d("BottomSheetBehavior", "State Collapsed");
                        break;
                    case AnchorSheetBehavior.STATE_DRAGGING:
                        Log.d("BottomSheetBehavior", "State Dragging");
                        break;
                    case AnchorSheetBehavior.STATE_EXPANDED:
                        Log.d("BottomSheetBehavior", "State Expanded");
                        break;
                    case AnchorSheetBehavior.STATE_HIDDEN:
                        Log.d("BottomSheetBehavior", "State Hidden");
                        break;
                    case AnchorSheetBehavior.STATE_SETTLING:
                        Log.d("BottomSheetBehavior", "State Settling");
                        break;
                    case AnchorSheetBehavior.STATE_ANCHOR:
                        Log.d("BottomSheetBehavior","State Anchor");
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

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
    public void startRecognizeSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.prompt_speech_input));
        try {
            startActivityForResult(intent, REQUEST_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            showMessage("Not Support Speech");
        }
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
        mLocationRequest.setInterval(20000);
        mLocationRequest.setFastestInterval(20000);
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
            case REQUEST_SPEECH_INPUT:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   // showMessage(result.get(0));
                    mPresenter.sendTTSRequest(result.get(0));
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
        mLastLocation = location;
        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(16));
    }

    @Override
    public void placeStation(double lng, double lat, String address) {

        LatLng latLng = new LatLng(lat, lng);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title(address);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_station));
        mMap.addMarker(markerOptions);
    }

    @Override
    public void showBottomSheet() {
        mBottomSheetBehavior.setState(mBottomSheetBehavior.STATE_ANCHOR);
        mBottomSheetBehavior.setPeekHeight(150);
    }
}
