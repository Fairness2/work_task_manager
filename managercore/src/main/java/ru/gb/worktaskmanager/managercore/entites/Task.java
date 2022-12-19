package ru.gb.worktaskmanager.managercore.entites;

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
public class Task {
    /**
     * Стандартный формат даты
     */
    private static final String dateFormat = "yyyy-MM-dd kk:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "employer_id")
    private Long employerId;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "responsible_user_id")
    private Long responsibleUserId;

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
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<RefTaskStatus> taskStatuses;

    @OneToMany(mappedBy = "task")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<File> files;

    /**
     * Получим данные работника из сервиса пользователей
     * @return UserDto
     */
    public UserDto getEmployer() {
        //TODO связь с сервисом пользователя и получение данных из него
        return UserDto.builder()
                .id(this.employerId)
                .build();
    }

    /**
     * Получим данные автора из сервиса пользователей
     * @return UserDto
     */
    public UserDto getAuthor() {
        //TODO связь с сервисом пользователя и получение данных из него
        return UserDto.builder()
                .id(this.authorId)
                .build();
    }

    /**
     * Получим данные ответственного на данный момент пользователя из сервиса пользователей
     * @return UserDto
     */
    public UserDto getResponsibleUser() {
        //TODO связь с сервисом пользователя и получение данных из него
        return UserDto.builder()
                .id(this.responsibleUserId)
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
