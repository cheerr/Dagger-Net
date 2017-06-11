package org.sangguo.draggertest.module.modules.home.activity;

import android.os.Bundle;
import javax.inject.Inject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.module.abs.presenter.PresenterLifeCycle;
import org.sangguo.draggertest.module.modules.home.di.MainActivityComponent;
import org.sangguo.draggertest.module.modules.home.inject.InjectMainActivity;
import org.sangguo.draggertest.module.modules.home.presenter.MainPresenter;

public class MainActivity extends InjectMainActivity {

  @Inject @PresenterLifeCycle MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public void inject(MainActivityComponent component) {
    component.inject(this);
  }
}
