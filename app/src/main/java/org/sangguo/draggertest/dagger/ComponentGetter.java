package org.sangguo.draggertest.dagger;

/**
 * 所在用到Component的组件（Activity或者Fragment等）继承它，提供Component
 */
public interface ComponentGetter<C> {
  public C getComponent();
}
