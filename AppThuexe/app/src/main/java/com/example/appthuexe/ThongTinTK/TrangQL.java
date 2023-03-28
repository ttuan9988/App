package com.example.appthuexe.ThongTinTK;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.R;
import com.example.appthuexe.ThongTinTK.ThanhToanMOMO.thanhtoan;
import com.example.appthuexe.TrangChu;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.TK.TKModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangQL extends AppCompatActivity {
    String value1;
    TextView tienhienco;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ql);
        TextView ten = this.findViewById(R.id.lbten4) ;
        tienhienco = this.findViewById(R.id.txtsodusqltk3) ;
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        ten.setText(value1);
        loadtk();
    }
    public void naptien(View view)
    {
        Intent i = new Intent(TrangQL.this,NapTien.class);
        String temp = String.valueOf(value1);
        i.putExtra("Key_1", temp);
        startActivity(i);
    }
    public void loadtk()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<TKModel> call = methods.GetTK();
        call.enqueue(new Callback<TKModel>() {
            @Override
            public void onResponse(Call<TKModel> call, Response<TKModel> response) {
                List<TKModel.Data> data = response.body().getData();

                for(TKModel.Data dt : data){
                    String tksql =dt.getTaikhoan();
                    int tiensql = Integer.parseInt(dt.getTien());
                    if(value1.equals(tksql)) {
                        tienhienco.setText("Số dư tài khoản là "+tiensql);
                    }
                }
            }

            @Override
            public void onFailure(Call<TKModel> call, Throwable t) {

            }
        });
    }

    public void vetrgc(View view) {
        Intent i = new Intent(TrangQL.this, TrangChu.class);
        String temp = String.valueOf(value1);
        i.putExtra("Key_1", temp);
        startActivity(i);
    }
}