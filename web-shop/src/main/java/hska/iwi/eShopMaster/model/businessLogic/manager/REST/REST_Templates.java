package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

import org.json.JSONObject;


@Entity // This tells Hibernate to make a table out of this class
public class REST_Templates{

	public String get_user_url(){
        return "http://zuulapplication:8791/user-service"; // THIS WORKS TOO!
        //return "http://172.18.0.7:8773/"; // THIS WORKS 
        /*
        //DOESN'T WORK LOAD BALANCER IS NULL ERROR
        ServiceInstance serviceInstance=loadBalancer.choose("core-service-user");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
        */
    }

    public OAuth2RestTemplate get_rest_template_user(){

        ClientCredentialsResourceDetails resourceDetailsUser  = new ClientCredentialsResourceDetails();
        resourceDetailsUser.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsUser.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsUser.setId("1");
        resourceDetailsUser.setTokenName("Core_User");
        //resourceDetailsUser.setAccessTokenUri("http://172.18.0.11:8300/oauth/token");
        resourceDetailsUser.setAccessTokenUri("http://oauthserver:8300/oauth/token");
        resourceDetailsUser.setClientId("coreUserId");
        resourceDetailsUser.setClientSecret("coreUserSecret");
        resourceDetailsUser.setGrantType("client_credentials");
        resourceDetailsUser.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrUser = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsUser, new DefaultOAuth2ClientContext(atrUser));
    }

	public String get_category_url(){
        return "http://zuulapplication:8791/category-service"; // THIS WORKS TOO!
        //return "http://172.18.0.7:8773/"; // THIS WORKS 
        /*
        //DOESN'T WORK LOAD BALANCER IS NULL ERROR
        ServiceInstance serviceInstance=loadBalancer.choose("core-service-user");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
        */
    }

    public OAuth2RestTemplate get_rest_template_category(){

        ClientCredentialsResourceDetails resourceDetailsCategory  = new ClientCredentialsResourceDetails();
        resourceDetailsCategory.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsCategory.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsCategory.setId("1");
        resourceDetailsCategory.setTokenName("Core_Category");
        resourceDetailsCategory.setAccessTokenUri("http://oauthserver:8300/oauth/token");
        resourceDetailsCategory.setClientId("coreCategoryId");
        resourceDetailsCategory.setClientSecret("coreCategorySecret");
        resourceDetailsCategory.setGrantType("client_credentials");
        resourceDetailsCategory.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrCategory = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsCategory, new DefaultOAuth2ClientContext(atrCategory));
    }
	public String get_product_url(){
        return "http://zuulapplication:8791/product-service"; // THIS WORKS TOO!
        //return "http://172.18.0.7:8773/"; // THIS WORKS 
        /*
        //DOESN'T WORK LOAD BALANCER IS NULL ERROR
        ServiceInstance serviceInstance=loadBalancer.choose("core-service-product");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
        */
    }

    public OAuth2RestTemplate get_rest_template_product(){

        ClientCredentialsResourceDetails resourceDetailsProduct  = new ClientCredentialsResourceDetails();
        resourceDetailsProduct.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsProduct.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsProduct.setId("1");
        resourceDetailsProduct.setTokenName("Core_Product");
        resourceDetailsProduct.setAccessTokenUri("http://oauthserver:8300/oauth/token");
        resourceDetailsProduct.setClientId("coreProductId");
        resourceDetailsProduct.setClientSecret("coreProductSecret");
        resourceDetailsProduct.setGrantType("client_credentials");
        resourceDetailsProduct.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrProduct = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsProduct, new DefaultOAuth2ClientContext(atrProduct));
    }
	public String get_composite_url(){
        return "http://zuulapplication:8791/composite"; // THIS WORKS TOO!
        //return "http://172.18.0.7:8773/"; // THIS WORKS 
        /*
        //DOESN'T WORK LOAD BALANCER IS NULL ERROR
        ServiceInstance serviceInstance=loadBalancer.choose("composite-service-product-category");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
        */
    }

    public OAuth2RestTemplate get_rest_template_composite(){

        ClientCredentialsResourceDetails resourceDetailsComposite  = new ClientCredentialsResourceDetails();
        resourceDetailsComposite.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsComposite.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsComposite.setId("1");
        resourceDetailsComposite.setTokenName("Composite");
        resourceDetailsComposite.setAccessTokenUri("http://oauthserver:8300/oauth/token");
        resourceDetailsComposite.setClientId("compositeId");
        resourceDetailsComposite.setClientSecret("compositeSecret");
        resourceDetailsComposite.setGrantType("client_credentials");
        resourceDetailsComposite.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrComposite = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsComposite, new DefaultOAuth2ClientContext(atrComposite));
    }

}
