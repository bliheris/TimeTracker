package pl.mb.shared.project;

public class Project {

    private ProjectId id;
    private String name;

    public Project(ProjectId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
