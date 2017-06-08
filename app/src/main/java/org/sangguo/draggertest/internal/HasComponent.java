package org.sangguo.draggertest.internal;

/**
 * 所在用到Component的组件（Activity或者Fragment等）继承它，提供Component
 */
public interface HasComponent<C> {
  public C getComponent();

  public void inject(C component);
}
