package pl.mb.shared.project;

import java.util.*;

public class ProjectRepository {

    private Map<ProjectId, Project> repo = new HashMap<>();
    private Long idSequence = 1L;
    private List<String> g;

    public Project create(String name){
        ProjectId id = new ProjectId(idSequence++);
        Project p = new Project(id, name);
        repo.put(id, p);
        return p;
    }

    public Project load(ProjectId id){
        return repo.get(id);
    }

    public Project loadOrCreate(String name){
        Project p = findByName(name);
        if(p == null){
            p = create(name);
        }
        return p;
    }

    private Project findByName(String name) {
        Project result = null;
        for (ProjectId projectId : repo.keySet()) {
            Project p = repo.get(projectId);
            if(p.getName().equals(name)){
                result = p;
                break;
            }
        }
        return result;
    }

    public List<Project> getAllProjects(){
        List<Project> list = new ArrayList<Project>(repo.values());
        Collections.sort(list, new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return list;
    }

    public List<String> getAllProjectsNames() {
        List<Project> projects = getAllProjects();
        List<String> result = new ArrayList<>();
        for (Project project : projects) {
            result.add(project.getName());
        }
        return result;
    }
}
