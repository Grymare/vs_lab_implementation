package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import java.util.Arrays;

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


public class UserManagerREST{
    @Autowired
    private LoadBalancerClient loadBalancer;

    private String get_user_url(){
        return "http://172.18.0.10:8791/user-service"; // THIS WORKS TOO!
        //return "http://172.18.0.7:8773/"; // THIS WORKS 
        /*
        ServiceInstance serviceInstance=loadBalancer.choose("core-service-user");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
        */
    }

    private OAuth2RestTemplate get_rest_template_user(){

        ClientCredentialsResourceDetails resourceDetailsUser  = new ClientCredentialsResourceDetails();
        resourceDetailsUser.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsUser.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsUser.setId("1");
        resourceDetailsUser.setTokenName("Core_User");
        resourceDetailsUser.setAccessTokenUri("http://172.18.0.11:8300/oauth/token");
        resourceDetailsUser.setClientId("coreUserId");
        resourceDetailsUser.setClientSecret("coreUserSecret");
        resourceDetailsUser.setGrantType("client_credentials");
        resourceDetailsUser.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrUser = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsUser, new DefaultOAuth2ClientContext(atrUser));
    }


    //@Autowired
    //private OAuth2RestTemplate restTemplate;

    //ConsumeCoreUser rest = new ConsumeCoreUser();

    @Autowired
    private PasswordEncoder encoder;

    public Account getUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(get_user_url());
        try {
            OAuth2RestTemplate restTemplateUser = get_rest_template_user();
            Account user = restTemplateUser.getForObject(get_user_url() + "/user"+ "?username=" + username, Account.class);
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
            OAuth2RestTemplate restTemplateUser = get_rest_template_user();
            Account user = restTemplateUser.getForObject(get_user_url() + "/user" + username, Account.class);

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
}

