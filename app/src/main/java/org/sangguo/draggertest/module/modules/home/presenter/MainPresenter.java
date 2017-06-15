package org.sangguo.draggertest.module.modules.home.presenter;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import javax.inject.Inject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.dagger.scope.PerActivity;
import org.sangguo.draggertest.module.abs.activity.BaseActivity;
import org.sangguo.draggertest.module.abs.presenter.BaseViewPresenter;
import org.sangguo.draggertest.module.common.toast.ToastType;
import org.sangguo.draggertest.module.modules.home.control.PageControl;
import org.sangguo.draggertest.module.modules.home.viewer.MainViewer;
import org.sangguo.draggertest.observer.ObsData;
import org.sangguo.draggertest.observer.example.UserUpdateObserver;

/**
 * Created by chenwei on 2017/6/7.
 */

/**
 * MainActivity的Presenter
 *
 * 注解变量放在成员变量里生成，个人感觉比构造函数生成可读性要强
 */
@PerActivity
public class MainPresenter extends BaseViewPresenter {

  @Inject PageControl pageControl;
  @Inject ToastType styleToast;

  private BaseActivity activity;

  private MainViewer viewer;

  @BindView(R.id.pageText) TextView pageTextView;

  @Inject MainPresenter(BaseActivity activity) {
    this.activity = activity;
    Log.i("TAG", "MainPresenter_CREATE" + hashCode());

    UserUpdateObserver.newInstance().post(new ObsData().put("avatar", "http://121.32.32.44"));
  }

  /**
   * 设置操作视图
   */
  public void setViewer(MainViewer viewer) {
    this.viewer = viewer;
  }

  //@Override protected View findViewById(@IdRes int id) {
  //  return activity.findViewById(id);
  //}

  @Override public void createdView(View view) {
    super.createdView(view);
    Log.i("Presenter", "createdView()");
    pageControl.init();

    if (styleToast != null) {
      styleToast.showToast();
    }
    // pageText.setText("ahhahaha");
  }

  @OnClick(R.id.pageText)
  public void onPageTextClick(View view) {
    viewer.showToast("onPageTextClick");
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
    viewer = null;
    pageControl = null;
    styleToast = null;
  }
}
