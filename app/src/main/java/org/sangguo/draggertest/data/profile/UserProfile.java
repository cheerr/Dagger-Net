package org.sangguo.draggertest.data.profile;

/**
 * Created by chenwei on 2017/6/7.
 */

//用户信息接口类
public interface UserProfile {

  public void initSettings(String uid);

  public void put(String key, Integer v);

  public void put(String key, Long v);

  public void put(String key, Boolean v);

  public void put(String key, String v);

  public void put(String key, Float v);

  public Integer getInt(String key, Integer df);

  public Long getLong(String key, Long df);

  public Boolean getBoolean(String key, Boolean df);

  public Float getFloat(String key, Float df);

  public String getString(String key);
}
