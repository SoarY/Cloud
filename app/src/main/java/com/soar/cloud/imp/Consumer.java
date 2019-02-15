package com.soar.cloud.imp;

/**
 * NAME：YONG_
 * Created at: 2019/2/15
 * Describe:
 */
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
