package EvgenUlianov.HomeTask_1_1.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder extends BCryptPasswordEncoder implements PasswordEncoder {
    public CustomPasswordEncoder(){
        super();
    }
}
