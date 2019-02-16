package com.appreciated.masterdetail;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

@StyleSheet("frontend://styles.css")
public class ChatView extends VerticalLayout {
    VerticalLayout messageHolder = new VerticalLayout();
    TextField inputField = new TextField();
    Button sendButton = new Button(VaadinIcon.PAPERPLANE_O.create());
    private boolean reply;

    public ChatView() {
        add(messageHolder);
        messageHolder.setSizeFull();
        messageHolder.setMargin(false);
        sendButton.addClickListener(event -> {
            if (inputField.getValue() != null && !inputField.getValue().equals("")) {
                addMessage(inputField.getValue(), reply);
                reply = !reply;
            }
        });
        add(getInputBar());
        setSizeFull();
        messageHolder.setJustifyContentMode(JustifyContentMode.END);
        messageHolder.setMargin(false);
        messageHolder.setPadding(false);
        setMargin(false);
    }

    public void addMessage(String message, boolean fromUser) {
        Div messageView = new Div();
        messageView.addClassName("message");
        messageView.addClassName(fromUser ? "user-message" : "friend-message");
        messageView.add(new Label(message));
        inputField.setValue("");
        inputField.focus();
        messageHolder.add(messageView);
        if (fromUser) {
            messageView.getStyle().set("align-self", "flex-end");
        }
    }

    HorizontalLayout getInputBar() {
        HorizontalLayout wrapper = new HorizontalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("100%");
        inputField.setWidth("100%");
        sendButton.setWidth("50px");
        HorizontalLayout inputBar = new HorizontalLayout(inputField, sendButton);
        inputBar.setWidth("100%");
        wrapper.add(inputBar);
        return wrapper;
    }
}
