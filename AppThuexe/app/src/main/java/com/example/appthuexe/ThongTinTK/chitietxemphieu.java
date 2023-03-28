package com.example.appthuexe.ThongTinTK;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.appthuexe.R;
import com.example.appthuexe.TrangAdmin.duyetdatxe;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chitietxemphieu extends AppCompatActivity {
    String value1,nlp;
    TextView ngaydat,ngaytra,thoigian,tiencoc,tienxe,duyetx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietxemphieu);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        nlp = intent.getStringExtra("nlp");

        ngaydat= (TextView) this.findViewById(R.id.txtndx);
        ngaytra= (TextView) this.findViewById(R.id.txtntx);
        thoigian= (TextView) this.findViewById(R.id.txttgx);
        tiencoc= (TextView) this.findViewById(R.id.txttcx);
        tienxe= (TextView) this.findViewById(R.id.txttxx);
        duyetx= (TextView) this.findViewById(R.id.txtdx);
        getlist();
    }
    public void getlist()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<PHIEUTHUEXEModel> call = methods.GetPHIEUTHUEXE();
        call.enqueue(new Callback<PHIEUTHUEXEModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXEModel> call, Response<PHIEUTHUEXEModel> response) {
                List<PHIEUTHUEXEModel.Data> data = response.body().getData();

                for(PHIEUTHUEXEModel.Data dt : data){
                    String ngaylap = dt.getThoigian();
                    String tkn = dt.getTaikhoan();
                    if (nlp.equals(ngaylap)&&value1.equals(tkn))
                    {
                        thoigian.setText("Ngày lập: "+dt.getThoigian().toString());
                        ngaydat.setText("Ngày lấy xe: "+dt.getNgaythue().toString());
                        ngaytra.setText("Ngày trả xe: "+dt.getNgaytra().toString());
                        tiencoc.setText("Tiền cọc đã trả: "+dt.getTiencoc().toString());
                        tienxe.setText("Tiền phải trả thêm: "+dt.getTienxe().toString());
                        String duyet = dt.getDuyet().toString();
                        if(duyet.equals("chua"))
                        {
                            duyet = "Phiếu chưa được duyệt";
                        }else if(duyet.equals("daduyet"))
                        {
                            duyet = "Phiếu đã được duyệt";
                        }else if(duyet.equals("huy"))
                            duyet = "Phiếu đã bị hủy và hoàn trả lại tiền";
                        duyetx.setText(duyet);
                    }
                }
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXEModel> call, Throwable t) {

            }
        });

    }
}