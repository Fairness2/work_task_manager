package ru.gb.worktaskmanager.managercore.entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import ru.gb.worktaskmanager.managercore.dtos.UserDto;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Справочник статусов заданий
 */
@Data
@Entity
@Table(name = "t_task")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    /**
     * Стандартный формат даты
     */
    private static final String dateFormat = "yyyy-MM-ddTkk:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "employer_id")
    private Users employerId;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Users authorId;

    @ManyToOne
    @JoinColumn(name = "responsible_user_id")
    private Users responsibleUserId;

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

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RefTaskStatus> taskStatuses;

    /*@OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> files;*/

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    /**
     * @return UserDto
     */
    public UserDto getEmployer() {
        return UserDto.builder()
                .id(this.employerId.getId())
                .name(this.employerId.getName())
                .surname(this.employerId.getSurname())
                .patronymic(this.employerId.getPatronymic())
                .username(this.employerId.getUsername())
                .build();
    }

    /**
     * @return UserDto
     */
    public UserDto getAuthor() {
        return UserDto.builder()
                .id(this.authorId.getId())
                .name(this.authorId.getName())
                .surname(this.authorId.getSurname())
                .patronymic(this.authorId.getPatronymic())
                .username(this.authorId.getUsername())
                .build();
    }

    /**
     * @return UserDto
     */
    public UserDto getResponsibleUser() {
        return UserDto.builder()
                .id(this.responsibleUserId.getId())
                .name(this.responsibleUserId.getName())
                .surname(this.responsibleUserId.getSurname())
                .patronymic(this.responsibleUserId.getPatronymic())
                .username(this.responsibleUserId.getUsername())
                .build();
    }

    /**
     * Преобразователь даты в строку
     * @param date
     * @return String
     */
    private String dateToStr(LocalDateTime date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Task.dateFormat);
        return date != null ? dateFormat.format(date) : null;
    }

    /**
     * Получим строковое представление плановой даты начала
     * @return String
     */
    public String getStrPlanStartDate() {
        return dateToStr(this.planStartDate);
    }

    /**
     * Получим строковое представление плановой даты окончания
     * @return String
     */
    public String getStrPlanEndDate() {
        return dateToStr(this.planEndDate);
    }

    /**
     * Получим строковое представление даты создания
     * @return String
     */
    public String getStrCreatedAt() {
        return dateToStr(this.createdAt);
    }

    /**
     * Получим строковое представление даты обновления
     * @return String
     */
    public String getStrUpdatedAt() {
        return dateToStr(this.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "\"title\": \"" + title + "\"," +
                "\"description\": \"" + description + "\"," +
                "\"description\": \"" + description + "\"" +
                "}";
    }
}
