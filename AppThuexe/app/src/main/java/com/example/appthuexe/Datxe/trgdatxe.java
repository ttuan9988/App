package com.example.appthuexe.Datxe;

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
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.DangKyOTP.DangKy;
import com.example.appthuexe.MainActivity;
import com.example.appthuexe.R;
import com.example.appthuexe.ThongTinTK.ThanhToanMOMO.thanhtoan;
import com.example.appthuexe.TrangAdmin.trgAdmin;
import com.example.appthuexe.TrangChu;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXECallBackModel;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEInsertUpdateDeleteModel;
import com.example.lib.Model.PHIEUTHUEXE.PHIEUTHUEXEModel;
import com.example.lib.Model.TK.TKCallBackModel;
import com.example.lib.Model.TK.TKInsertUpdateDeleteModel;
import com.example.lib.Model.TK.TKModel;
import com.example.lib.Model.XETHUE.XETHUEModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sun.bob.mcalendarview.MCalendarView;
import sun.bob.mcalendarview.MarkStyle;
import sun.bob.mcalendarview.listeners.OnDateClickListener;
import sun.bob.mcalendarview.vo.DateData;
import sun.bob.mcalendarview.vo.MarkedDates;

public class trgdatxe extends AppCompatActivity {
    final int REQUEST_CODE_GALLERY = 999;
    MCalendarView calendarView;
    DateData datecu;
    String bienso,value1;
    String ndchon,ntchon;
    TextView tenxe,lbngay,sodu,lbtb,biensoxe,ndlich,tiencoc,dongia,tienxe,ngaydat,ngaytra;
    ImageView hanh;
    EditText songay;
    Button btnchonanh,btnngaydat,btnokdx,btndat;
    ConstraintLayout lay;
    String mk,quyen,ten,sdt,vitri;
    int tienco=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trgdatxe);
         hanh = (ImageView) this.findViewById(R.id.imgbldx);
         tenxe = (TextView) findViewById(R.id.txttenxedx);
         sodu = (TextView) findViewById(R.id.txtsodudx);
         lbngay = (TextView) findViewById(R.id.lbngay);
         lbtb = (TextView) findViewById(R.id.lbtbdx);
         biensoxe = (TextView) findViewById(R.id.txtbiensodx);
         ndlich = (TextView) findViewById(R.id.txtndl);
         songay = (EditText) findViewById(R.id.txtsongay);
         tiencoc = (TextView) findViewById(R.id.txttiencocdx);
         dongia = (TextView) findViewById(R.id.txtdongia);
         tienxe = (TextView) findViewById(R.id.txttienxedx);
         ngaydat = (TextView) findViewById(R.id.txtngaydatdx);
         lay = (ConstraintLayout) findViewById(R.id.laydatx);
         lay.setVisibility(View.GONE);
         ngaytra = (TextView) findViewById(R.id.txtngaytradx);
         btnchonanh = (Button) findViewById(R.id.btnanhbldx);
         btnngaydat = (Button) findViewById(R.id.btnngaydatdx);
         btnokdx = (Button) findViewById(R.id.btnokdx);
         btndat = (Button) findViewById(R.id.btndatdx);
        calendarView = (MCalendarView) findViewById(R.id.lichdat);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        bienso = intent.getStringExtra("bs");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datenow = df.format(Calendar.getInstance().getTime());
        loadxe();
        loadtien();
        calendarView.setOnDateClickListener(new OnDateClickListener() {
            @Override
            public void onDateClick(View view, DateData date) {
                Calendar a = Calendar.getInstance();
                Date dn = Date.valueOf(datenow);
                a.setTime(dn);
                String ntss = date.getYear()+"-"+date.getMonth()+"-"+date.getDay();
                Calendar b = Calendar.getInstance();
                Date date2s = Date.valueOf(ntss);
                b.setTime(date2s);
                long thx = (b.getTime().getTime() - a.getTime().getTime()) / (24 * 3600 * 1000);
                MarkedDates markedDates = calendarView.getMarkedDates();
                if(markedDates.check(date)==null&&thx>0)
                {
                    if(datecu==null)
                    {
                        calendarView.setMarkedStyle(MarkStyle.BACKGROUND, Color.BLUE).markDate(date.getYear(),  date.getMonth(), date.getDay());
                        datecu=date;
                        ndlich.setText("Ngày thuê đã chọn là: "+date.getDay()+"/"+date.getMonth()+"/"+date.getYear());
                        ndchon=date.getDay()+"/"+date.getMonth()+"/"+date.getYear();
                    }
                    else
                    {
                        calendarView.unMarkDate(datecu);
                        calendarView.setMarkedStyle(MarkStyle.BACKGROUND,Color.BLUE).markDate(date.getYear(),  date.getMonth(), date.getDay());
                        datecu=date;
                        ndlich.setText("Ngày thuê đã chọn là: "+date.getDay()+"/"+date.getMonth()+"/"+date.getYear());
                        ndchon=date.getDay()+"/"+date.getMonth()+"/"+date.getYear();
                    }
                }

            }

        });
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
                Toast.makeText(getApplicationContext(), "Ứng dụng chưa có quyền truy cập vào tệp!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ImageView hanh = (ImageView) this.findViewById(R.id.imgbldx);
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
    private void layngay()
    {
        Methods methods = getRetrofit().create(Methods.class);
        Call<PHIEUTHUEXEModel> call = methods.GetPHIEUTHUEXE();
        call.enqueue(new Callback<PHIEUTHUEXEModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXEModel> call, Response<PHIEUTHUEXEModel> response) {
                List<PHIEUTHUEXEModel.Data> data = response.body().getData();
                int dung=1;
                for(PHIEUTHUEXEModel.Data dt : data){
                    if(bienso.equals(dt.getBiensoxe().toString()))
                    {
                        String nds= dt.getNgaythue().toString();
                        Scanner sc = new Scanner(nds);
                        sc.useDelimiter("/");
                        String d = sc.next();
                        String m = sc.next();
                        String y = sc.next();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String ndss = y+"-"+m+"-"+d;
                        Calendar a = Calendar.getInstance();
                        Date date1s = Date.valueOf(ndss);
                        a.setTime(date1s);

                        String nts= dt.getNgaytra().toString();
                        Scanner sc2 = new Scanner(nts);
                        sc2.useDelimiter("/");
                        String d1 = sc2.next();
                        String m1 = sc2.next();
                        String y1 = sc2.next();
                        String ntss = y1+"-"+m1+"-"+d1;
                        Calendar b = Calendar.getInstance();
                        Date date2s = Date.valueOf(ntss);
                        b.setTime(date2s);

                        String date = df.format(Calendar.getInstance().getTime());
                        Calendar n = Calendar.getInstance();
                        Date date1n = Date.valueOf(date);
                        n.setTime(date1n);
                        long th2 = (b.getTime().getTime() - n.getTime().getTime()) / (24 * 3600 * 1000);
                        if(th2>0)
                        {
                            long th1 = (b.getTime().getTime() - a.getTime().getTime()) / (24 * 3600 * 1000);
                            for (int p=0;p<=th1;p++)
                            {
                                String ngay = df.format(a.getTime());
                                Scanner scx = new Scanner(ngay);
                                scx.useDelimiter("-");
                                int q = Integer.parseInt(scx.next());
                                int w = Integer.parseInt(scx.next());
                                int e = Integer.parseInt(scx.next());
                                calendarView.setMarkedStyle(MarkStyle.BACKGROUND,Color.RED).markDate(q,w,e);
                                a.add(Calendar.DATE, 1);
                            }
                        }
                    }

                }
            }
            @Override
            public void onFailure(Call<PHIEUTHUEXEModel> call, Throwable t) {

            }
        });



    }
    private void clearngay()
    {

            MarkedDates markedDates = calendarView.getMarkedDates();
            ArrayList markData = markedDates.getAll();
            for (int k=0; k<markData.size();k++){
                calendarView.unMarkDate((DateData) markData.get(k));
            }


    }
    private void loadxe()
    {
        biensoxe.setText(bienso);
        Methods methods = getRetrofit().create(Methods.class);
        Call<XETHUEModel> call = methods.GetXETHUE();
        call.enqueue(new Callback<XETHUEModel>() {
            @Override
            public void onResponse(Call<XETHUEModel> call, Response<XETHUEModel> response) {
                List<XETHUEModel.Data> data = response.body().getData();
                int dung=1;
                for(XETHUEModel.Data dt : data){
                    if(bienso.equals(dt.getBiensoxe().toString()))
                    {
                        dongia.setText(dt.getGiaxe().toString());
                        tenxe.setText(dt.getTenxe().toString());
                        tiencoc.setText(dt.getGiaxe().toString());
                    }

                }
            }
            @Override
            public void onFailure(Call<XETHUEModel> call, Throwable t) {

            }
        });
    }
    public void chonngay(View view)
    {
        clearngay();
        layngay();
        btnchonanh.setVisibility(View.GONE);
        btnngaydat.setVisibility(View.GONE);
        btndat.setVisibility(View.GONE);
        lay.setVisibility(View.VISIBLE);
    }
    public void chonok(View view)
    {
        String sn = String.valueOf(songay.getText());

        if(ndchon.length()==0||sn.length()==0)
        {
            Toast.makeText(getApplicationContext(),"Hãy chọn ngày đặt và số ngày thuê!",Toast.LENGTH_LONG).show();
        }else {

            int snx = Integer.parseInt(sn);
            Scanner sc = new Scanner(ndchon);
            sc.useDelimiter("/");
            String d = sc.next();
            String m = sc.next();
            String y = sc.next();
            String ndss = y+"-"+m+"-"+d;
            Calendar a = Calendar.getInstance();
            Date date1s = Date.valueOf(ndss);
            a.setTime(date1s);
            if(snx>0)
            {
                a.add(Calendar.DATE, snx);
                DateFormat dd = new SimpleDateFormat("dd/MM/yyyy");
                String nt = dd.format(a.getTime());

                Methods methods = getRetrofit().create(Methods.class);
                Call<PHIEUTHUEXEModel> call = methods.GetPHIEUTHUEXE();
                call.enqueue(new Callback<PHIEUTHUEXEModel>() {
                    @Override
                    public void onResponse(Call<PHIEUTHUEXEModel> call, Response<PHIEUTHUEXEModel> response) {
                        List<PHIEUTHUEXEModel.Data> data = response.body().getData();
                        int k=1;
                        for(PHIEUTHUEXEModel.Data dt : data){
                            if(bienso.equals(dt.getBiensoxe().toString()))
                            {
                                String ntsql= dt.getNgaythue();
                                Scanner scanner = new Scanner(ntsql);
                                scanner.useDelimiter("/");
                                String ds = scanner.next();
                                String ms = scanner.next();
                                String ys = scanner.next();
                                ntsql = ys+"-"+ms+"-"+ds;
                                Calendar c = Calendar.getInstance();
                                Date date3s = Date.valueOf(ntsql);
                                c.setTime(date3s);
                                Calendar ndst = Calendar.getInstance();
                                ndst.setTime(date1s);
                                long th1 = (c.getTime().getTime() - ndst.getTime().getTime()) / (24 * 3600 * 1000);
                                if(th1>0)
                                {
                                    DateFormat ddd = new SimpleDateFormat("yyyy-MM-dd");
                                    String ntt = ddd.format(a.getTime());
                                    Calendar q = Calendar.getInstance();
                                    Date daq = Date.valueOf(ntt);
                                    q.setTime(daq);
                                    long tt = (c.getTime().getTime() - q.getTime().getTime()) / (24 * 3600 * 1000);
                                    if(tt<=0)
                                    {
                                        k=2;break;
                                    }
                                }
                            }

                        }
                        if(k==1)
                        {
                            btnchonanh.setVisibility(View.VISIBLE);
                            btnngaydat.setVisibility(View.VISIBLE);
                            btndat.setVisibility(View.VISIBLE);
                            lay.setVisibility(View.GONE);
                            ngaydat.setText(ndchon);
                            ngaytra.setText(nt);
                            double tien1 = Double.parseDouble((String) tiencoc.getText());
                            double ac = (snx - 1) * tien1;
                            String ax = String.valueOf(ac);
                            tienxe.setText(ax);
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"Số ngày chọn lớn hơn thời gian rảnh của xe!!!",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PHIEUTHUEXEModel> call, Throwable t) {

                    }
                });
            }
        }
    }
    public void chonanhbl(View view)
    {
        ActivityCompat.requestPermissions(
                trgdatxe.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE_GALLERY
        );
    }
    public void backh(View view)
    {
        Intent i=new Intent(trgdatxe.this, TrangChu.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
    public void loadtien()
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        Call<TKModel> call = methods.GetTK();
        call.enqueue(new Callback<TKModel>() {
            @Override
            public void onResponse(Call<TKModel> call, Response<TKModel> response) {
                List<TKModel.Data> data = response.body().getData();
                for(TKModel.Data dt : data){
                    if(value1.equals(dt.getTaikhoan().toString()))
                    {
                        sodu.setText(dt.getTien().toString());
                        int tiensql = Integer.parseInt(dt.getTien());
                        tienco =tiensql;
                        mk= dt.getMatkhau();
                        quyen=dt.getQuyen();
                        ten = dt.getTen();
                        sdt = dt.getSdt();
                        vitri = dt.getVitri();
                    }

                }
                if(tienco==-1)
                {
                    Toast.makeText(trgdatxe.this, "Chức năng đang bị lỗi xin vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TKModel> call, Throwable t) {

            }
        });
    }
    public  void lapphieuthuexe(View view)
    {
        lapphieu();
        tinhtien();
        ngaydat.setText("");
        ngaytra.setText("");
        loadtien();
    }
    public void lapphieu()
    {
        Methods methods = getRetrofit().create(Methods.class);
        PHIEUTHUEXEInsertUpdateDeleteModel tknew = new PHIEUTHUEXEInsertUpdateDeleteModel();
        tknew.setNgaytra(ngaytra.getText().toString());
        tknew.setTienxe(tienxe.getText().toString());
        tknew.setTaikhoan(value1);
        tknew.setBiensoxe(bienso);
        tknew.setTiencoc(tiencoc.getText().toString());
        BitmapDrawable bitmapDrawable = (BitmapDrawable) hanh.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String hinhxe = Base64.encodeToString(bytes, Base64.DEFAULT);
        tknew.setBanglai(hinhxe);
        tknew.setNgaythue(ngaydat.getText().toString());
        tknew.setDuyet("chua");
        DateFormat df = new SimpleDateFormat("HH:mm:ss-dd/MM/yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        tknew.setThoigian(date);
        Call<PHIEUTHUEXECallBackModel> call = methods.insertPHIEUTHUEXE(tknew);
        call.enqueue(new Callback<PHIEUTHUEXECallBackModel>() {
            @Override
            public void onResponse(Call<PHIEUTHUEXECallBackModel> call, Response<PHIEUTHUEXECallBackModel> response) {

            }
            @Override
            public void onFailure(Call<PHIEUTHUEXECallBackModel> call, Throwable t) {
            }
        });
    }

    public void tinhtien()
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        TKInsertUpdateDeleteModel tknew = new TKInsertUpdateDeleteModel();
        tknew.setTaikhoan(value1);
        String tc = tiencoc.getText().toString();
        tienco= tienco- Integer.parseInt(tc);
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
                Toast.makeText(trgdatxe.this, "Đặt xe thành công số dư là "+tienco+ "đồng", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<TKCallBackModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Đặt xe thất bại!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}