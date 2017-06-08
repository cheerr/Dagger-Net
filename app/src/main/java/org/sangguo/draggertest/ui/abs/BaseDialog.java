package org.sangguo.draggertest.ui.abs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.modules.abs.BaseActivity;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * 简单维护一个BaseDialog，关联到BaseActivity，由Activity生命周期控制Dialog的消失
 */
public class BaseDialog extends Dialog {

  private BaseActivity baseActivity;

  public BaseDialog(@NonNull Context context) {
    super(context, R.style.SimpleDialog);

    if (context instanceof BaseActivity) {
      this.baseActivity = (BaseActivity) context;
    }
  }

  @Override
  public void show() {
    super.show();
    if (baseActivity != null) {
      baseActivity.catchDialog(this);
    }
  }

  @Override
  public void dismiss() {
    if (baseActivity != null) {
      baseActivity.removeDialog(this);
    }
    super.dismiss();
  }
}

