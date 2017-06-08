package org.sangguo.draggertest.utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 17/3/21.
 */

public class WeakReferenceList<T> {

  private List<WeakReference<T>> list = new ArrayList<>();

  public void add(T t) {
    if (contains(t) == -1) {
      synchronized (this) {
        list.add(new WeakReference<T>(t));
      }
    }
  }

  public synchronized void remove(T t) {
    int index = contains(t);
    if (index >= 0) {
      synchronized (this) {
        list.remove(index);
      }
    }
  }

  public int contains(T t) {
    synchronized (this) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i) != null && list.get(i).get() == t) {
          return i;
        }
      }
      return -1;
    }
  }

  public synchronized void clear() {
    synchronized (this) {
      list.clear();
    }
  }

  public List<T> getList() {
    List<T> retList = new ArrayList<>();
    synchronized (this) {
      for (WeakReference<T> reference : list) {
        if (reference != null && reference.get() != null) {
          retList.add(reference.get());
        }
      }
    }
    return retList;
  }
}
