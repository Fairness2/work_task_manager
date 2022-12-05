package ru.gb.worktaskmanager.managercore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.worktaskmanager.managercore.entites.TaskStatus;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer> {

}
