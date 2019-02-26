package com.github.appreciated.masterdetail;

@FunctionalInterface
public interface MasterViewNavigationElementListener<T> {
    void onMasterViewNavigationEvent(T parameter);
}
