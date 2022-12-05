package ru.gb.worktaskmanager.managercore.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Справочник статусов заданий
 */
@Data
@Entity
@Table(name = "d_task_status")
@NoArgsConstructor
public class TaskStatus {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "title")
    private String title;

    public TaskStatus(String code, String title) {
        this.code = code;
        this.title = title;
    }
}
