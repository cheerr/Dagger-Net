package org.sangguo.draggertest.module.model;

import java.io.Serializable;
import org.json.JSONObject;
import thirdparty.http.lib.data.Result;

/**
 * 默认返回数据模板
 * Created by chenwei on 2017/6/9.
 */

public class SimpleResult implements Result, Serializable {

  private String data;
  private int code;
  private String msg;

  public SimpleResult(JSONObject jsonObject) {
    if (jsonObject == null) return;
    data = jsonObject.optString("data", "");
    code = jsonObject.optInt("code", -1);
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

  @Override public boolean isSuccess() {
    return code == 0;
  }

  @Override public String toString() {
    return "SimpleResult{" +
        "data='" + data + '\'' +
        ", code=" + code +
        ", msg='" + msg + '\'' +
        '}';
  }
}
