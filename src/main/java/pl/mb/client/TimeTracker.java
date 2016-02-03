package pl.mb.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwt.charts.client.*;
import com.googlecode.gwt.charts.client.corechart.PieChart;
import com.googlecode.gwt.charts.client.corechart.PieChartOptions;
import com.googlecode.gwt.charts.client.event.ReadyEvent;
import com.googlecode.gwt.charts.client.event.ReadyHandler;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import pl.mb.shared.project.Project;
import pl.mb.shared.project.ProjectRepository;
import pl.mb.shared.timeRecord.TimeRecord;
import pl.mb.shared.timeRecord.TimeRecordRepository;

import java.util.Date;

public class TimeTracker implements EntryPoint {

    private PieChart chart;

    private ProjectRepository projectRepo = new ProjectRepository();
    private TimeRecordRepository trRepo = new TimeRecordRepository();

    public void onModuleLoad() {

        PageHeader pageHeader = new PageHeader();
        pageHeader.setText("Welcome to TimeTracker");
        pageHeader.setSubText("Time tracking application");


        Row firstRow = new Row();
        Column menuColumn = new Column(ColumnSize.MD_2);
        Column mainColumn = new Column(ColumnSize.MD_10);

        LinkedGroupItem lgi1 = new LinkedGroupItem();
        lgi1.setText("Logs");
        lgi1.setActive(true);

        final LinkedGroupItem lgi2 = new LinkedGroupItem();
        lgi2.setText("Projects");

        LinkedGroupItem lgi3 = new LinkedGroupItem();
        lgi3.setText("Reports");

        LinkedGroup menu = new LinkedGroup();
        menu.add(lgi1);
        menu.add(lgi2);
        menu.add(lgi3);

        menuColumn.add(menu);

        firstRow.add(menuColumn);
        firstRow.add(mainColumn);

        Row inputRow = new Row();
        final CustomForm form = new CustomForm();
        inputRow.add(form);

        final MainCellTable cellTable = new MainCellTable();
        Row valuesRow = new Row();
        valuesRow.setPaddingTop(20);
        valuesRow.add(cellTable);

        final Row chartRow = new Row();

        mainColumn.add(inputRow);
        mainColumn.add(valuesRow);
        mainColumn.add(chartRow);
        //mainColumn.add(cellTable.getPager());

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

        Container container = new Container();
        container.add(pageHeader);
        container.add(firstRow);


        ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
        chartLoader.loadApi(new Runnable() {

            @Override
            public void run() {
                // Create and attach the chart
                chart = new PieChart();
                chart.setHeight("400px");
                chartRow.add(chart);
                draw();
            }
        });

        Project project = projectRepo.create("Studia");

        cellTable.addRecord(trRepo.create(new Date(), "2:30", project, "Inżynierka"));
        cellTable.addRecord(trRepo.create(new Date(), "1:20", project, "Inżynierka"));
        cellTable.addRecord(trRepo.create(new Date(), "5:40", project, "Inżynierka"));

        RootPanel.get().add(container);
    }

    private void draw() {
        // Prepare the data
        DataTable dataTable = DataTable.create();
        dataTable.addColumn(ColumnType.STRING, "Task");
        dataTable.addColumn(ColumnType.NUMBER, "Hours per Day");
        dataTable.addRows(5);
        dataTable.setValue(0, 0, "Work");
        dataTable.setValue(0, 1, 11);
        dataTable.setValue(1, 0, "Sleep");
        dataTable.setValue(1, 1, 7);
        dataTable.setValue(2, 0, "Watch TV");
        dataTable.setValue(2, 1, 3);
        dataTable.setValue(3, 0, "Eat");
        dataTable.setValue(3, 1, 2);
        dataTable.setValue(4, 0, "Commute");
        dataTable.setValue(4, 1, 1);

        // Set options
        PieChartOptions options = PieChartOptions.create();
        options.setBackgroundColor("#f0f0f0");

        // options.setColors(colors);
        options.setFontName("Tahoma");
        options.setIs3D(false);
        options.setPieResidueSliceColor("#000000");
        options.setPieResidueSliceLabel("Others");
        options.setSliceVisibilityThreshold(0.1);
        options.setTitle("So, how was your day?");

        // Draw the chart
        chart.draw(dataTable, options);
        chart.addReadyHandler(new ReadyHandler() {
            @Override
            public void onReady(ReadyEvent event) {
                chart.setSelection(Selection.create(1, null));
            }
        });
    }
}
