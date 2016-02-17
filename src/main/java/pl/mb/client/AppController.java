package pl.mb.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import pl.mb.client.logs.LogsPresenter;
import pl.mb.client.main.MainPresenter;
import pl.mb.client.mvp.Presenter;
import pl.mb.client.reports.ReportsPresenter;

import java.util.HashMap;
import java.util.Map;

public class AppController implements Presenter, ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private HasWidgets container;
    private MainPresenter mainPresenter;
    private Map<String, Presenter> presenters;

    public AppController(HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.mainPresenter = new MainPresenter();

        presenters = new HashMap<String, Presenter>();
        presenters.put(Tokens.LOGS, new LogsPresenter());
        presenters.put(Tokens.REPORTS, new ReportsPresenter());

        bind();
    }

    private void bind() {
        History.addValueChangeHandler(this);
    }

    @Override
    public void go(final HasWidgets con) {
        container = con;
        mainPresenter.go(container);

        if ("".equals(History.getToken())) {
            History.newItem(Tokens.LOGS);
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token == null || presenters.get(token) == null) {
            return;
        }

        Presenter presenter = presenters.get(token);
        mainPresenter.activateMenuItem(token);
        presenter.go(mainPresenter.mainContainer());
    }
}