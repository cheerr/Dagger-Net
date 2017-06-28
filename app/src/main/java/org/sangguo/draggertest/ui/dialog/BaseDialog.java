package org.sangguo.draggertest.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import org.sangguo.draggertest.R;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * 所在Activity实现DialogControl 方法，控制Dialog生命周期
 */
public class BaseDialog extends Dialog {

  private DialogControl control;
  private Activity activity;

  public void setControl(DialogControl control) {
    this.control = control;
  }

  public BaseDialog(@NonNull Activity activity) {
    super(activity, R.style.SimpleDialog);

    this.activity = activity;
    if (activity instanceof DialogControl) {
      this.control = (DialogControl) activity;
    }
  }

  @Override
  public void show() {
    super.show();
    if (control != null) {
      control.catchDialog(this);
    }
  }

  private void superDismiss() {
    if (control != null) {
      control.removeDialog(this);
    }
    super.dismiss();
  }

  @Override
  public void dismiss() {
    if (activity != null && !activity.isFinishing()) {

    } else {
      superDismiss();
    }
  }
}

