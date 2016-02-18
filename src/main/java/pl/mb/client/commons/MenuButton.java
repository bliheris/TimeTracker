package pl.mb.client.commons;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import org.gwtbootstrap3.client.ui.LinkedGroupItem;

public class MenuButton extends LinkedGroupItem implements IMenuButton {

    public MenuButton(String text) {
        super();
        setText(text);
    }

    @Override
    public void setHistoryToken(final String token){
        setTargetHistoryToken(token);
        addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                History.newItem(token);
            }
        });
    }
}
