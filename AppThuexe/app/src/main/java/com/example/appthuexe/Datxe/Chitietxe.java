package com.example.appthuexe.Datxe;

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

import com.example.appthuexe.R;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chitietxe extends AppCompatActivity {
    ImageView imageView;
    Button datxe;
    String value1,bienso;
    TextView tenxe,loaixe,tenbienso,dangkiem,tinhtrang,giathue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietxe);
         imageView = (ImageView) findViewById(R.id.imgct);
         tenxe = (TextView) findViewById(R.id.txttenxect);
         loaixe = (TextView) findViewById(R.id.txtloaict);
         tenbienso = (TextView) findViewById(R.id.txtbiensoct);
         dangkiem = (TextView) findViewById(R.id.txtdangkiemct);
         tinhtrang = (TextView) findViewById(R.id.txttinhtrangct);
         giathue = (TextView) findViewById(R.id.txtgiathuect);
        datxe = (Button) findViewById(R.id.btndatct);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        bienso = intent.getStringExtra("bs");
        getxe();
    }
    public void dattx(View view)
    {
        Intent i=new Intent(Chitietxe.this, trgdatxe.class);
        i.putExtra("Key_1", value1);
        i.putExtra("bs", bienso);
        startActivity(i);
    }
    public void getxe()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<XETHUEModel> call = methods.GetXETHUE();
        call.enqueue(new Callback<XETHUEModel>() {
            @Override
            public void onResponse(Call<XETHUEModel> call, Response<XETHUEModel> response) {
                List<XETHUEModel.Data> data = response.body().getData();
                for(XETHUEModel.Data dt : data){
                    String bsx = dt.getBiensoxe();
                    if (bsx.equals(bienso))
                    {
                        tenxe.setText("- Tên xe: "+dt.getTenxe().toString());
                        loaixe.setText("- Loại xe: "+dt.getLoaixe().toString());
                        tenbienso.setText("- Biển số xe: "+bienso);
                        tinhtrang.setText("- Tình trạng xe: "+dt.getTinhtrang().toString());
                        giathue.setText("- Giá thuê: "+dt.getGiaxe().toString());
                        dangkiem.setText("- Thời hạn đăng kiểm: "+dt.getKiemdinh().toString());
                        String hinhanh = dt.getHinhanh().toString();
                        byte[] ha = Base64.decode(hinhanh,Base64.DEFAULT);
                        Bitmap bitmap= BitmapFactory.decodeByteArray(ha,0,ha.length);
                        imageView.setImageBitmap(bitmap);
                    }

                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {

            }
        });
    }
}