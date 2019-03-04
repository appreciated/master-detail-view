package com.appreciated.masterdetail.component;

import com.github.appreciated.card.StatefulCard;
import com.github.appreciated.card.StatefulCardGroup;
import com.github.appreciated.card.content.IconItem;
import com.github.appreciated.card.content.ItemBody;
import com.github.appreciated.masterdetail.MasterView;
import com.github.appreciated.masterdetail.MasterViewNavigationElementListener;
import com.vaadin.flow.component.html.Div;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;

public class ChatOverview extends StatefulCardGroup<StatefulCard> implements MasterView<Integer> {

    private MasterViewNavigationElementListener<Integer> listener;

    public ChatOverview() {
        for (int i = 0; i < 20; i++) {
            add(getCard(i));
        }
    }

    private StatefulCard getCard(int i) {
        Div img = new Div();
        img.getStyle().set("background", "var(--lumo-primary-color)");
        img.setWidth("50px");
        img.setHeight("50px");
        img.getStyle().set("border-radius", "100%");

        Fairy fairy = Fairy.create();
        Person person = fairy.person();
        StatefulCard card = new StatefulCard(event -> listener.onMasterViewNavigationEvent(i), new IconItem(img, new ItemBody(person.getFullName(), "My telephone number is " + person.getTelephoneNumber())));
        card.setElevation(0);
        card.setWidth("100%");
        return card;
    }

    @Override
    public void setNavigationListener(MasterViewNavigationElementListener<Integer> listener) {
        this.listener = listener;
    }

    @Override
    public void setActiveElement(Integer element) {
        setState(getCards().get(element));
    }

}
