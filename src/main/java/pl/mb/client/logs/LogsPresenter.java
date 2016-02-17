package pl.mb.client.logs;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import pl.mb.client.mvp.Presenter;

public class LogsPresenter implements Presenter {

    interface Display {
        Widget asWidget();
    }

    private Display display;

    public LogsPresenter() {
        display = new LogsView();
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
