package pl.mb.shared.timeRecord;


import pl.mb.shared.project.Project;

import java.util.Date;

public class TimeRecord {

    private TimeRecordId id;
    private Date date;
    private String time;
    private Project project;
    private String description;

    public TimeRecord(
            TimeRecordId id, Date date, String time,
            Project project, String description) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.project = project;
        this.description = description;
    }

    public TimeRecordId getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Project getProject() {
        return project;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeRecord that = (TimeRecord) o;

        return id.equals(that.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
