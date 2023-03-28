package com.example.appthuexe.TrangAdmin;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.appthuexe.R;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.XETHUE.XETHUECallBackModel;
import com.example.lib.Model.XETHUE.XETHUEInsertUpdateDeleteModel;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class themOTO extends AppCompatActivity {
    String value1,mx;
    ImageView hanh;
    Button themxe,sua,xoa;
    EditText tenxe,bienso,giaxe,tinhtrang,kiemdinh,thaynhot;
    RadioButton rtudong,rsosan;
    ConstraintLayout chonngay;
    DatePicker ngaypick;
    String loai="",bs;
    final int REQUEST_CODE_GALLERY = 999;
    int q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_oto);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        loai = intent.getStringExtra("loai");
        hanh = (ImageView) this.findViewById(R.id.imganhxethem);
        tenxe = (EditText) this.findViewById(R.id.txtxethem);
        bienso = (EditText) this.findViewById(R.id.txtbsthem);
        giaxe = (EditText) this.findViewById(R.id.txtgiathem);
        tinhtrang = (EditText) this.findViewById(R.id.txttinhtrangthem);
        kiemdinh = (EditText) this.findViewById(R.id.txtdkthem);
        thaynhot = (EditText) this.findViewById(R.id.txttnthem);
        rtudong = (RadioButton) this.findViewById(R.id.rbtudong);
        rsosan= (RadioButton) this.findViewById(R.id.rbsosan);
        ngaypick= (DatePicker) this.findViewById(R.id.datethem);
        chonngay= this.findViewById(R.id.ngaythem);
        chonngay.setVisibility(View.GONE);
        themxe = (Button) this.findViewById(R.id.btnthemxe);
        sua = (Button) this.findViewById(R.id.btnsuathem);
        xoa = (Button) this.findViewById(R.id.btnxoaoto);

        if(loai.equals("2"))
        {
            themxe.setVisibility(View.GONE);
            bs = intent.getStringExtra("bs");
            bienso.setFocusable(false);
            bienso.setEnabled(false);
            capnhat();
        }else {
            sua.setVisibility(View.GONE);
            xoa.setVisibility(View.GONE);
            rtudong.setChecked(true);
        }
    }
    public void capnhat()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<XETHUEModel> call = methods.GetXETHUE();
        call.enqueue(new Callback<XETHUEModel>() {
            @Override
            public void onResponse(Call<XETHUEModel> call, Response<XETHUEModel> response) {
                List<XETHUEModel.Data> data = response.body().getData();
                for(XETHUEModel.Data dt : data){
                    String bsx =dt.getBiensoxe();
                    if(bs.equals(bsx))
                    {
                        tenxe.setText(dt.getTenxe());
                        bienso.setText(bs);
                        giaxe.setText(dt.getGiaxe());
                        tinhtrang.setText(dt.getTinhtrang());
                        kiemdinh.setText(dt.getKiemdinh());
                        thaynhot.setText(dt.getHanthaynhot());
                        String loaix = dt.getLoaixe();
                        if(loaix.equals("Xe số tự động")) rtudong.setChecked(true);else if(loaix.equals("Xe số sàn")) rsosan.setChecked(true);
                        byte[] ha = Base64.decode(dt.getHinhanh(),Base64.DEFAULT);
                        Bitmap bitmap= BitmapFactory.decodeByteArray(ha,0,ha.length);
                        hanh.setImageBitmap(bitmap);
                        laymaxe();
                    }
                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {

            }
        });
    }
    public void chondk(View view) {
        chonngay.setVisibility(View.VISIBLE);
        q=1;
    }
    public void chonnhot(View view) {
        chonngay.setVisibility(View.VISIBLE);
        q=2;
    }
    public void okngay(View view) {
        chonngay.setVisibility(View.GONE);
        if(q==1)
        {
            int day = ngaypick.getDayOfMonth();
            int month = ngaypick.getMonth();
            month=month+1;
            int year =  ngaypick.getYear();
            kiemdinh.setText(day+"/"+month+"/"+year);
        }else
        if(q==2)
        {
            int day = ngaypick.getDayOfMonth();
            int month = ngaypick.getMonth();
            month=month+1;
            int year =  ngaypick.getYear();
            thaynhot.setText(day+"/"+month+"/"+year);
        }
    }
    public void chonanh(View view) {
        ActivityCompat.requestPermissions(
                themOTO.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }
    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView hanh = (ImageView) this.findViewById(R.id.imganhxethem);
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                hanh.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void themoto (View view)
    {
        if(bienso.getText().length()==0||giaxe.getText().length()==0||tinhtrang.getText().length()==0||kiemdinh.getText().length()==0||thaynhot.getText().length()==0)
        {
            Toast.makeText(getApplicationContext(),"Anh bạn à hãy nhập đầy đủ!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
            Methods methods = getRetrofit().create(Methods.class);
            XETHUEInsertUpdateDeleteModel tknew = new XETHUEInsertUpdateDeleteModel();
            tknew.setTenxe(tenxe.getText().toString());
            String lx= "Xe số tự động";
            if(!rtudong.isChecked())
                lx="Xe số sàn";
            tknew.setLoaixe(lx);
            tknew.setBiensoxe(bienso.getText().toString());
            tknew.setGiaxe(giaxe.getText().toString());
            BitmapDrawable bitmapDrawable = (BitmapDrawable) hanh.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String hinhxe = Base64.encodeToString(bytes, Base64.DEFAULT);
            tknew.setHinhanh(hinhxe);
            tknew.setTinhtrang(tinhtrang.getText().toString());
            tknew.setXe("2");
            tknew.setKiemdinh(kiemdinh.getText().toString());
            tknew.setHanthaynhot(thaynhot.getText().toString());
            Call<XETHUECallBackModel> call = methods.insertXETHUE(tknew);
            call.enqueue(new Callback<XETHUECallBackModel>() {
                @Override
                public void onResponse(Call<XETHUECallBackModel> call, Response<XETHUECallBackModel> response) {
                    Toast.makeText(getApplicationContext(),"Thêm thành công.",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(themOTO.this, chinhsuaOTO.class);
                    i.putExtra("Key_1", value1);
                    startActivity(i);
                }
                @Override
                public void onFailure(Call<XETHUECallBackModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    public void suaoto (View view)
    {
        if(bienso.getText().length()==0||giaxe.getText().length()==0||tinhtrang.getText().length()==0||kiemdinh.getText().length()==0||thaynhot.getText().length()==0)
        {
            Toast.makeText(getApplicationContext(),"Anh bạn à hãy nhập đầy đủ!",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
            Methods methods = getRetrofit().create(Methods.class);
            XETHUEInsertUpdateDeleteModel tknew = new XETHUEInsertUpdateDeleteModel();
            tknew.setMaxe(mx);
            tknew.setTenxe(tenxe.getText().toString());
            String lx= "Xe số tự động";
            if(!rtudong.isChecked())
                lx="Xe số sàn";
            tknew.setLoaixe(lx);
            tknew.setBiensoxe(bienso.getText().toString());
            tknew.setGiaxe(giaxe.getText().toString());
            BitmapDrawable bitmapDrawable = (BitmapDrawable) hanh.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            String hinhxe = Base64.encodeToString(bytes, Base64.DEFAULT);
            tknew.setHinhanh(hinhxe);
            tknew.setTinhtrang(tinhtrang.getText().toString());
            tknew.setXe("2");
            tknew.setKiemdinh(kiemdinh.getText().toString());
            tknew.setHanthaynhot(thaynhot.getText().toString());
            Call<XETHUECallBackModel> call = methods.updateXETHUE(tknew);
            call.enqueue(new Callback<XETHUECallBackModel>() {
                @Override
                public void onResponse(Call<XETHUECallBackModel> call, Response<XETHUECallBackModel> response) {
                    Toast.makeText(getApplicationContext(),"Sửa thành công.",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(themOTO.this, chinhsuaOTO.class);
                    i.putExtra("Key_1", value1);
                    startActivity(i);
                }
                @Override
                public void onFailure(Call<XETHUECallBackModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Sửa thất bại!",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void laymaxe()
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        Call<XETHUEModel> call = methods.GetXETHUE();
        call.enqueue(new Callback<XETHUEModel>() {
            @Override
            public void onResponse(Call<XETHUEModel> call, Response<XETHUEModel> response) {
                List<XETHUEModel.Data> data = response.body().getData();
                int dung=1;
                for(XETHUEModel.Data dt : data){
                    String bssql =dt.getBiensoxe();
                    if(bs.equals(bssql))
                        mx = dt.getMaxe().toString();
                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {
            }
        });

    }
    public void xoaxeoto (View view)
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        XETHUEInsertUpdateDeleteModel tknew = new XETHUEInsertUpdateDeleteModel();
        tknew.setMaxe(mx);
        tknew.setTenxe(tenxe.getText().toString());
        String lx= "Xe số tự động";
        if(!rtudong.isChecked())
            lx="Xe số sàn";
        tknew.setLoaixe(lx);
        tknew.setBiensoxe(bienso.getText().toString());
        tknew.setGiaxe(giaxe.getText().toString());
        BitmapDrawable bitmapDrawable = (BitmapDrawable) hanh.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String hinhxe = Base64.encodeToString(bytes, Base64.DEFAULT);
        tknew.setHinhanh(hinhxe);
        tknew.setTinhtrang(tinhtrang.getText().toString());
        tknew.setXe("Vô hiệu hóa");
        tknew.setKiemdinh(kiemdinh.getText().toString());
        tknew.setHanthaynhot(thaynhot.getText().toString());
        Call<XETHUECallBackModel> call = methods.updateXETHUE(tknew);
        call.enqueue(new Callback<XETHUECallBackModel>() {
            @Override
            public void onResponse(Call<XETHUECallBackModel> call, Response<XETHUECallBackModel> response) {
                Toast.makeText(getApplicationContext(),"Vô hiệu hóa xe thành công.",Toast.LENGTH_SHORT).show();
                Intent i=new Intent(themOTO.this, chinhsuaOTO.class);
                i.putExtra("Key_1", value1);
                startActivity(i);
            }
            @Override
            public void onFailure(Call<XETHUECallBackModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Đang gặp lỗi xin thử lại sau!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}