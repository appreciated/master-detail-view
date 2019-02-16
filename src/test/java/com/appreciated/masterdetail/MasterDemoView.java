package com.appreciated.masterdetail;

import com.appreciated.masterdetail.component.ChatOverview;
import com.appreciated.masterdetail.view.masterdetail.MasterDetailView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.router.Route;

@Route("")
public class MasterDemoView extends MasterDetailView {

    private ChatOverview overview;

    public MasterDemoView() {
        setMaster(getMasterView());
        setDetail(DetailDemoView.class, 0, true);
    }

    private Component getMasterView() {
        overview = new ChatOverview(integer -> setDetail(DetailDemoView.class, integer));
        return overview;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
    }
}
