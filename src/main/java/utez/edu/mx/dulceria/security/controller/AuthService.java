package utez.edu.mx.dulceria.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utez.edu.mx.dulceria.security.model.AuthUser;
import utez.edu.mx.dulceria.user.model.User;
import utez.edu.mx.dulceria.user.service.UserService;

@Service
@Transactional
public class AuthService  implements UserDetailsService {
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userService.getByUsername(username).get();
        return AuthUser.build(user);
    }
}