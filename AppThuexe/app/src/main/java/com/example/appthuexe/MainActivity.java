package com.example.appthuexe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import  static com.example.lib.RetrofitClient.getRetrofit;
import com.example.appthuexe.DangKyOTP.ChonSDT;
import com.example.appthuexe.TrangAdmin.Dinhvi.guidvxe;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.TK.TKModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText mk,tk;
    Button btndangnhap,btndk;
    ArrayAdapter<String> stringArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btndangnhap =(Button)this.findViewById(R.id.btnDN);
        btndk =(Button)this.findViewById(R.id.btnfrmdk);
        tk = (EditText)this.findViewById(R.id.txtTK) ;
        mk = (EditText)this.findViewById(R.id.txtMK) ;
    }


    public void dangky(View view)
    {
        Intent intent=new Intent(MainActivity.this, ChonSDT.class);
        startActivity(intent);
    }

    public void dangnhap(View view)
    {
        String taik = String.valueOf(tk.getText());
        String matk = String.valueOf(mk.getText());

        if (taik.length()==0||matk.length()==0)
        {
            Toast.makeText(getApplicationContext(),"Hãy điền đầy đủ.",Toast.LENGTH_SHORT).show();
        }
        else if(taik.equals("batdinhvi")&&matk.equals("batdinhvi"))
        {
            Intent i = new Intent(MainActivity.this, guidvxe.class);
            startActivity(i);
        }
        else {
            Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
            Methods methods = getRetrofit().create(Methods.class);
            Call<TKModel> call = methods.GetTK();
            call.enqueue(new Callback<TKModel>() {
                @Override
                public void onResponse(Call<TKModel> call, Response<TKModel> response) {
                    List<TKModel.Data> data = response.body().getData();
                    int dung=1;
                    for(TKModel.Data dt : data){
                        String tksql =dt.getTaikhoan();
                        String mksql =dt.getMatkhau();
                        String quyen =dt.getQuyen();
                        if(taik.equals(tksql)&&matk.equals(mksql)&&quyen.equals("khach"))
                            dung=2;
                        if (taik.equals(tksql)&&matk.equals(mksql)&&quyen.equals("admin"))
                            dung=3;
                        if (taik.equals(tksql)&&matk.equals(mksql)&&quyen.equals("khoa"))
                            dung=4;
                    }
                    if(dung==2)
                    {
                        Toast.makeText(getApplicationContext(),"Đăng nhập thành công.",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this,TrangChu.class);
                        String temp = String.valueOf(tk.getText());
                        i.putExtra("Key_1", temp);

                        startActivity(i);
                    }else
                    if (dung==3)
                    {
                        Toast.makeText(getApplicationContext(),"Xin chào ADMIN.",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MainActivity.this,TrangChu.class);
                        String temp = String.valueOf(tk.getText());
                        i.putExtra("Key_1", temp);

                        startActivity(i);
                    }
                    else
                    if(dung==4)
                    {
                        Toast.makeText(getApplicationContext(),"Tài khoản của bạn đã bị khóa!",Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Đăng nhập thất bại.",Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<TKModel> call, Throwable t) {

                }
            });

        }


    }

}