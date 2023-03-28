package com.example.appthuexe.DangKyOTP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appthuexe.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class NhapMaOTP extends AppCompatActivity {
    String sdt;
    String otpid;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_ma_otp);

        sdt=getIntent().getStringExtra("sodt").toString();
        EditText t2=(EditText)findViewById(R.id.txtotp);
        Button b2=(Button)findViewById(R.id.btnotp);
        mAuth=FirebaseAuth.getInstance();
        initiateotp();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(t2.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Không được để trống OTP!",Toast.LENGTH_LONG).show();
                else if(t2.getText().toString().length()!=6)
                    Toast.makeText(getApplicationContext(),"OTP không hợp lệ!",Toast.LENGTH_LONG).show();
                else
                {
                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otpid,t2.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });
    }
    private void initiateotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                sdt,60, TimeUnit.SECONDS,this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                {
                    @Override
                    public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken)
                    {
                        otpid=s;
                        Toast.makeText(NhapMaOTP.this, "Mã OTP đang được gửi...", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
                    {
                        // signInWithPhoneAuthCredential(phoneAuthCredential);
                    }
                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                        Log.e("Bug",e.getMessage());

                    }
                });
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Intent i = new Intent(NhapMaOTP.this,DangKy.class);
                            i.putExtra("sodt", sdt);
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(),"Sai mã OTP!",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}