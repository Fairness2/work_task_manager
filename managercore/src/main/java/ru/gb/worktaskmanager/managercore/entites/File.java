package ru.gb.worktaskmanager.managercore.entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Статусы задания
 */
@Data
@Entity
@Table(name = "t_file")
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    /*@ManyToOne
    @JoinColumn(name = "comment_ id", nullable = false)
    private Comment comment;*/

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @NaturalId
    @Column(name = "file_id") //ID фалйа в медиасервисе
    private String fileId;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Сделаем ссылку на сервис файлов
     * @return String
     */
    public String getLink() {
        //TODO взять из конфига
        String serviceLink = "http://localhost:8945/file_manager";
        String fileUrl = "/files";
        return new StringBuilder(serviceLink)
                .append(fileUrl)
                .append("/")
                .append(this.fileId)
                .toString();
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id);
    }
}
