package efrei.carrental.domain.service;

import efrei.carrental.external.entity.ApplicationUser;
import efrei.carrental.external.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    ApplicationUserRepository applicationUserService;
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser u = applicationUserService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        User user = createUser(u);

        return user;
    }

    private User createUser(ApplicationUser u) {
        return new User(u.getUsername(), u.getPassword(), createAuthorities(u));
    }

    private Collection<GrantedAuthority> createAuthorities(ApplicationUser u) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+u.getUserType().toUpperCase()));
        return  authorities;
    }
}
