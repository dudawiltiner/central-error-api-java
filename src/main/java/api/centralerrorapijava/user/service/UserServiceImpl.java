package api.centralerrorapijava.user.service;

import api.centralerrorapijava.user.model.User;
import api.centralerrorapijava.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return this.userRepository.findByLogin(login);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
