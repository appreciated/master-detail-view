package com.appreciated.masterdetail;

import com.github.appreciated.card.ClickableCard;
import com.vaadin.flow.component.html.Div;

public class ExampleCard extends ClickableCard {
    public ExampleCard() {
        Div div = new Div();
        div.setSizeFull();
        add(div);
        setSizeFull();
    }
}