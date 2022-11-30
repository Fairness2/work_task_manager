package ru.gb.worktaskmanager.managercore.entites;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Статусы задания
 */
@Data
@Entity
@Table(name = "ref_task_status")
@NoArgsConstructor
public class RefTaskStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne
    @JoinColumn(name = "status_code", nullable = false)
    private TaskStatus status;


}
