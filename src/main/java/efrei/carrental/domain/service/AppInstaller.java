package efrei.carrental.domain.service;

import efrei.carrental.application.security.AppRole;
import efrei.carrental.external.entity.ApplicationUser;
import efrei.carrental.external.repository.ApplicationUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AppInstaller implements ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${app.superadmin.email}")
    private String superadminEmail;

    @Value("${app.superadmin.username}")
    private String superadminUsername;
    @Value("${app.superadmin.password}")
    private String superadminPassword;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Fonction appelée quand l'application démarre
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createSuperadmin();
    }

    /**
     * Permet la création d'un superadmin s'il n'existe pas
     */
    private void createSuperadmin() {
        if(applicationUserRepository.findByUsername(superadminUsername).isEmpty()) {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setUserType(AppRole.AGENT.name());
            applicationUser.setUsername(superadminUsername);
            applicationUser.setPassword(superadminPassword);
            applicationUser.setEmail(superadminEmail);
            applicationUserService.createUser(applicationUser);
        }
        logger.info("Created superadmin {}", this.superadminEmail);
    }
}

