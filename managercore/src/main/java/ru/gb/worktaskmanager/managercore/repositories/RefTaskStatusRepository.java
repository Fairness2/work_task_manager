package ru.gb.worktaskmanager.managercore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.worktaskmanager.managercore.entites.RefTaskStatus;
import ru.gb.worktaskmanager.managercore.entites.Task;

import java.util.Optional;

@Repository
public interface RefTaskStatusRepository extends JpaRepository<RefTaskStatus, Integer> {
    Optional<RefTaskStatus> findByTaskAndEndedAtIsNull(Task task);
}
