package com.github.appreciated.masterdetail;


public interface MasterView<T> {
    void setNavigationListener(MasterViewNavigationElementListener<T> listener);
}
