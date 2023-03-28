package com.example.appthuexe.ThongTinTK;

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
import com.example.appthuexe.TrangAdmin.chitietduyet;
import com.example.appthuexe.TrangAdmin.duyetdatxe;
import com.example.appthuexe.TrangChu;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class xemphieu extends AppCompatActivity {
    String value1;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xemphieu);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        lv= this.findViewById(R.id.listxemphieu);
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
                Intent i=new Intent(xemphieu.this, chitietxemphieu.class);
                i.putExtra("Key_1", value1);
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
                    String tentk =dt.getTaikhoan();
                    if (tentk.equals(value1))
                    {
                        String duyet =dt.getDuyet();
                        String tgt =dt.getThoigian();
                        String nd =dt.getNgaythue();
                        String nt = dt.getNgaytra();
                        Map<String,String> dtname= new HashMap<String,String>();
                        if(duyet.equals("chua"))
                        {
                            duyet = "Phiếu chưa được duyệt";
                        }else if(duyet.equals("daduyet"))
                        {
                            duyet = "Phiếu đã được duyệt";
                        }else if(duyet.equals("huy"))
                            duyet = "Phiếu đã bị hủy và hoàn trả lại tiền";

                        dtname.put("a",duyet);
                        dtname.put("b","Ngày lập phiếu: "+tgt);
                        dtname.put("c","Thuê từ ngày "+nd+" đến "+nt);
                        datalist.add(dtname);
                        String[] fromw = {"a","b","c"};
                        int[] tow = {R.id.tenxe,R.id.bienso,R.id.gia};
                        SimpleAdapter ad = new SimpleAdapter(xemphieu.this,datalist,R.layout.listxe,fromw,tow);
                        lv.setAdapter(ad);
                    }
                }
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXEModel> call, Throwable t) {

            }
        });

    }

    public void backk(View view) {
        Intent i=new Intent(xemphieu.this, TrangChu.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
}