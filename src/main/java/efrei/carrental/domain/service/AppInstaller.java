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

    @Value("${app.customer.email}")
    private String supercustomerEmail;

    @Value("${app.customer.username}")
    private String supercustomerUsername;
    @Value("${app.customer.password}")
    private String supercustomerPassword;

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
        this.createSuperCustomer();
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

    private void createSuperCustomer(){
        if(applicationUserRepository.findByUsername(supercustomerUsername).isEmpty()) {
            ApplicationUser applicationUser = new ApplicationUser();
            applicationUser.setUserType(AppRole.CUSTOMER.name());
            applicationUser.setUsername(supercustomerUsername);
            applicationUser.setPassword(supercustomerPassword);
            applicationUser.setEmail(supercustomerEmail);
            applicationUserService.createUser(applicationUser);
        }
        logger.info("Created supercustomer {}", this.supercustomerEmail);
    }
}

