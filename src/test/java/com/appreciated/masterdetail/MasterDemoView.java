package com.appreciated.masterdetail;

import com.appreciated.masterdetail.view.masterdetail.MasterDetailView;
import com.github.appreciated.card.ClickableCard;
import com.github.appreciated.card.content.IconItem;
import com.github.appreciated.card.content.ItemBody;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

@Route("")
public class MasterDemoView extends MasterDetailView {

    public MasterDemoView() {
        setMaster(getMasterView());
    }

    private Component getMasterView() {
        VerticalLayout layout = new VerticalLayout();
        for (int i = 0; i < 20; i++) {
            layout.add(getCard(i));
        }
        layout.setSizeFull();
        layout.setSpacing(false);
        layout.setMargin(false);
        layout.setPadding(false);
        return layout;
    }

    private ClickableCard getCard(int i) {
        Div img = new Div();
        img.getStyle().set("background", "var(--lumo-primary-color)");
        img.setWidth("50px");
        img.setHeight("50px");

        img.getStyle().set("border-radius", "100%");

        Fairy fairy = Fairy.create();
        Person person = fairy.person();

        ClickableCard card = new ClickableCard(event -> setDetail(DetailDemoView.class, new Integer(i)), new IconItem(img, new ItemBody(person.getFullName(), "My telephone number is " + person.getTelephoneNumber())));
        card.setElevation(0);
        card.setWidth("100%");

        return card;
    }
}
