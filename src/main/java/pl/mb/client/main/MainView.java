package pl.mb.client.main;

import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import pl.mb.client.commons.IMenuButton;
import pl.mb.client.commons.MenuButton;

public class MainView extends Container implements MainPresenter.Display {

    private Column mainColumn;
    private MenuButton logs;
    private MenuButton projects;
    private MenuButton reports;

    public MainView() {
        PageHeader pageHeader = new PageHeader();
        pageHeader.setText("Welcome to TimeTracker");
        pageHeader.setSubText("Time tracking application");


        Column menuColumn = new Column(ColumnSize.MD_2);
        mainColumn = new Column(ColumnSize.MD_10);

        logs = new MenuButton("Logs");
        projects = new MenuButton("Projects");
        reports = new MenuButton("Reports");

        LinkedGroup menu = new LinkedGroup();
        menu.add(logs);
        menu.add(projects);
        menu.add(reports);

        menuColumn.add(menu);

        Row firstRow = new Row();
        firstRow.add(menuColumn);
        firstRow.add(mainColumn);

        add(pageHeader);
        add(firstRow);
    }

    @Override
    public IMenuButton logs() {
        return logs;
    }

    @Override
    public IMenuButton projects() {
        return projects;
    }

    @Override
    public IMenuButton reports() {
        return reports;
    }

    @Override
    public Column mainColumn() {
        return mainColumn;
    }

    @Override
    public Widget asWidget() {
        return this;
    }
}
