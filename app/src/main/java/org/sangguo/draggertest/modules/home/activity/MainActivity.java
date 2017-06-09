package org.sangguo.draggertest.modules.home.activity;

import android.os.Bundle;
import javax.inject.Inject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.http.ApiEnum;
import org.sangguo.draggertest.http.callback.WeakResponseListener;
import org.sangguo.draggertest.http.callback.WeakSubscriber;
import org.sangguo.draggertest.http.params.Params;
import org.sangguo.draggertest.modules.home.di.MainActivityComponent;
import org.sangguo.draggertest.modules.home.inject.InjectMainActivity;
import org.sangguo.draggertest.modules.home.model.MainData;
import org.sangguo.draggertest.modules.home.presenter.MainPresenter;

public class MainActivity extends InjectMainActivity {

  @Inject MainPresenter presenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ApiEnum.TEST1.execute(new Params(), MainData.class)
        .subscribe(new WeakSubscriber<MainData>(getActivity()) {
          @Override public void onDataSuccess(MainData mainData) {

          }

          @Override public void onDataFailure() {

          }

          @Override public void onDataCompleted() {

          }
        });

    ApiEnum.TEST3.execute(new Params(), new WeakResponseListener(getActivity()) {

    });
  }

  @Override
  public void inject(MainActivityComponent component) {
    component.inject(this);
  }
}
