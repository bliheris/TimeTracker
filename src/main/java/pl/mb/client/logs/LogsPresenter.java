package pl.mb.client.logs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import pl.mb.client.mvp.Presenter;
import pl.mb.shared.project.Project;
import pl.mb.shared.project.ProjectRepository;
import pl.mb.shared.timeRecord.TimeRecord;
import pl.mb.shared.timeRecord.TimeRecordRepository;

import java.util.Date;
import java.util.List;

public class LogsPresenter implements Presenter {

    interface Display {
        void showIn(HasWidgets container);
        void setRecords(List<TimeRecord> records);
        void addRecord(TimeRecord timeRecord);
        void setProjectAutoSuggest(List<String> projects);
        void addLogHandler(ClickHandler clickHandler);
        void clearForm();


        Date dateValue();
        String projectValue();
        String timeValue();
        String descriptionValue();
    }


    private Display display;
    private TimeRecordRepository repo;
    private ProjectRepository projectRepo;

    public LogsPresenter(TimeRecordRepository trRepo, ProjectRepository projectRepository) {
        display = new LogsView();
        repo = trRepo;
        projectRepo = projectRepository;

        bind();
    }

    private void bind(){

        display.addLogHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                createTimeRecord();
            }
        });
    }

    private void createTimeRecord() {
        Project project = projectRepo.loadOrCreate(display.projectValue());
        TimeRecord timeRecord = repo.create(
                display.dateValue(),
                display.timeValue(),
                project,
                display.descriptionValue()
        );
        display.addRecord(timeRecord);
        display.clearForm();
    }

    @Override
    public void go(HasWidgets container) {
        display.showIn(container);
        display.setRecords(repo.getAllRecords());
        display.setProjectAutoSuggest(projectRepo.getAllProjectsNames());
    }
}
