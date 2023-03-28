// Generated code from Butter Knife. Do not modify!
package com.example.appthuexe.ThongTinTK.ThanhToanMOMO;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.appthuexe.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class thanhtoan_ViewBinding implements Unbinder {
  private thanhtoan target;

  private View view7f0800ac;

  @UiThread
  public thanhtoan_ViewBinding(thanhtoan target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public thanhtoan_ViewBinding(final thanhtoan target, View source) {
    this.target = target;

    View view;
    target.edAmount = Utils.findRequiredViewAsType(source, R.id.edAmount, "field 'edAmount'", EditText.class);
    target.tvMessage = Utils.findRequiredViewAsType(source, R.id.tvMessage, "field 'tvMessage'", TextView.class);
    view = Utils.findRequiredView(source, R.id.btnPayMoMo, "field 'btnPayMoMo' and method 'onViewClicked'");
    target.btnPayMoMo = Utils.castView(view, R.id.btnPayMoMo, "field 'btnPayMoMo'", Button.class);
    view7f0800ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    thanhtoan target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.edAmount = null;
    target.tvMessage = null;
    target.btnPayMoMo = null;

    view7f0800ac.setOnClickListener(null);
    view7f0800ac = null;
  }
}
