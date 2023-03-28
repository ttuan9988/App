package com.example.appthuexe;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.Datxe.danhsachOTO;
import com.example.appthuexe.ThongTinTK.NapTien;
import com.example.appthuexe.ThongTinTK.TrangQL;
import com.example.appthuexe.ThongTinTK.xemphieu;
import com.example.appthuexe.TrangAdmin.chinhsuaOTO;
import com.example.appthuexe.TrangAdmin.trgAdmin;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.TK.TKModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChu extends AppCompatActivity {
    TextView ten,tx,thongbao;
    ImageButton btnadmin;
    String value1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
         ten = this.findViewById(R.id.lbten) ;
         tx = this.findViewById(R.id.textView8) ;
         thongbao = this.findViewById(R.id.txtsltb) ;
         btnadmin =(ImageButton) this.findViewById(R.id.btnadmin);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        ten.setText(value1);
        tx.setVisibility(View.GONE);
        btnadmin.setVisibility(View.GONE);
        kiemtraadmin();
    }
    public void kiemtraadmin()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<TKModel> call = methods.GetTK();
        call.enqueue(new Callback<TKModel>() {
            @Override
            public void onResponse(Call<TKModel> call, Response<TKModel> response) {
                List<TKModel.Data> data = response.body().getData();
                for(TKModel.Data dt : data){
                    String tksql =dt.getTaikhoan();
                    String quyen =dt.getQuyen();
                    if(value1.equals(tksql)&&quyen.equals("admin"))
                    {
                        tx.setVisibility(View.VISIBLE);
                        btnadmin.setVisibility(View.VISIBLE);
                    }
                }
            }
            @Override
            public void onFailure(Call<TKModel> call, Throwable t) {

            }
        });
    }
    public void thongtintk(View view)
    {
        Intent i = new Intent(TrangChu.this, TrangQL.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
    public void vaoqladmin(View view)
    {
        Intent i=new Intent(TrangChu.this, trgAdmin.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
    public void xemdsxeoto(View view)
    {
        Intent i=new Intent(TrangChu.this, danhsachOTO.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
    public void xemp(View view)
    {
        Intent i=new Intent(TrangChu.this, xemphieu.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
    public void dangxuat(View view)
    {
        Intent i=new Intent(TrangChu.this, MainActivity.class);
        startActivity(i);
    }
}