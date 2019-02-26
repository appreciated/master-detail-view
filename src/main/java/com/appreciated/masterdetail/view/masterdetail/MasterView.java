package com.appreciated.masterdetail.view.masterdetail;


public interface MasterView<T> {
    void setNavigationListener(MasterViewNavigationElementListener<T> listener);
}
