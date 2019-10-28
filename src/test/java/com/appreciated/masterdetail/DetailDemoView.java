package com.appreciated.masterdetail;

import com.appreciated.masterdetail.component.ChatView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route("master/detail")
public class DetailDemoView extends ChatView implements HasUrlParameter<Integer> {

    public DetailDemoView() {
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer parameter) {
        if (parameter != null) {
            addMessage("Test" + parameter, false);
            addMessage("Test" + parameter, false);
            addMessage("Test" + parameter, false);
            addMessage("Test" + parameter, false);
            addMessage("Test" + parameter, false);
            addMessage("Test" + parameter, false);
            addMessage("Test" + parameter, false);
        } else {
            removeAll();
            add(new Label("Nothing to do here!"));
            setAlignItems(Alignment.CENTER);
            setJustifyContentMode(JustifyContentMode.CENTER);
        }
    }

}