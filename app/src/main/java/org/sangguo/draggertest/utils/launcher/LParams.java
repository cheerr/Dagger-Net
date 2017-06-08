package org.sangguo.draggertest.utils.launcher;

/**
 * Created by chenwei on 2017/6/7.
 */

public class LParams extends LParamsImpl {

  private LParams() {
    super();
  }

  public static LParamsImpl create() {
    return new LParams();
  }
}
