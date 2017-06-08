package org.sangguo.draggertest.modules.home.model;

import org.sangguo.draggertest.modules.abs.BaseBean;

/**
 * Created by chenwei on 2017/6/7.
 */

public class MainData extends BaseBean {

  private String data = getClass().getSimpleName();

  public String text() {
    return data;
  }
}
