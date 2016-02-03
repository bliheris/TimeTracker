package pl.mb.shared.timeRecord;

import pl.mb.shared.project.Project;
import pl.mb.shared.project.ProjectId;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeRecordRepository {

    private Map<TimeRecordId, TimeRecord> repo = new HashMap<>();
    private Long idSequence = 1L;

    public TimeRecord create(Date date, String time,
                             Project project, String description){
        TimeRecordId id = new TimeRecordId(idSequence++);
        TimeRecord tr = new TimeRecord(id, date, time, project, description);
        repo.put(id, tr);
        return tr;
    }

    public TimeRecord load(ProjectId id){
        return repo.get(id);
    }
}
