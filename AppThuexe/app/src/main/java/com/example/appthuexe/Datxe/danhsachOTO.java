package com.example.appthuexe.Datxe;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.appthuexe.R;
import com.example.appthuexe.TrangAdmin.chinhsuaOTO;
import com.example.appthuexe.TrangAdmin.themOTO;
import com.example.appthuexe.TrangChu;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class danhsachOTO extends AppCompatActivity {
    ListView lv;
    String value1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsach_oto);
        lv= this.findViewById(R.id.listxedat);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
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
                Intent i=new Intent(danhsachOTO.this, Chitietxe.class);
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
                        SimpleAdapter ad = new SimpleAdapter(danhsachOTO.this,datalist,R.layout.listxe,fromw,tow);
                        lv.setAdapter(ad);
                    }

                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {

            }
        });
    }

    public void home1(View view) {
        Intent i=new Intent(danhsachOTO.this, TrangChu.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
}