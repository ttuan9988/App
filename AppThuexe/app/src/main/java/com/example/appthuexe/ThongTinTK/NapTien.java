package com.example.appthuexe.ThongTinTK;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.appthuexe.R;
import com.example.appthuexe.ThongTinTK.ThanhToanMOMO.MoMoConstants;
import com.example.appthuexe.ThongTinTK.ThanhToanMOMO.thanhtoan;
import com.example.appthuexe.TrangChu;

public class NapTien extends AppCompatActivity {
    int environment = 1;//developer default - Production environment = 2
    String value1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nap_tien);
        Intent intent = getIntent();
        value1 = intent.getStringExtra("Key_1");
        TextView ten = this.findViewById(R.id.lbtenhd) ;
        ten.setText(value1);
    }
    public void chonnapmomo(View view)
    {
        Bundle data = new Bundle();
        Intent intent;
        intent = new Intent(NapTien.this, thanhtoan.class);
        String temp = String.valueOf(value1);
        intent.putExtra("Key_1", temp);
        data.putInt(MoMoConstants.KEY_ENVIRONMENT, environment);
        intent.putExtras(data);
        startActivity(intent);
    }

    public void homent(View view) {
        Intent intent;
        intent = new Intent(NapTien.this, TrangChu.class);
        String temp = String.valueOf(value1);
        intent.putExtra("Key_1", temp);
        startActivity(intent);
    }
}