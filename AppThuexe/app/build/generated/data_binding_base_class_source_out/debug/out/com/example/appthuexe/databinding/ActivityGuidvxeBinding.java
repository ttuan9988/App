// Generated by view binder compiler. Do not edit!
package com.example.appthuexe.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.appthuexe.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityGuidvxeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button button;

  @NonNull
  public final ListView listdsbat;

  @NonNull
  public final TextView txtguivt;

  private ActivityGuidvxeBinding(@NonNull ConstraintLayout rootView, @NonNull Button button,
      @NonNull ListView listdsbat, @NonNull TextView txtguivt) {
    this.rootView = rootView;
    this.button = button;
    this.listdsbat = listdsbat;
    this.txtguivt = txtguivt;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityGuidvxeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityGuidvxeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_guidvxe, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityGuidvxeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.button;
      Button button = ViewBindings.findChildViewById(rootView, id);
      if (button == null) {
        break missingId;
      }

      id = R.id.listdsbat;
      ListView listdsbat = ViewBindings.findChildViewById(rootView, id);
      if (listdsbat == null) {
        break missingId;
      }

      id = R.id.txtguivt;
      TextView txtguivt = ViewBindings.findChildViewById(rootView, id);
      if (txtguivt == null) {
        break missingId;
      }

      return new ActivityGuidvxeBinding((ConstraintLayout) rootView, button, listdsbat, txtguivt);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
