package pl.mb.client.commons;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface IMenuButton extends HasClickHandlers {
    void setActive(boolean active);
    void setHistoryToken(String token);
}
