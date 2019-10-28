package com.appreciated.masterdetail;

import com.appreciated.masterdetail.component.ChatsOverview;
import com.github.appreciated.masterdetail.MasterDetailView;
import com.vaadin.flow.router.Route;

@Route("master")
public class MasterDemoView extends MasterDetailView<ChatsOverview, DetailDemoView, Integer> {

    public MasterDemoView() {
        setMaster(new ChatsOverview());
        setDetail(DetailDemoView.class);
    }
}
