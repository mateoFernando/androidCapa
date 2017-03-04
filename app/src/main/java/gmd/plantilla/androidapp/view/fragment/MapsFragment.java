package gmd.plantilla.androidapp.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import gmd.plantilla.androidapp.R;
import gmd.plantilla.androidapp.domain.model.Local;
import gmd.plantilla.androidapp.domain.ro.response.LocalesResponse;
import gmd.plantilla.androidapp.domain.ro.response.LoginResponse;
import gmd.plantilla.androidapp.service.business.LocalesService;
import gmd.plantilla.androidapp.service.business.impl.LocalesServiceImpl;
import gmd.plantilla.androidapp.util.Constants;
import gmd.plantilla.androidapp.util.PermissionChecker;
import gmd.plantilla.androidapp.view.activity.LoginActivity;
import gmd.plantilla.androidapp.view.activity.PrincipalActivity;

public class MapsFragment extends Fragment implements OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener,
    LocationListener{

    private MapView mapView;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Location location;
    private LocationRequest mLocationRequest;

    public MapsFragment() {
        // Required empty public constructor
    }


    public static MapsFragment newInstance(String tipo) {
        Bundle args = new Bundle();

        args.putString("tipo", tipo);
        MapsFragment fragment = new MapsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void localesCallback(LocalesResponse response) {

        for(Local local : response.getData()){
            LatLng point = new LatLng(Double.parseDouble(local.getLatitud()), Double.parseDouble(local.getLongitud()));
            mMap.addMarker(new MarkerOptions().position(point)
                    .title(local.getDescripcion())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        mapView = (MapView) view.findViewById(R.id.map_stack);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

    }


    @Override
    public void onActivityCreated(Bundle savedInstance) {
        super.onActivityCreated(savedInstance);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(PermissionChecker.isGpsEnable(getActivity())){
            location = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            mMap.setMyLocationEnabled(true);
            if(location != null){
                goToMyPosition();
            }

            mLocationRequest = new LocationRequest();
            mLocationRequest.setInterval(3000); //3 seconds
            mLocationRequest.setFastestInterval(1000); //1 seconds
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
        else{
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Constants.PERMISSIONS.ACCESS_FINE_LOCATION);
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

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        mMap.clear();
        LocalesService localesService = new LocalesServiceImpl();
        localesService.loadlocales();

        Log.d("MAP READY","MAP READY");
    }


    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void goToMyPosition() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(Constants.VALUES.ZOOM_MAPA_DEFAULT)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    public void goToDefaultPosition() {
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(Constants.VALUES.LATITUDE_POSITION_DEFAULT, Constants.VALUES.LONGITUDE_POSITION_DEFAULT))
                .zoom(Constants.VALUES.ZOOM_MAPA_DEFAULT)
                .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Constants.PERMISSIONS.ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mGoogleApiClient.reconnect();

                } else {

                    goToDefaultPosition();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
