package EvgenUlianov.HomeTask_1_1.repositories;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface TaskRepository extends JpaRepository<TaskDescription, Long> {

    List<TaskDescription> findByName(String name);
}
