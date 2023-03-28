package com.example.appthuexe.TrangAdmin.Dinhvi;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.R;
import com.example.appthuexe.vitrix;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.XETHUE.XETHUEModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class guidvxe extends AppCompatActivity {
    Double vtlat,vtlon;
    HubConnection hubConnection;
    private LocationRequest locationRequest;
    TextView vtht;
    ListView lv;
    String bsn="";

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            guidi();
            timerHandler.postDelayed(this, 500);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidvxe);
        lv= this.findViewById(R.id.listdsbat);
        hubConnection = HubConnectionBuilder.create("http://ttuan9988-001-site1.ctempurl.com/chatHub").build();
        vtht= this.findViewById(R.id.txtguivt);
        getlist();
        if(hubConnection.getConnectionState() == HubConnectionState.DISCONNECTED){
            hubConnection.start();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView bienso = (TextView) view.findViewById(R.id.bienso);
                String bs= (String) bienso.getText();
                Scanner sc = new Scanner(bs);
                sc.useDelimiter(": ");
                sc.next();
                bsn = sc.next();
                vtht.setText("Bật vị trí xe: "+bsn);
            }});
    }
    public void guivitri(View view)
    {
        if(!bsn.equals(""))
        {
            timerHandler.postDelayed(timerRunnable, 0);
        }
    }
    public void guidi()
    {
        locationRequest = com.google.android.gms.location.LocationRequest.create();
        locationRequest.setPriority(com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        layvitri();
    }
    public void getlist()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<XETHUEModel> call = methods.GetXETHUE();
        call.enqueue(new Callback<XETHUEModel>() {
            @Override
            public void onResponse(Call<XETHUEModel> call, Response<XETHUEModel> response) {
                List<XETHUEModel.Data> data = response.body().getData();

                List<Map<String,String>> datalist = null;
                datalist = new ArrayList<Map<String,String>>();
                for(XETHUEModel.Data dt : data){
                    String xe = dt.getXe();
                    if (xe.equals("2"))
                    {
                        String tenxe =dt.getTenxe();
                        String bsx =dt.getBiensoxe();
                        String gia =dt.getGiaxe();
                        Map<String,String> dtname= new HashMap<String,String>();
                        dtname.put("a",tenxe);
                        dtname.put("b","Biển số xe: "+bsx);
                        dtname.put("c","Giá thuê: "+gia);
                        datalist.add(dtname);
                        String[] fromw = {"a","b","c"};
                        int[] tow = {R.id.tenxe,R.id.bienso,R.id.gia};
                        SimpleAdapter ad = new SimpleAdapter(guidvxe.this,datalist,R.layout.listxe,fromw,tow);
                        lv.setAdapter(ad);
                    }

                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {

            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if (gpsdangbat()) {

                    layvitri();

                }else {

                    batgps();
                }
            }
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {

                layvitri();
            }
        }
    }
    private void layvitri() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(guidvxe.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                if (gpsdangbat()) {

                    LocationServices.getFusedLocationProviderClient(guidvxe.this)
                            .requestLocationUpdates(locationRequest, new LocationCallback() {
                                @Override
                                public void onLocationResult(@NonNull LocationResult locationResult) {
                                    super.onLocationResult(locationResult);

                                    LocationServices.getFusedLocationProviderClient(guidvxe.this)
                                            .removeLocationUpdates(this);

                                    if (locationResult != null && locationResult.getLocations().size() >0){

                                        int index = locationResult.getLocations().size() - 1;
                                        vtlat = locationResult.getLocations().get(index).getLatitude();
                                        vtlon = locationResult.getLocations().get(index).getLongitude();
                                        vtht.setText(vtlat+", "+vtlon);
                                        String vitri= vtlat+", "+vtlon;
                                        hubConnection.send("SendMessage",bsn,vitri);
                                    }
                                }
                            }, Looper.getMainLooper());

                } else {
                    batgps();
                }

            } else {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }
    private void batgps() {
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response = task.getResult(ApiException.class);
                    Toast.makeText(guidvxe.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(guidvxe.this, 2);
                            } catch (IntentSender.SendIntentException ex) {
                                ex.printStackTrace();
                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //Device does not have location
                            break;
                    }
                }
            }
        });

    }
    private boolean gpsdangbat() {
        LocationManager locationManager = null;
        boolean isEnabled = false;

        if (locationManager == null) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }

        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;

    }
}