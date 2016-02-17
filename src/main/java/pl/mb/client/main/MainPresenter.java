package pl.mb.client.main;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Column;
import pl.mb.client.Tokens;
import pl.mb.client.commons.IMenuButton;
import pl.mb.client.mvp.Presenter;

import java.util.HashMap;
import java.util.Map;

public class MainPresenter implements Presenter {

    interface Display {

        IMenuButton logs();
        IMenuButton projects();
        IMenuButton reports();

        Column mainColumn();
        Widget asWidget();
    }

    private Display display;
    private Map<String, IMenuButton> menu;

    public MainPresenter() {
        display = new MainView();
        menu = new HashMap<String, IMenuButton>();
        menu.put(Tokens.LOGS, display.logs());
        menu.put(Tokens.PROJECTS, display.projects());
        menu.put(Tokens.REPORTS, display.reports());
    }

    @Override
    public void go(final HasWidgets container) {
        bind();
        container.clear();
        container.add(display.asWidget());
    }

    private void bind() {
        display.logs().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                History.newItem(Tokens.LOGS);
            }
        });
        display.reports().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                History.newItem(Tokens.REPORTS);
            }
        });
    }

    public void activateMenuItem(String token){
        for (IMenuButton button : menu.values()) {
            button.setActive(false);
        }
        menu.get(token).setActive(true);
    }

    public HasWidgets mainContainer() {
        return display.mainColumn();
    }

}
