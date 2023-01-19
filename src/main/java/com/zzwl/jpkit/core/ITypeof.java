package com.zzwl.jpkit.core;

/**
 * 解析返回类型
 *
 * @param <T> 解析类型
 */
public interface ITypeof<T> {
    /**
     * 返回对应的值
     *
     * @return 对应值
     */
    T getValue();

    String apply(String name);
}
