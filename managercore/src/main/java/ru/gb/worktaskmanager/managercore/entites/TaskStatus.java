package ru.gb.worktaskmanager.managercore.entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Справочник статусов заданий
 */
@Data
@Entity
@Table(name = "d_task_status")
@NoArgsConstructor
public class TaskStatus {
    @Id
    @NaturalId
    @Column(name = "code")
    private String code;
    @Column(name = "title")
    private String title;

    public TaskStatus(String code, String title) {
        this.code = code;
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatus that = (TaskStatus) o;
        return Objects.equals(code, that.code);
    }
}
