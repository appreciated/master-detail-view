package com.appreciated.masterdetail.component;

import com.appreciated.masterdetail.view.masterdetail.MasterView;
import com.appreciated.masterdetail.view.masterdetail.MasterViewNavigationElementListener;
import com.github.appreciated.card.ClickableCard;
import com.github.appreciated.card.content.IconItem;
import com.github.appreciated.card.content.ItemBody;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

import java.util.ArrayList;
import java.util.List;

public class ChatOverview extends VerticalLayout implements MasterView<Integer> {

    private MasterViewNavigationElementListener<Integer> listener;

    public ChatOverview() {
        List<ClickableCard> cards = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            cards.add(getCard(i));
        }
        setSizeFull();
        setSpacing(false);
        setMargin(false);
        setPadding(false);
        cards.forEach(this::add);
    }

    private ClickableCard getCard(int i) {
        Div img = new Div();
        img.getStyle().set("background", "var(--lumo-primary-color)");
        img.setWidth("50px");
        img.setHeight("50px");
        img.getStyle().set("border-radius", "100%");

        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        ClickableCard card = new ClickableCard(event -> listener.onMasterViewNavigationEvent(i), new IconItem(img, new ItemBody(person.getFullName(), "My telephone number is " + person.getTelephoneNumber())));
        card.setElevation(0);
        card.setWidth("100%");
        return card;
    }

    @Override
    public void setNavigationListener(MasterViewNavigationElementListener<Integer> listener) {
        this.listener = listener;
    }
}
