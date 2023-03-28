package com.example.appthuexe.DangKyOTP;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.MainActivity;
import com.example.appthuexe.R;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.TK.TKCallBackModel;
import com.example.lib.Model.TK.TKInsertUpdateDeleteModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {
    EditText tk,mk,ten,sdt;
    TextView tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
         tk = (EditText)this.findViewById(R.id.txtTKdk) ;
         tb = this.findViewById(R.id.lbtbkhieunai) ;
         mk = (EditText)this.findViewById(R.id.txtMKdk) ;
         ten = (EditText)this.findViewById(R.id.txtTen) ;
         sdt = (EditText)this.findViewById(R.id.txtSDT) ;
        String s=getIntent().getStringExtra("sodt").toString();
        sdt.setText(s);
    }
    public void dangkytk(View view)
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        TKInsertUpdateDeleteModel tknew = new TKInsertUpdateDeleteModel();
        tknew.setTaikhoan(tk.getText().toString());
        tknew.setMatkhau(mk.getText().toString());
        tknew.setTen(ten.getText().toString());
        tknew.setSdt(sdt.getText().toString());
        tknew.setQuyen("khach");
        tknew.setTien("0");
        Call<TKCallBackModel> call = methods.insertTK(tknew);
        call.enqueue(new Callback<TKCallBackModel>() {
            @Override
            public void onResponse(Call<TKCallBackModel> call, Response<TKCallBackModel> response) {
                Toast.makeText(getApplicationContext(),"Đăng ký thành công.",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(DangKy.this, MainActivity.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<TKCallBackModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập đã bị trùng!",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void quaylai(View view)
    {
        Intent intent=new Intent(DangKy.this, MainActivity.class);
        startActivity(intent);
    }
}