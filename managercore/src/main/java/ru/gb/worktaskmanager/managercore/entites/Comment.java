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
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Справочник комментариев
 */
@Data
@Entity
@Table(name = "t_comment")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "text")
    private String text;

    @Column(name = "author_id")
    private Long authorId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne()
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @ManyToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "type_code", nullable = false)
    private CommentType type;

    @Column(name = "result_at")
    private LocalDateTime resultAt;

    @Column(name = "action_code")
    private ActionEnum action;


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

    public Long getTaskId() {
        //TODO понять запрашивается ли весь task для этого. Нужно получить только ID, которое является значение join колонки
        return this.task.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment task = (Comment) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id + "," +
                "\"text\": \"" + text + "\"," +
                "\"authorId\": \"" + authorId + "\"" +
                "}";
    }
}
