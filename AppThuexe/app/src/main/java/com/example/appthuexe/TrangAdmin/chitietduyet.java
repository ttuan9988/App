package com.example.appthuexe.TrangAdmin;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.R;
import com.example.appthuexe.ThongTinTK.ThanhToanMOMO.thanhtoan;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXECallBackModel;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEInsertUpdateDeleteModel;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEModel;
import com.example.lib.Model.TK.TKCallBackModel;
import com.example.lib.Model.TK.TKInsertUpdateDeleteModel;
import com.example.lib.Model.TK.TKModel;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class chitietduyet extends AppCompatActivity {
    Button xembl,huyduyet,duyet;
    TextView tentk,tenxe,bienso,ngaydat,ngaytra,tien;
    ImageView hanh;
    String value1,taik,nlp;
    String mp,nd,nt,tc,tk,bs,tg,tx,bl;
    String mk,quyen,ten,sdt,vitri;
    int tienco = 0;
    int x=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietduyet);
         xembl = (Button) findViewById(R.id.btnanhddx);
         huyduyet = (Button) findViewById(R.id.btnhuyddx);
         duyet = (Button) findViewById(R.id.btnduyetddx);
         tentk = (TextView) findViewById(R.id.txttkthuedx);
         hanh = (ImageView) findViewById(R.id.imgvddx);
         bienso = (TextView) findViewById(R.id.txtbsddx);
         ngaydat = (TextView) findViewById(R.id.txtndddx);
         ngaytra = (TextView) findViewById(R.id.txtntddx);
         tien = (TextView) findViewById(R.id.txttienddx);
        Intent intent = getIntent();
         value1 = intent.getStringExtra("Key_1");
         taik = intent.getStringExtra("taik");
         nlp = intent.getStringExtra("nlp");
        hanh.setVisibility(View.GONE);
        dovao();
    }
    public void dovao()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<PHIEUTHUEXEModel> call = methods.GetPHIEUTHUEXE();
        call.enqueue(new Callback<PHIEUTHUEXEModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXEModel> call, Response<PHIEUTHUEXEModel> response) {
                List<PHIEUTHUEXEModel.Data> data = response.body().getData();
                for(PHIEUTHUEXEModel.Data dt : data){
                    if (taik.equals(dt.getTaikhoan().toString())&&nlp.equals(dt.getThoigian().toString()))
                    {
                        tentk.setText("Tài khoản thuê: "+dt.getTaikhoan().toString());
                        bienso.setText("Biển số xe: "+dt.getBiensoxe());
                        ngaydat.setText("Ngày đặt: "+dt.getNgaythue());
                        ngaytra.setText("Ngày trả: "+dt.getNgaytra());
                        String tienx= dt.getTienxe();
                        String tienc= dt.getTiencoc();
                        tien.setText("Tiền cọc: "+tienc+", Tiền phải trả: "+tienx);
                        bl = dt.getBanglai();
                        byte[] ha = Base64.decode(bl,Base64.DEFAULT);
                        Bitmap bitmap= BitmapFactory.decodeByteArray(ha,0,ha.length);
                        hanh.setImageBitmap(bitmap);
                        // String mp,nd,nt,tc,tk,bs,tg,tx,bl;
                        mp = dt.getMaphieu();
                        nd= dt.getNgaythue();
                        nt= dt.getNgaytra();
                        tc= dt.getTiencoc();
                        tk = dt.getTaikhoan();
                        bs = dt.getBiensoxe();
                        tg= dt.getThoigian();
                        tx= dt.getTienxe();
                    }
                }
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXEModel> call, Throwable t) {

            }
        });
    }


    public void xemanhbl(View view) {
        if(x==1)
        {
            hanh.setVisibility(View.VISIBLE);
            x=2;
        }else
        {
            hanh.setVisibility(View.GONE);
            x=1;
        }
    }

    public void dphieu(View view) {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        PHIEUTHUEXEInsertUpdateDeleteModel tknew = new PHIEUTHUEXEInsertUpdateDeleteModel();
        // String mp,nd,nt,tc,tk,bs,tg,tx,bl;
        tknew.setMaphieu(mp);
        tknew.setNgaythue(nd);
        tknew.setNgaytra(nt);
        tknew.setTiencoc(tc);
        tknew.setTaikhoan(tk);
        tknew.setBiensoxe(bs);
        tknew.setThoigian(tg);
        tknew.setTienxe(tx);
        tknew.setBanglai(bl);
        tknew.setDuyet("daduyet");
        Call<PHIEUTHUEXECallBackModel> call = methods.updatePHIEUTHUEXE(tknew);
        call.enqueue(new Callback<PHIEUTHUEXECallBackModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXECallBackModel> call, Response<PHIEUTHUEXECallBackModel> response) {
                Toast.makeText(chitietduyet.this, "Duyệt thành công ", Toast.LENGTH_LONG).show();
                Intent i=new Intent(chitietduyet.this, duyetdatxe.class);
                i.putExtra("Key_1", value1);
                startActivity(i);
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXECallBackModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Duyệt thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void hphieu(View view) {
        loadtk();
        huy();

    }
    public void huy()
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        PHIEUTHUEXEInsertUpdateDeleteModel tknew = new PHIEUTHUEXEInsertUpdateDeleteModel();
        // String mp,nd,nt,tc,tk,bs,tg,tx,bl;
        tknew.setMaphieu(mp);
        tknew.setNgaythue(nd);
        tknew.setNgaytra(nt);
        tknew.setTiencoc(tc);
        tknew.setTaikhoan(tk);
        tknew.setBiensoxe(bs);
        tknew.setThoigian(tg);
        tknew.setTienxe(tx);
        tknew.setDuyet("huy");
        Call<PHIEUTHUEXECallBackModel> call = methods.updatePHIEUTHUEXE(tknew);
        call.enqueue(new Callback<PHIEUTHUEXECallBackModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXECallBackModel> call, Response<PHIEUTHUEXECallBackModel> response) {
                Toast.makeText(chitietduyet.this, "Hủy thành công ", Toast.LENGTH_LONG).show();
                Intent i=new Intent(chitietduyet.this, duyetdatxe.class);
                i.putExtra("Key_1", value1);
                startActivity(i);
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXECallBackModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Hủy thất bại!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void nap()
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        TKInsertUpdateDeleteModel tknew = new TKInsertUpdateDeleteModel();
        tknew.setTaikhoan(tk);
        tienco= tienco+ Integer.parseInt(tc);
        tknew.setTien(String.valueOf(tienco));
        tknew.setMatkhau(mk);
        tknew.setQuyen(quyen);
        tknew.setTen(ten);
        tknew.setSdt(sdt);
        tknew.setVitri(vitri);
        Call<TKCallBackModel> call = methods.updateTK(tknew);
        call.enqueue(new Callback<TKCallBackModel>() {
            @Override
            public void onResponse(Call<TKCallBackModel> call, Response<TKCallBackModel> response) {

            }
            @Override
            public void onFailure(Call<TKCallBackModel> call, Throwable t) {

            }
        });
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
                    if(tk.equals(tksql)) {
                        tienco =tiensql;
                        mk= dt.getMatkhau();
                        quyen=dt.getQuyen();
                        ten = dt.getTen();
                        sdt = dt.getSdt();
                        vitri = dt.getVitri();
                        nap();

                    }
                }
            }
            @Override
            public void onFailure(Call<TKModel> call, Throwable t) {

            }
        });
    }
}