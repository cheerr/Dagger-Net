package org.sangguo.draggertest.module.abs.presenter;

import java.lang.reflect.Field;

/**
 * Created by chenwei on 2017/6/11.
 */

public class PresenterInjector {

  /**
   * 注册Presenter，在onViewCreated 中调用
   */
  public static void injectPresenter(PresenterSetter setter) {
    if (setter == null) return;
    Field[] fields = setter.getClass().getDeclaredFields();

    for (Field field : fields) {

      PresenterLifeCycle annotation = field.getAnnotation(PresenterLifeCycle.class);
      if (annotation != null) {
        field.setAccessible(true);
        try {
          Object obj = field.get(setter);
          if (obj instanceof Presenter) {
            setter.setPresenter((Presenter) obj);
            return;
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
