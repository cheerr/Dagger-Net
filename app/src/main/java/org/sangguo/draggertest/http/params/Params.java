package org.sangguo.draggertest.http.params;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by chenwei on 2017/6/8.
 */

/**
 * 子类扩展，而不是覆盖或者使父类的功能
 * 子类是一种特殊的类型，而不只是父类的一个角色；
 **/
public class Params extends HashMap<String, String> {

  private HashMap<String, File> fileMap;

  public Params() {
    //put("device_token", Build.SERIAL);
    //put("device_type", "android");
    //put("sys_version", Build.VERSION.RELEASE);
    //put("platform", Build.MODEL.replace(" ", ""));
  }

  /**
   * 构造POST请求（包括文件）
   */
  public Request.Builder buildPostBody() {
    if (hasFile()) {
      return buildMultipartBody();
    } else {
      return buildFormBody();
    }
  }

  /**
   * 构造Post 纯表单上传Builder
   */
  private Request.Builder buildFormBody() {
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

  /**
   * 构造Post 多文件上传Builder
   */
  private Request.Builder buildMultipartBody() {
    MultipartBody.Builder bodyBuild =
        new MultipartBody.Builder("-----").setType(MultipartBody.FORM);
    for (Entry<String, String> entry : entrySet()) {
      if (entry != null && !TextUtils.isEmpty(entry.getValue())) {
        bodyBuild.addFormDataPart(entry.getKey(), entry.getValue());
      }
    }
    if (fileMap != null && fileMap.size() > 0) {
      for (Entry<String, File> entry : fileMap.entrySet()) {
        if (entry != null && entry.getValue() != null) {
          bodyBuild.addFormDataPart(entry.getKey(), entry.getValue().getName(),
              RequestBody.create(MediaType.parse("application/octet-stream"), entry.getValue()));
        }
      }
    }
    Request.Builder builder = new Request.Builder();
    builder.post(bodyBuild.build());
    return builder;
  }

  /**
   * 构造Get请求字符串
   */
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

  public boolean hasFile() {
    return fileMap != null && !fileMap.isEmpty();
  }

  public HashMap<String, File> getFileMap() {
    return fileMap;
  }

  /**
   * 上传File
   */
  public void putFile(String key, File file) {
    if (fileMap == null) {
      fileMap = new HashMap<>();
    }
    fileMap.put(key, file);
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
