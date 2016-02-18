package pl.mb.client.projects;

import com.google.gwt.user.client.ui.HasWidgets;
import pl.mb.client.mvp.Presenter;
import pl.mb.shared.project.Project;
import pl.mb.shared.project.ProjectRepository;

import java.util.List;

public class ProjectsPresenter implements Presenter {

    interface Display {
        void showIn(HasWidgets container);
        void setProjects(List<Project> projects);
    }

    private Display display;
    private ProjectRepository repo;

    public ProjectsPresenter(ProjectRepository projectRepository) {
        display = new ProjectsView();
        repo = projectRepository;
    }

    @Override
    public void go(HasWidgets container) {
        display.showIn(container);
        display.setProjects(repo.getAllProjects());
    }

}
