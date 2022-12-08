package ru.gb.worktaskmanager.managercore.entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Справочник статусов заданий
 */
@Data
@Entity
@Table(name = "d_task_status")
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "employer_id")
    private String employerId;

    @Column(name = "author_id")
    private String authorId;

    @Column(name = "responsible_user_id")
    private String responsibleUserId;

    @Column(name = "working_hours")
    private Integer workingHours;

    @Column(name = "plan_start_date")
    private LocalDateTime planStartDate;

    @Column(name = "plan_end_date")
    private LocalDateTime planEndDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "task")
    private List<RefTaskStatus> taskStatuses;

    @OneToMany(mappedBy = "task")
    private List<File> files;


}