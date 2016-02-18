package pl.mb.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;
import pl.mb.client.logs.LogsPresenter;
import pl.mb.client.main.MainPresenter;
import pl.mb.client.mvp.Presenter;
import pl.mb.client.projects.ProjectsPresenter;
import pl.mb.client.reports.ReportsPresenter;
import pl.mb.shared.project.Project;
import pl.mb.shared.project.ProjectRepository;
import pl.mb.shared.timeRecord.TimeRecordRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppController implements Presenter, ValueChangeHandler<String> {

    private final HandlerManager eventBus;
    private HasWidgets container;
    private MainPresenter mainPresenter;
    private Map<String, Presenter> presenters;

    private TimeRecordRepository trRepo = new TimeRecordRepository();
    private ProjectRepository projectRepo = new ProjectRepository();

    public AppController(HandlerManager eventBus) {
        this.eventBus = eventBus;
        this.mainPresenter = new MainPresenter();

        presenters = new HashMap<String, Presenter>();
        presenters.put(Tokens.LOGS, new LogsPresenter(trRepo, projectRepo));
        presenters.put(Tokens.PROJECTS, new ProjectsPresenter(projectRepo));
        presenters.put(Tokens.REPORTS, new ReportsPresenter());

        bind();


        projectRepo.create("Solin");
        projectRepo.create("Praca");
        projectRepo.create("Dom");
        Project project = projectRepo.create("Studia");
        trRepo.create(new Date(), "2:30", project, "Inżynierka");
        trRepo.create(new Date(), "1:20", project, "Inżynierka");
        trRepo.create(new Date(), "5:40", project, "Inżynierka");
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
        mainPresenter.mainContainer().clear();
        presenter.go(mainPresenter.mainContainer());
    }
}