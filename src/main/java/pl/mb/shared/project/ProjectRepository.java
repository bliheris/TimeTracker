package pl.mb.shared.project;

import java.util.HashMap;
import java.util.Map;

public class ProjectRepository {

    private Map<ProjectId, Project> repo = new HashMap<>();
    private Long idSequence = 1L;

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
}
