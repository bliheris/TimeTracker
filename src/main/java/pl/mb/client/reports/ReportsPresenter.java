package pl.mb.client.reports;

import com.google.gwt.user.client.ui.HasWidgets;
import pl.mb.client.mvp.Presenter;

public class ReportsPresenter implements Presenter {

    interface Display {
        void showIn(HasWidgets container);
    }

    private Display display;

    public ReportsPresenter() {
        this.display = new ReportsView();
    }

    @Override
    public void go(HasWidgets container) {
        display.showIn(container);
    }
}
