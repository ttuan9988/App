package com.example.appthuexe.TrangAdmin.Dinhvi;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.R;
import com.example.appthuexe.TrangAdmin.chinhsuaOTO;
import com.example.appthuexe.TrangAdmin.themOTO;
import com.example.appthuexe.vitrix;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.XETHUE.XETHUEModel;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dsxe extends AppCompatActivity {
    String value1;
    ListView lv;
    private LocationRequest locationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsxe);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        lv= this.findViewById(R.id.listdsdv);
        gpsdangbat();
        getlist();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView bienso = (TextView) view.findViewById(R.id.bienso);
                String bs= (String) bienso.getText();
                Scanner sc = new Scanner(bs);
                sc.useDelimiter(": ");
                sc.next();
                String bsn = sc.next();
                Intent i=new Intent(dsxe.this, vitrix.class);
                i.putExtra("Key_1", value1);
                i.putExtra("bs", bsn);
                startActivity(i);
            }});
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
                        SimpleAdapter ad = new SimpleAdapter(dsxe.this,datalist,R.layout.listxe,fromw,tow);
                        lv.setAdapter(ad);
                    }

                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {

            }
        });
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
                    Toast.makeText(dsxe.this, "GPS is already tured on", Toast.LENGTH_SHORT).show();

                } catch (ApiException e) {

                    switch (e.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                resolvableApiException.startResolutionForResult(dsxe.this, 2);
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