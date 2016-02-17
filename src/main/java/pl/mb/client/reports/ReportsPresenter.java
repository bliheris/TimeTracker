package pl.mb.client.reports;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import pl.mb.client.mvp.Presenter;

public class ReportsPresenter implements Presenter {

    interface Display {
        Widget asWidget();
    }

    private Display display;

    public ReportsPresenter() {
        this.display = new ReportsView();
    }

    @Override
    public void go(HasWidgets container) {
        //bind();
        container.clear();
        container.add(display.asWidget());
        //fetchContactDetails();
    }
}
