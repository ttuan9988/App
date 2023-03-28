package com.example.appthuexe.ThongTinTK.ThanhToanMOMO;

import static com.example.lib.RetrofitClient.getRetrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appthuexe.DangKyOTP.DangKy;
import com.example.appthuexe.Datxe.danhsachOTO;
import com.example.appthuexe.MainActivity;
import com.example.appthuexe.R;
import com.example.appthuexe.TrangChu;
import com.example.lib.InterfaceRepository.Methods;
import com.example.lib.Model.TK.TKCallBackModel;
import com.example.lib.Model.TK.TKInsertUpdateDeleteModel;
import com.example.lib.Model.TK.TKModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.momo.momo_partner.AppMoMoLib;
import vn.momo.momo_partner.MoMoParameterNamePayment;


public class thanhtoan extends AppCompatActivity {
    String value1;
    @BindView(R.id.edAmount)
    EditText edAmount;
    @BindView(R.id.tvMessage)
    TextView tvMessage;
    @BindView(R.id.btnPayMoMo)
    Button btnPayMoMo;
    int tienco=-1;
    String mk,quyen,ten,sdt,vitri;
    private String amount ="";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "Thuê xe XLC";
    private String merchantCode = "MOMOJ6SF20211123";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanhtoan);
        ButterKnife.bind(this);
        Bundle data = getIntent().getExtras();
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        loadtk();
        description=  "Nạp tiền vào tài khoản "+value1;
        if(data != null){
            environment = data.getInt(MoMoConstants.KEY_ENVIRONMENT);
        }
        if(environment == 0){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEBUG);
        }else if(environment == 1){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        }else if(environment == 2){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION);
        }
    }
    //example payment
    private void requestPayment() {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);
        if (edAmount.getText().toString() != null && edAmount.getText().toString().trim().length() != 0)
            amount = edAmount.getText().toString().trim();
        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put(MoMoParameterNamePayment.MERCHANT_NAME, merchantName);
        eventValue.put(MoMoParameterNamePayment.MERCHANT_CODE, merchantCode);
        eventValue.put(MoMoParameterNamePayment.AMOUNT, amount);
        eventValue.put(MoMoParameterNamePayment.DESCRIPTION, description);
        //client Optional
        eventValue.put(MoMoParameterNamePayment.FEE, fee);
        eventValue.put(MoMoParameterNamePayment.MERCHANT_NAME_LABEL, merchantNameLabel);
        eventValue.put(MoMoParameterNamePayment.REQUEST_ID,  merchantCode+"-"+ UUID.randomUUID().toString());
        eventValue.put(MoMoParameterNamePayment.PARTNER_CODE, "MOMOJ6SF20211123");
        eventValue.put(MoMoParameterNamePayment.REQUEST_TYPE, "payment");
        eventValue.put(MoMoParameterNamePayment.LANGUAGE, "vi");
        eventValue.put(MoMoParameterNamePayment.EXTRA, "");
        //Request momo app
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if(data != null) {
                if(data.getIntExtra("status", -1) == 0) {
                    ////////
                    nap();
                    if(data.getStringExtra("data") != null && !data.getStringExtra("data").equals("")) {
                        // TODO:
                    } else {
                        tvMessage.setText("Thông báo: mes7");
                    }
                } else if(data.getIntExtra("status", -1) == 1) {
                    String message = data.getStringExtra("message") != null?data.getStringExtra("message"):"Thất bại";
                    tvMessage.setText("message: " + message);
                } else if(data.getIntExtra("status", -1) == 2) {
                    tvMessage.setText("message: mes3");
                } else {
                    tvMessage.setText("Thông báo: Bạn đã hủy giao dịch. " );
                    Toast.makeText(thanhtoan.this, "Bạn đã hủy giao dịch.", Toast.LENGTH_SHORT).show();

                }
            } else {
                tvMessage.setText("Thông báo: mes5");
            }
        } else {
            tvMessage.setText("Thông báo: Nạp tiền thất bại!");
            Toast.makeText(thanhtoan.this, "Nạp tiền thất bại!", Toast.LENGTH_SHORT).show();

        }
    }

    @OnClick(R.id.btnPayMoMo)
    public void onViewClicked() {
        if(edAmount.getText().length()==0)
        {
            tvMessage.setText("Hãy điền số tiền cần nạp!");
        }
        else if(tienco==-1)
        {
            tvMessage.setText("Chức năng nạp tiền đang bị lỗi!");
        }
        else
        {
            requestPayment();
        }

    }

    public void nap()
    {
        Toast.makeText(getApplicationContext(),"Loading...",Toast.LENGTH_SHORT).show();
        Methods methods = getRetrofit().create(Methods.class);
        TKInsertUpdateDeleteModel tknew = new TKInsertUpdateDeleteModel();
        tknew.setTaikhoan(value1);
        tienco= tienco+ Integer.parseInt(amount);
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
                edAmount.setText("");
                Toast.makeText(thanhtoan.this, "Nạp thành công số dư là "+tienco, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<TKCallBackModel> call, Throwable t) {
                edAmount.setText("");
                Toast.makeText(getApplicationContext(),"Nạp thất bại!",Toast.LENGTH_SHORT).show();
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
                    if(value1.equals(tksql)) {
                        tienco =tiensql;
                        mk= dt.getMatkhau();
                        quyen=dt.getQuyen();
                        ten = dt.getTen();
                        sdt = dt.getSdt();
                        vitri = dt.getVitri();
                        Toast.makeText(thanhtoan.this, "Số dư hiện tại là: " +tienco, Toast.LENGTH_SHORT).show();
                    }
                }
                if(tienco==-1)
                {
                    Toast.makeText(thanhtoan.this, "Nạp tiền đang bị lỗi!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<TKModel> call, Throwable t) {

            }
        });
    }

    public void homena(View view) {
        Intent i=new Intent(thanhtoan.this, TrangChu.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
}