package org.sangguo.draggertest.http.callback;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.sangguo.draggertest.R;
import org.sangguo.draggertest.utils.Res;
import org.sangguo.draggertest.utils.RunnablePost;

/**
 * Created by chenwei on 15/9/22.
 * <p/>
 * 底层的http请求回调，负责分发到指定回调状态，并做log的统一打印
 */
public abstract class ResponseInterface implements Callback {

  private boolean isAsync = true;//请求的环境是否是异步环境（非主线程）

  public ResponseInterface() {
  }

  //请求准备阶段准备，获取一些环境数据
  //最好再获取用户ID，以免请求和返回不是同一个用户 // TODO: 2017/6/8
  public final ResponseInterface prepare() {
    isAsync = Looper.getMainLooper() != Looper.myLooper();
    return this;
  }

  /**
   * 请求的返回是否还需要（如宿主的Activity或Fragment销毁时，返回不需要处理）
   */
  protected boolean isShouldDealResponse() {
    return true;
  }

  //返回成功且status==200
  public abstract void onResponseSuccess(JSONObject response);

  //返回成功但是status!=200或者返回失败
  public abstract void onResponseFailure(int statusCode, String errorMsg);

  public void onResponseFailureJSONObject(int statusCode, JSONObject jsonObject) {
  }

  //回调结束
  public abstract void onResponseFinish();

  //返回成功且status==200
  private void handleResponseSuccess(final JSONObject response) {

    RunnablePost.postOn(new Runnable() {
      @Override
      public void run() {
        if (isShouldDealResponse()) {
          onResponseSuccess(response);
        }
      }
    }, isAsync ? Looper.getMainLooper() : null);
  }

  //返回成功但是status!=200或者返回失败
  private void handleResponseFailure(final int statusCode, final String errorMsg) {
    RunnablePost.postOn(new Runnable() {
      @Override
      public void run() {
        if (isShouldDealResponse()) {
          onResponseFailure(statusCode, errorMsg);
        }
      }
    }, isAsync ? Looper.getMainLooper() : null);
  }

  //回调结束
  private void handleResponseFinish() {
    RunnablePost.postOn(new Runnable() {
      @Override
      public void run() {
        if (isShouldDealResponse()) {
          onResponseFinish();
        }
      }
    }, isAsync ? Looper.getMainLooper() : null);
  }

  private void handleResponseFailureJSONObject(final int status, final JSONObject jsonObject) {
    RunnablePost.postOn(new Runnable() {
      @Override
      public void run() {
        if (isShouldDealResponse()) {
          onResponseFailureJSONObject(status, jsonObject);
        }
      }
    }, isAsync ? Looper.getMainLooper() : null);
  }

  /**
   * 判断是否返回成功
   */
  protected boolean isResponseSuccess(JSONObject body) {
    boolean hasStatus = body.has("status");
    if (hasStatus) {
      int status = body.optInt("status");
      return (status == 200);
    }
    return false;
  }

  @Override public void onFailure(Call call, IOException e) {
    Log.i("http", "onFailure :");
    handleResponseFinish();
    handleResponseFailure(NetErrorCode.NETWORK_FAIL, Res.string(R.string.connect_network_fail));
  }

  @Override public void onResponse(Call call, Response response) throws IOException {
    if (!response.isSuccessful()) {
      Log.i("http", "onResponse :is Not Successful");
      handleResponseFinish();
      handleResponseFailure(response.code(), response.message());
    } else {
      String jsonString = response.body().string();

      if (TextUtils.isEmpty(jsonString)) {
        handleResponseFinish();
        handleResponseFailure(NetErrorCode.SERVER_RESPONSE_EMPTY,
            Res.string(R.string.server_response_empty));
        return;
      }
      Log.i("http", "onSuccess :" + jsonString);
      JSONObject jsonObject;
      try {
        jsonObject = new JSONObject(jsonString);
      } catch (JSONException e) {
        handleResponseFinish();
        handleResponseFailure(NetErrorCode.SERVER_EXCEPTION, Res.string(R.string.server_exception));
        e.printStackTrace();
        return;
      }

      if (isResponseSuccess(jsonObject)) {
        handleResponseFinish();
        handleResponseSuccess(jsonObject);
      } else {
        int status = jsonObject.optInt("status", NetErrorCode.SERVER_EXCEPTION);
        handleResponseFinish();
        handleResponseFailure(status, jsonObject.optString("msg"));
        handleResponseFailureJSONObject(status, jsonObject);
      }
    }
  }
}
