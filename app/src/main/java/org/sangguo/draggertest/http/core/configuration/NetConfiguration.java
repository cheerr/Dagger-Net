package org.sangguo.draggertest.http.core.configuration;

import org.json.JSONObject;
import org.sangguo.draggertest.http.core.ApiImpl;
import org.sangguo.draggertest.http.core.JsonChanger;
import org.sangguo.draggertest.http.core.configuration.okhttpclient.SimpleHttpClient;
import org.sangguo.draggertest.http.core.util.ILog;
import org.sangguo.draggertest.http.data.Result;
import org.sangguo.draggertest.http.data.SimpleResult;

public class NetConfiguration {

  private NetOptions defaultNetOptions;

  private NetConfiguration() {
  }

  public static class Builder {

    private NetConfiguration netConfiguration;

    public Builder() {
      netConfiguration = new NetConfiguration();
    }

    public Builder setDefaultNetOptions(NetOptions netOptions) {
      netConfiguration.defaultNetOptions = netOptions;
      return this;
    }

    public Builder setDebug(boolean debug) {
      ILog.setDebug(debug);
      return this;
    }

    public void build() {
      initWithoutNull();
      ApiImpl.optionsDefault = netConfiguration.defaultNetOptions;
    }

    //检查null
    private void initWithoutNull() {

      if (netConfiguration.defaultNetOptions == null) {
        netConfiguration.defaultNetOptions = new NetOptions();
      }

      if (netConfiguration.defaultNetOptions.getJsonChanger() == null) {
        netConfiguration.defaultNetOptions.setJsonChanger(new JsonChanger() {
          @Override public Result jsonToResult(JSONObject json) {
            return new SimpleResult(json);
          }
        });
      }
      if (netConfiguration.defaultNetOptions.getOkHttpClient() == null) {
        netConfiguration.defaultNetOptions.setOkHttpClient(new SimpleHttpClient().get());
      }
    }
  }
}
