package org.sangguo.draggertest.observer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chenwei on 2017/6/12.
 */

public class ObsData implements Serializable {

  private HashMap<String, Object> dataMap = new HashMap<>();

  public ObsData put(String key, Object value) {
    if (key != null) {
      dataMap.put(key, value);
    }
    return this;
  }

  public <T> T get(String key, T df) {
    if (key != null) {
      Object obj = dataMap.get(key);

      if (obj != null) return (T) obj;
    }
    return df;
  }

  public <T> T get(String key) {
    return get(key, null);
  }

  @Override
  public String toString() {
    Iterator<Map.Entry<String, Object>> i = dataMap.entrySet().iterator();
    if (!i.hasNext()) {
      return "{}" + "@" + hashCode();
    }

    StringBuilder sb = new StringBuilder();
    sb.append('{');
    for (; ; ) {
      Map.Entry<String, Object> e = i.next();
      String key = e.getKey();
      Object value = e.getValue();
      sb.append(key);
      sb.append('=');
      sb.append(value == this ? "(this Map)" : value);
      if (!i.hasNext()) {
        return sb.append('}').toString() + "@" + hashCode();
      }
      sb.append(", ");
    }
  }
}
