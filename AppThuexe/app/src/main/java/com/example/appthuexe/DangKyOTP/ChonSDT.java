package com.example.appthuexe.DangKyOTP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appthuexe.R;
import com.hbb20.CountryCodePicker;

public class ChonSDT extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_sdt);
        EditText t1=(EditText)findViewById(R.id.txtsdtotp);
        CountryCodePicker ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(t1);
        Button btn1=(Button)findViewById(R.id.btnnhanma);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChonSDT.this,NhapMaOTP.class);
                intent.putExtra("sodt",ccp.getFullNumberWithPlus().replace(" ",""));
                startActivity(intent);
            }
        });
    }
}