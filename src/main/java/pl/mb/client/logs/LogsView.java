package pl.mb.client.logs;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HasWidgets;
import org.gwtbootstrap3.client.ui.Row;
import pl.mb.client.CustomForm;
import pl.mb.client.MainCellTable;
import pl.mb.shared.project.ProjectRepository;
import pl.mb.shared.timeRecord.TimeRecord;
import pl.mb.shared.timeRecord.TimeRecordRepository;

import java.util.Date;
import java.util.List;

class LogsView implements LogsPresenter.Display {

    private ProjectRepository projectRepo = new ProjectRepository();
    private TimeRecordRepository trRepo = new TimeRecordRepository();

    private Row inputRow;
    private Row valuesRow;

    private CustomForm form;
    private MainCellTable cellTable;

    public LogsView() {

        form = new CustomForm();
        cellTable = new MainCellTable();

        inputRow = new Row();
        inputRow.add(form);

        valuesRow = new Row();
        valuesRow.setPaddingTop(20);
        valuesRow.add(cellTable);
    }

    @Override
    public void showIn(HasWidgets container) {
        container.add(inputRow);
        container.add(valuesRow);
    }

    @Override
    public void setRecords(List<TimeRecord> records) {
        cellTable.clearData();
        for (TimeRecord record : records) {
            cellTable.addRecord(record);
        }
    }

    @Override
    public void addRecord(TimeRecord timeRecord) {
        cellTable.addRecord(timeRecord);
    }

    @Override
    public void setProjectAutoSuggest(List<String> projects) {
        form.setProjectAutoSuggest(projects);
    }

    @Override
    public void addLogHandler(ClickHandler clickHandler) {
        form.addLogHandler(clickHandler);
    }

    @Override
    public void clearForm() {
        form.clearForm();
    }

    @Override
    public Date dateValue() {
        return form.datePickerValue();
    }

    @Override
    public String projectValue() {
        return form.projectValue();
    }

    @Override
    public String timeValue() {
        return form.timeValue();
    }

    @Override
    public String descriptionValue() {
        return form.descriptionValue();
    }
}
