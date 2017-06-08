package org.sangguo.draggertest.modules.home.presenter;

import android.util.Log;
import javax.inject.Inject;
import org.sangguo.draggertest.internal.scope.PerActivity;
import org.sangguo.draggertest.modules.abs.BaseActivity;
import org.sangguo.draggertest.modules.abs.BasePresenter;
import org.sangguo.draggertest.modules.abs.BasePresenterWrapperActivity;
import org.sangguo.draggertest.modules.common.toast.ToastType;
import org.sangguo.draggertest.modules.home.control.PageControl;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * MainActivity的Presenter
 *
 * 注解变量放在成员变量里生成，个人感觉比构造函数生成可读性要强
 */
@PerActivity
public class MainPresenter implements BasePresenter {

  @Inject PageControl pageControl;
  @Inject ToastType styleToast;

  @Inject
  public MainPresenter(BaseActivity baseActivity) {
    Log.i("TAG", "MainPresenter_CREATE" + hashCode());

    if (baseActivity instanceof BasePresenterWrapperActivity) {
      ((BasePresenterWrapperActivity) baseActivity).setPresenter(this);
    }
  }

  @Override public void createdView() {
    pageControl.init();

    if (styleToast != null) {
      styleToast.showToast();
    }
  }

  @Override public void resume() {

  }

  @Override public void pause() {

  }

  /**
   * 记得手动销毁@Inject的变量
   */
  @Override public void destroy() {
    pageControl = null;
    styleToast = null;
  }
}
