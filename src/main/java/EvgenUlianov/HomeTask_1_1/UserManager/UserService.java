package EvgenUlianov.HomeTask_1_1.UserManager;

import EvgenUlianov.HomeTask_1_1.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final private PasswordEncoder passwordEncoder;

    public Iterable<User> Users(){
        return userRepository.findAll(Sort.by("Login"));
    }

    public User getUser(String name){
        return userRepository.getByUsername(name);
    }

    public void delete(Integer id){
        long longId = (long) id;
        userRepository.deleteById(longId);
    }

    public User add(String username, String password){
        if (checkName(username))
            return null;
        User user = new User(username);
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return user;
    }

    private boolean checkName (String username) {
        if (username.length() == 0) {
            String msg = "Не указано наименование";
            log.error(msg);
            return true;
        }
        User user = userRepository.getByUsername(username);
        if (user != null) {
            String msg = "такой пользователь уже есть";
            log.error(msg);
            return true;
        }
        return false;
    }

}
