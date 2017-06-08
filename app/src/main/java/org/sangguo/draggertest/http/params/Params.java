package org.sangguo.draggertest.http.params;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.FormBody;
import okhttp3.Request;

/**
 * Created by chenwei on 2017/6/8.
 */

public class Params extends HashMap<String, String> {

  public Params() {
    //put("device_token", Build.SERIAL);
    //put("device_type", "android");
    //put("sys_version", Build.VERSION.RELEASE);
    //put("platform", Build.MODEL.replace(" ", ""));
  }

  public Request.Builder buildPostBody() {
    FormBody.Builder bodyBuild = new FormBody.Builder();
    for (Entry<String, String> entry : entrySet()) {
      if (entry != null && !TextUtils.isEmpty(entry.getValue())) {
        bodyBuild.add(entry.getKey(), entry.getValue());
      }
    }
    Request.Builder builder = new Request.Builder();
    builder.post(bodyBuild.build());
    return builder;
  }

  public String buildGetUrl() {
    StringBuilder builder = new StringBuilder();
    Iterator<Entry<String, String>> iter = entrySet().iterator();
    boolean isF = true;
    while (iter.hasNext()) {
      Entry<String, String> entry = iter.next();
      if (entry != null && !TextUtils.isEmpty(entry.getValue())) {
        builder.append(isF ? "" : "&")
            .append(entry.getKey())
            .append("=")
            .append(entry.getValue());
        isF = false;
      }
    }
    return builder.toString();
  }

  public void put(String key, int v) {
    put(key, String.valueOf(v));
  }

  public void put(String key, long v) {
    put(key, String.valueOf(v));
  }

  public void put(String key, ArrayList<Integer> v) {
    put(key, String.valueOf(v));
  }

  public void put(String key, float v) {
    put(key, String.valueOf(v));
  }

  public void put(String key, double v) {
    put(key, String.valueOf(v));
  }

  public void put(String key, boolean v) {
    put(key, String.valueOf(v));
  }

  public void put(String key, char v) {
    put(key, String.valueOf(v));
  }
}
