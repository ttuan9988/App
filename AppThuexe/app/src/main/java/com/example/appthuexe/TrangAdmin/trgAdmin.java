package com.example.appthuexe.TrangAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appthuexe.DangKyOTP.DangKy;
import com.example.appthuexe.MainActivity;
import com.example.appthuexe.R;
import com.example.appthuexe.TrangAdmin.Dinhvi.dsxe;
import com.example.appthuexe.TrangChu;

public class trgAdmin extends AppCompatActivity {
    String value1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trg_admin);
        Intent intent = getIntent();
        TextView ten = findViewById(R.id.lbtenadmin);
        value1 = intent.getStringExtra("Key_1");
        ten.setText(value1);

    }
    public void chinhsuaoto(View view)
    {
        Intent i=new Intent(trgAdmin.this, chinhsuaOTO.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
    public void duyetxe(View view)
    {
        Intent i=new Intent(trgAdmin.this, duyetdatxe.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }

    public void vetc(View view) {
        Intent i=new Intent(trgAdmin.this, TrangChu.class);
        i.putExtra("Key_1", value1);
        this.closeOptionsMenu();
        startActivity(i);
    }

    public void xemvtx(View view) {
        Intent i=new Intent(trgAdmin.this, dsxe.class);
        i.putExtra("Key_1", value1);
        startActivity(i);
    }
}