package de.hska.iwi.vislab.oauthserver.REST;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.test.OAuth2ContextConfiguration.ClientCredentials;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableOAuth2Client
public class ConsumeCoreUser {


    private static final Logger log = LoggerFactory.getLogger(ConsumeCoreUser.class);

    public OAuth2ProtectedResourceDetails oAuth2ResourceDetails() {
        //BaseOAuth2ProtectedResourceDetails details = new BaseOAuth2ProtectedResourceDetails();
        //ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
        ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
        
        details.setClientId("oauthId");
        details.setClientSecret("oauthSecret");
        details.setAccessTokenUri("http://oauthserver:8300/oauth/token");
        //details.setGrantType("client_credentials");
        List<String> scope = new ArrayList<>();
        scope.add("read");scope.add("write");
        details.setScope(scope);
        details.setAuthenticationScheme(AuthenticationScheme.header);
        details.setClientAuthenticationScheme(AuthenticationScheme.header);
        details.setId("1");
        details.setTokenName("oauth");

        return details;
    }
 
    @Bean // has to be done at runtime because the authorization server would not be up otherwise
    public OAuth2RestTemplate  foo() {;
        AccessTokenRequest atr = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(oAuth2ResourceDetails(), new DefaultOAuth2ClientContext(atr));
    }

    public User getUser(String input) {
        try {
            OAuth2RestTemplate restTemplate3 = foo();
            User user = restTemplate3.getForObject("http://coreuser:8101/user/" + input, User.class);
            log.info("GET User: " + user.getUsername());
            return user;
        } catch (Exception e) {
            log.info("GETTING user failed in ConsumeCoreUser!");
            e.printStackTrace();
            throw e;
        }
    }

}