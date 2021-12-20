package EvgenUlianov.HomeTask_1_1.repositories;

import EvgenUlianov.HomeTask_1_1.UserManager.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByUsername(String username);
}
