package org.sangguo.draggertest.modules.abs;

/**
 * Created by chenwei on 2017/6/7.
 */

public class BasePresenterWrapperActivity extends BaseBarActivity {

  private BasePresenter presenter;

  public void setPresenter(BasePresenter presenter) {
    this.presenter = presenter;
  }

  public void onViewCreated() {
    super.onViewCreated();
    if (presenter != null) {
      presenter.createdView();
    }
  }

  public void onResume() {
    super.onResume();
    if (presenter != null) {
      presenter.resume();
    }
  }

  public void onPause() {
    super.onPause();
    if (presenter != null) {
      presenter.pause();
    }
  }

  public void onDestroy() {
    super.onDestroy();
    if (presenter != null) {
      presenter.destroy();
    }
    presenter = null;
  }
}
