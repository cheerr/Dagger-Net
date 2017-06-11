package org.sangguo.draggertest.module.modules.home.presenter;

import android.util.Log;
import javax.inject.Inject;
import org.sangguo.draggertest.dagger.scope.PerActivity;
import org.sangguo.draggertest.module.abs.activity.BaseActivity;
import org.sangguo.draggertest.module.abs.presenter.BasePresenter;
import org.sangguo.draggertest.module.common.toast.ToastType;
import org.sangguo.draggertest.module.modules.home.control.PageControl;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * MainActivity的Presenter
 *
 * 注解变量放在成员变量里生成，个人感觉比构造函数生成可读性要强
 */
@PerActivity
public class MainPresenter extends BasePresenter {

  @Inject PageControl pageControl;
  @Inject ToastType styleToast;

  @Inject
  public MainPresenter(BaseActivity baseActivity) {
    Log.i("TAG", "MainPresenter_CREATE" + hashCode());
  }

  @Override public void createdView() {
    Log.i("Presenter", "createdView()");
    pageControl.init();

    if (styleToast != null) {
      styleToast.showToast();
    }
  }

  @Override public void resume() {
    Log.i("Presenter", "resume()");
  }

  @Override public void pause() {
    Log.i("Presenter", "pause()");
  }

  /**
   * 记得手动销毁@Inject的变量
   */
  @Override public void destroy() {
    Log.i("Presenter", "destroy()");
    pageControl = null;
    styleToast = null;
  }
}
