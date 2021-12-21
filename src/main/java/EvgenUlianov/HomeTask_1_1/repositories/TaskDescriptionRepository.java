package EvgenUlianov.HomeTask_1_1.repositories;

import EvgenUlianov.HomeTask_1_1.TaskManager.TaskDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface TaskDescriptionRepository extends JpaRepository<TaskDescription, Long> {

    @Query(value = "FROM TaskDescription t WHERE t.owner.id = ?1 ORDER BY t.id")
    Collection<TaskDescription> findOwn(long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM TaskDescription t WHERE t.id = ?1 AND t.owner.id = ?2")
    int deleteOwnById(long id, long userId);

    @Query(value = "FROM TaskDescription t WHERE t.id = ?1 AND t.owner.id = ?2")
    Optional<TaskDescription> findOwnById(long id, long userId);

}
