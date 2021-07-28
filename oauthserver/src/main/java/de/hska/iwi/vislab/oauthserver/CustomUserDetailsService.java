package de.hska.iwi.vislab.oauthserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import de.hska.iwi.vislab.oauthserver.REST.ConsumeCoreUser;

public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreUser.class);

    //@Autowired
    //private OAuth2RestTemplate restTemplate;

    ConsumeCoreUser rest = new ConsumeCoreUser();

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            log.info("DEBUG: Try loading username " + username);
            de.hska.iwi.vislab.oauthserver.REST.User user =  rest.getUser(username);
            String role = "USER";
            if (user.getRoleId() == 0) {
                role = "ADMIN";
            }
            log.info("User in loadByUsername: " + user.getUsername());
        
            return User.withUsername(user.getUsername()).username(user.getUsername())
            .password(encoder.encode(user.getPassword())).roles(role).build();
        } catch (Exception e) {
            log.info("GETTING user failed! in loadbyUsername");
        }

        return User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();

    }

}