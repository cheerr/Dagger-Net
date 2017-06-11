package org.sangguo.draggertest.module.modules.home.activityfragment;

import android.os.Bundle;
import javax.inject.Inject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.module.abs.presenter.PresenterLifeCycle;
import org.sangguo.draggertest.module.modules.home.di.HomeMainComponent;
import org.sangguo.draggertest.module.modules.home.inject.InjectHomeMainBarActivity;
import org.sangguo.draggertest.module.modules.home.presenter.MainPresenter;

public class MainActivity extends InjectHomeMainBarActivity {

  @Inject @PresenterLifeCycle MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @Override
  public void inject(HomeMainComponent component) {
    component.inject(this);
  }
}