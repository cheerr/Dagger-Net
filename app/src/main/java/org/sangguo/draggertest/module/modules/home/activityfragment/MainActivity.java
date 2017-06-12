package org.sangguo.draggertest.module.modules.home.activityfragment;

import android.os.Bundle;
import android.util.Log;
import javax.inject.Inject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.module.abs.presenter.PresenterLifeCycle;
import org.sangguo.draggertest.module.modules.home.di.HomeMainComponent;
import org.sangguo.draggertest.module.modules.home.inject.InjectHomeMainBarActivity;
import org.sangguo.draggertest.module.modules.home.presenter.MainPresenter;
import org.sangguo.draggertest.observer.ObsData;
import org.sangguo.draggertest.observer.ObsRunnable;
import org.sangguo.draggertest.observer.ObserverFactory;
import org.sangguo.draggertest.observer.example.UserUpdateObserver;

public class MainActivity extends InjectHomeMainBarActivity {

  @Inject @PresenterLifeCycle MainPresenter presenter;

  @Override
  protected void beforeOnCreated(Bundle savedInstanceState) {
    super.beforeOnCreated(savedInstanceState);
    UserUpdateObserver.newInstance().registerObserve(this, new ObsRunnable() {
      @Override public void run(ObsData obsData) {
        Log.i("Observer", "" + obsData);
      }
    });
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState); //dagger注册在onCreate
    setContentView(R.layout.activity_main); //BufferKnife注册在View生成之后
  }

  @Override
  public void inject(HomeMainComponent component) {
    component.inject(this);
  }
}
