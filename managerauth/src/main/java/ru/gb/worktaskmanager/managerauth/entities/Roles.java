package ru.gb.worktaskmanager.managerauth.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@ToString
@NoArgsConstructor
@Table(name = "roles")
public class Roles {
    @Id
    @NaturalId
    @Column(name = "code")
    private String code;

    @Column(name = "title")
    private String title;

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles that = (Roles) o;
        return Objects.equals(code, that.code);
    }
}