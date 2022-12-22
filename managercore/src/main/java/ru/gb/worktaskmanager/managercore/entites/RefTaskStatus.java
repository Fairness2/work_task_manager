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
import java.util.Objects;

/**
 * Статусы задания
 */
@Data
@Entity
@Table(name = "ref_task_status")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RefTaskStatus {
    /**
     * Стандартный формат даты
     */
    private static final String dateFormat = "yyyy-MM-dd kk:mm";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "status_code", nullable = false)
    private TaskStatus status;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    @Column(name = "user_id")
    private Long userId;

    /**
     * Преобразователь даты в строку
     * @param date
     * @return String
     */
    private String dateToStr(LocalDateTime date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(RefTaskStatus.dateFormat);
        return date != null ? dateFormat.format(date) : null;
    }

    /**
     * Получим строковое представление даты создания
     * @return String
     */
    public String getStrCreatedAt() {
        return dateToStr(this.createdAt);
    }

    /**
     * Получим строковое представление даты создания
     * @return String
     */
    public String getStrEndedAt() {
        return dateToStr(this.endedAt);
    }

    /**
     * Получим данные автора из сервиса пользователей
     * @return UserDto
     */
    public UserDto getUser() {
        //TODO связь с сервисом пользователя и получение данных из него
        return UserDto.builder()
                .id(this.userId)
                .build();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefTaskStatus that = (RefTaskStatus) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "}";
    }
}
