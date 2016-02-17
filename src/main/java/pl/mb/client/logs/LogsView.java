package pl.mb.client.logs;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Row;
import pl.mb.client.CustomForm;
import pl.mb.client.MainCellTable;
import pl.mb.shared.project.Project;
import pl.mb.shared.project.ProjectRepository;
import pl.mb.shared.timeRecord.TimeRecord;
import pl.mb.shared.timeRecord.TimeRecordRepository;

import java.util.Date;

class LogsView extends Row implements LogsPresenter.Display {

    private ProjectRepository projectRepo = new ProjectRepository();
    private TimeRecordRepository trRepo = new TimeRecordRepository();

    public LogsView() {

        final CustomForm form = new CustomForm();

        final MainCellTable cellTable = new MainCellTable();

        Row inputRow = new Row();
        inputRow.add(form);

        Row valuesRow = new Row();
        valuesRow.setPaddingTop(20);
        valuesRow.add(cellTable);

        add(inputRow);
        add(valuesRow);

        form.addLogHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                Project project = projectRepo.loadOrCreate(form.projectValue());
                TimeRecord timeRecord = trRepo.create(
                        form.datePickerValue(),
                        form.timeValue(),
                        project,
                        form.descriptionValue()
                );
                cellTable.addRecord(timeRecord);
                form.clearForm();
            }
        });

        Project project = projectRepo.create("Studia");

        cellTable.addRecord(trRepo.create(new Date(), "2:30", project, "Inżynierka"));
        cellTable.addRecord(trRepo.create(new Date(), "1:20", project, "Inżynierka"));
        cellTable.addRecord(trRepo.create(new Date(), "5:40", project, "Inżynierka"));
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
