package com.example.appthuexe.TrangAdmin;

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
import com.example.appthuexe.TrangChu;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEModel;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class duyetdatxe extends AppCompatActivity {
    String value1;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyetdatxe);
        lv= this.findViewById(R.id.listdsdatxe);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        getlist();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                TextView ngaylap = (TextView) view.findViewById(R.id.bienso);
                String nlp= (String) ngaylap.getText();
                Scanner sc = new Scanner(nlp);
                sc.useDelimiter(": ");
                sc.next();
                String nlpm = sc.next();
                TextView taikhoan = (TextView) view.findViewById(R.id.tenxe);
                String taik= (String) taikhoan.getText();
                Scanner sc2 = new Scanner(taik);
                sc2.useDelimiter(": ");
                sc2.next();
                String taikn = sc2.next();
                Intent i=new Intent(duyetdatxe.this, chitietduyet.class);
                i.putExtra("Key_1", value1);
                i.putExtra("taik", taikn);
                i.putExtra("nlp", nlpm);
                startActivity(i);
            }});
    }
    public void getlist()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<PHIEUTHUEXEModel> call = methods.GetPHIEUTHUEXE();
        call.enqueue(new Callback<PHIEUTHUEXEModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXEModel> call, Response<PHIEUTHUEXEModel> response) {
                List<PHIEUTHUEXEModel.Data> data = response.body().getData();

                List<Map<String,String>> datalist = null;
                datalist = new ArrayList<Map<String,String>>();
                for(PHIEUTHUEXEModel.Data dt : data){
                    String duyet = dt.getDuyet();
                    if (duyet.equals("chua"))
                    {
                        String tentk =dt.getTaikhoan();
                        String tgt =dt.getThoigian();
                        String nd =dt.getNgaythue();
                        String nt = dt.getNgaytra();
                        Map<String,String> dtname= new HashMap<String,String>();
                        dtname.put("a","Tài khoản: "+tentk);
                        dtname.put("b","Ngày lập phiếu: "+tgt);
                        dtname.put("c","Thuê từ ngày "+nd+" đến "+nt);
                        datalist.add(dtname);
                        String[] fromw = {"a","b","c"};
                        int[] tow = {R.id.tenxe,R.id.bienso,R.id.gia};
                        SimpleAdapter ad = new SimpleAdapter(duyetdatxe.this,datalist,R.layout.listxe,fromw,tow);
                        lv.setAdapter(ad);
                    }
                }
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXEModel> call, Throwable t) {

            }
        });

    }

    public void home(View view) {
        Intent i=new Intent(duyetdatxe.this, TrangChu.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
}
