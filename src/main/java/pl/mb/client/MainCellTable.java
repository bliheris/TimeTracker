package pl.mb.client;


import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.ListDataProvider;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import pl.mb.shared.timeRecord.TimeRecord;

import java.util.Comparator;
import java.util.List;

public class MainCellTable extends CellTable {

    public static Long ID = 1L;
    private ListDataProvider<TimeRecord> dataProvider;
    //private Pagination pagination;
    //private SimplePager dataGridPager;

    public MainCellTable() {
        super(50);
        setBordered(false);
        setWidth("100%", true);
        dataProvider = new ListDataProvider<TimeRecord>();

        //pagination = new Pagination();
        //dataGridPager = new SimplePager();

        TextColumn<TimeRecord> dateColumn = new TextColumn<TimeRecord>() {
            @Override
            public String getValue(final TimeRecord tr) {
                return DateTimeFormat.getFormat("MM-dd").format(tr.getDate());
            }
        };
        TextColumn<TimeRecord> timeColumn = new TextColumn<TimeRecord>() {
            @Override
            public String getValue(final TimeRecord tr) {
                return tr.getTime();
            }
        };
        TextColumn<TimeRecord> projectColumn = new TextColumn<TimeRecord>() {
            @Override
            public String getValue(final TimeRecord tr) {
                return tr.getProject().getName();
            }
        };
        projectColumn.setSortable(true);
        TextColumn<TimeRecord> descriptionColumn = new TextColumn<TimeRecord>() {
            @Override
            public String getValue(final TimeRecord tr) {
                return tr.getDescription();
            }
        };


        ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER, IconType.TRASH);
        final Column<TimeRecord, String> deleteColumn = new Column<TimeRecord, String>(
                deleteCell) {
            @Override
            public String getValue(TimeRecord object) {
                return "Delete";
            }
        };
        deleteColumn.setFieldUpdater(new FieldUpdater<TimeRecord, String>() {
            @Override
            public void update(int index, TimeRecord object, String value) {
                dataProvider.getList().remove(object);
            }
        });

        addCol(dateColumn, "Date", "70px");
        addCol(timeColumn, "Time", "70px");
        addCol(projectColumn, "Project", "100px");
        addColumn(descriptionColumn, "Description");
        addCol(deleteColumn, "", "100px");

        //dataGridPager.setDisplay(this);
        //pagination.clear();
        dataProvider.addDataDisplay(this);

        List<TimeRecord> list = dataProvider.getList();

        ColumnSortEvent.ListHandler<TimeRecord> columnSortHandler =
                new ColumnSortEvent.ListHandler<TimeRecord>(list);
        columnSortHandler.setComparator(projectColumn,
                new Comparator<TimeRecord>() {
                    public int compare(TimeRecord o1, TimeRecord o2) {
                        if (o1 == o2) {
                            return 0;
                        }

                        if (o1 != null) {
                            return (o2 != null) ?
                                    o1.getProject().getName().compareTo(o2.getProject().getName()) : 1;
                        }
                        return -1;
                    }
                });
        addColumnSortHandler(columnSortHandler);
        // We know that the data is sorted alphabetically by default.
        getColumnSortList().push(projectColumn);

        //pagination.rebuild(dataGridPager);
    }

    private void addCol(Column<TimeRecord, String> col, String name, String width){
        addColumn(col, name);
        setColumnWidth(col, width);
    }

    public void addRecord(TimeRecord timeRecord){
        dataProvider.getList().add(0, timeRecord);
    }

    /*public Pagination getPager(){
        return pagination;
    }*/
}
