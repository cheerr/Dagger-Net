package org.sangguo.draggertest.http.data;

import java.io.Serializable;
import org.json.JSONObject;

/**
 * Created by chenwei on 2017/6/9.
 */

public class SimpleResult implements Result, Serializable {

  private String data;
  private int code;
  private String msg;

  public SimpleResult(JSONObject jsonObject) {
    if (jsonObject == null) return;
    data = jsonObject.optString("data", "");
    code = jsonObject.optInt("code", 0);
    msg = jsonObject.optString("msg", "");
  }

  @Override public String data() {
    return data;
  }

  @Override public int code() {
    return code;
  }

  @Override public String msg() {
    return msg;
  }
}
