package com.appreciated.masterdetail.view.masterdetail;

@FunctionalInterface
public interface MasterViewNavigationElementListener<T> {
    void onMasterViewNavigationEvent(T parameter);
}
