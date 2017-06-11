package org.sangguo.draggertest.ui.dialog;

import android.app.Dialog;
import android.content.Context;
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

  public void setControl(DialogControl control) {
    this.control = control;
  }

  public BaseDialog(@NonNull Context context) {
    super(context, R.style.SimpleDialog);

    if (context instanceof DialogControl) {
      this.control = (DialogControl) context;
    }
  }

  @Override
  public void show() {
    super.show();
    if (control != null) {
      control.catchDialog(this);
    }
  }

  @Override
  public void dismiss() {
    if (control != null) {
      control.removeDialog(this);
    }
    super.dismiss();
  }
}

