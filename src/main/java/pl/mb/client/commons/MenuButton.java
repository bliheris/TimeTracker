package pl.mb.client.commons;

import org.gwtbootstrap3.client.ui.LinkedGroupItem;

public class MenuButton extends LinkedGroupItem implements IMenuButton {

    public MenuButton(String text) {
        super();
        setText(text);
    }
}
