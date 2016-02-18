package pl.mb.client.projects;

import com.google.gwt.user.client.ui.HasWidgets;
import org.gwtbootstrap3.client.ui.ListGroup;
import org.gwtbootstrap3.client.ui.ListGroupItem;
import org.gwtbootstrap3.client.ui.Row;
import pl.mb.shared.project.Project;

import java.util.List;

class ProjectsView extends Row implements ProjectsPresenter.Display {

    private ListGroup lg;

    public ProjectsView(){
        lg = new ListGroup();
        add(lg);
    }

    private ListGroupItem createItem(String text){
        ListGroupItem item = new ListGroupItem();
        item.setText(text);
        return item;
    }

    @Override
    public void showIn(HasWidgets container) {
        container.add(this);
    }

    @Override
    public void setProjects(List<Project> projects) {
        lg.clear();
        for (Project project : projects) {
            lg.add(createItem(project.getName()));
        }
    }
}
