package pl.mb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.RootPanel;

public class TimeTracker implements EntryPoint {

    public void onModuleLoad() {
        HandlerManager eventBus = new HandlerManager(null);
        AppController appViewer = new AppController(eventBus);
        appViewer.go(RootPanel.get());
    }
}
