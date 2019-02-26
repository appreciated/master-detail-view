package com.appreciated.masterdetail;

import com.appreciated.masterdetail.component.ChatView;
import com.appreciated.masterdetail.view.masterdetail.DetailView;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

@Route("detail")
public class DetailDemoView extends ChatView implements HasUrlParameter<Integer>, DetailView<Integer> {

    public DetailDemoView() {
    }

    @Override
    public void setParameter(BeforeEvent event, Integer parameter) {
        addMessage("Test" + parameter, false);
        addMessage("Test" + parameter, false);
        addMessage("Test" + parameter, false);
        addMessage("Test" + parameter, false);
        addMessage("Test" + parameter, false);
        addMessage("Test" + parameter, false);
        addMessage("Test" + parameter, false);
    }

}