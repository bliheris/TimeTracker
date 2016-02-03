package pl.mb.client;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.FormType;
import org.gwtbootstrap3.extras.datepicker.client.ui.DatePicker;

import java.util.Arrays;
import java.util.Date;

public class CustomForm extends Form {

    private DatePicker datePicker;
    private TextBox time;
    private SuggestBox project;
    private TextBox description;
    private Button logButton;

    public CustomForm() {
        setType(FormType.INLINE);

        datePicker = new DatePicker();
        datePicker.setPlaceholder("DATE");
        datePicker.setAutoClose(true);
        datePicker.setHighlightToday(true);
        datePicker.setWidth("120px");

        time = new TextBox();
        time.setPlaceholder("TIME");
        time.setWidth("100px");

        MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
        oracle.addAll(Arrays.asList("Solin", "Test1", "Nowe", "Test2"));
        project = new SuggestBox(oracle);
        project.setPlaceholder("PROJECT");

        description = new TextBox();
        description.setPlaceholder("DESCRIPTION");
        description.setWidth("450px");

        logButton = new Button("Log it!");

        FieldSet fieldSet = new FieldSet();
        fieldSet.add(inFormGroup(datePicker));
        fieldSet.add(inFormGroup(time));
        fieldSet.add(inFormGroup(project));
        fieldSet.add(inFormGroup(description));
        fieldSet.add(inFormGroup(logButton));
        add(fieldSet);
    }

    public FormGroup inFormGroup(Widget w){
        FormGroup fg = new FormGroup();
        fg.add(w);
        return fg;
    }

    public void addLogHandler(ClickHandler clickHandler) {
        logButton.addClickHandler(clickHandler);
    }

    public Date datePickerValue() {
        return datePicker.getValue();
    }

    public String timeValue(){
        return time.getValue();
    }

    public String descriptionValue(){
        return description.getValue();
    }

    public String projectValue(){
        return project.getValue();
    }

    public void clearForm() {
        time.clear();
        project.setText("");
        description.clear();
    }
}
