package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import java.util.Arrays;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.REST_Templates;


public class UserManagerREST{
    @Autowired
    private LoadBalancerClient loadBalancer;



    //@Autowired
    //private OAuth2RestTemplate restTemplate;

    //ConsumeCoreUser rest = new ConsumeCoreUser();

    @Autowired
    private PasswordEncoder encoder;
    private REST_Templates rest_templates  = new REST_Templates();

    public Account getUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(rest_templates.get_user_url());
        try {
            OAuth2RestTemplate restTemplateUser = rest_templates.get_rest_template_user();
            Account user = restTemplateUser.getForObject(rest_templates.get_user_url() + "/user"+ "?username=" + username, Account.class);
            System.out.println("User in loadByUsername: " + user.getUsername());
            return user;

        } catch (Exception e) {
            System.out.println("GETTING user failed! in loadbyUsername");
            System.out.println(e);
        }

        return null;//User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();
    }

    /*
    public Account deleteObject(Account user) throws UsernameNotFoundException {
        try {
            OAuth2RestTemplate restTemplateUser = get_rest_template_user();
            Account user = restTemplateUser.getForObject(get_user_url() + "/user" + username, Account.class);
            System.out.println("User in loadByUsername: " + user.getUsername());
            return user;

        } catch (Exception e) {
            System.out.println("GETTING user failed! in loadbyUsername");
        }

        return null;//User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();
    }
    */

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            OAuth2RestTemplate restTemplateUser = rest_templates.get_rest_template_user();
            Account user = restTemplateUser.getForObject(rest_templates.get_user_url() + "/user" + username, Account.class);

            String role = "USER";
            if (user.getPermission() == 0) {
                role = "ADMIN";
            }
            System.out.println("User in loadByUsername: " + user.getUsername());

            return User.withUsername(user.getUsername()).username(user.getUsername())
            .password(encoder.encode(user.getPassword())).roles(role).build();

        } catch (Exception e) {
            System.out.println("GETTING user failed! in loadbyUsername");
        }

        return User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();
    }


    public void registerUser(Account user){
        try {
            OAuth2RestTemplate restTemplateUser = rest_templates.get_rest_template_user();
            System.out.println("REGISTER USER-1");
            restTemplateUser.postForObject(rest_templates.get_user_url() + "/user", user, HttpStatus.class);
            System.out.println("REGISTER USER-2");
        } catch (Exception e) {
            System.out.println("REGISTER user failed!");
            System.out.println(e);
        }
    }
}

