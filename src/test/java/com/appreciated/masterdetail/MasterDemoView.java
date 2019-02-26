package com.appreciated.masterdetail;

import com.appreciated.masterdetail.component.ChatOverview;
import com.appreciated.masterdetail.view.masterdetail.MasterDetailView;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.Route;

@Route("master")
public class MasterDemoView extends MasterDetailView<ChatOverview, DetailDemoView, Integer> {

    public MasterDemoView() {
        setMaster(new ChatOverview());
        setDetail(DetailDemoView.class);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer integer) {
        super.setParameter(beforeEvent, integer);
    }
}
