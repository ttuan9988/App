// Generated by view binder compiler. Do not edit!
package com.example.appthuexe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthuexe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNhapMaOtpBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button btnotp;

  @NonNull
  public final EditText txtotp;

  private ActivityNhapMaOtpBinding(@NonNull ConstraintLayout rootView, @NonNull Button btnotp,
      @NonNull EditText txtotp) {
    this.rootView = rootView;
    this.btnotp = btnotp;
    this.txtotp = txtotp;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNhapMaOtpBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNhapMaOtpBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_nhap_ma_otp, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNhapMaOtpBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnotp;
      Button btnotp = ViewBindings.findChildViewById(rootView, id);
      if (btnotp == null) {
        break missingId;
      }

      id = R.id.txtotp;
      EditText txtotp = ViewBindings.findChildViewById(rootView, id);
      if (txtotp == null) {
        break missingId;
      }

      return new ActivityNhapMaOtpBinding((ConstraintLayout) rootView, btnotp, txtotp);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
