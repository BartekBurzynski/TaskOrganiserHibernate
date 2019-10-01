 package pl.bb.taskorganiser.model;

import org.springframework.format.annotation.DateTimeFormat;
import pl.bb.taskorganiser.enums.TaskCategory;
import pl.bb.taskorganiser.enums.TaskDone;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    @Enumerated(EnumType.STRING)
    private TaskDone done;
    @Enumerated(EnumType.STRING)
    private TaskCategory category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public TaskDone getDone() {
        return done;
    }

    public void setDone(TaskDone done) {
        this.done = done;
    }

    public TaskCategory getCategory() {
        return category;
    }

    public void setCategory(TaskCategory category) {
        this.category = category;
    }

    public Task(String name, String description, LocalDate endTime, TaskDone done, TaskCategory category) {
        this.name = name;
        this.description = description;
        this.endTime = endTime;
        this.done = done;
        this.category = category;
    }

    public Task() {
    }

}
