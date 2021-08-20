package de.hska.iwi.vislab.oauthserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

//@RequireArgsConstructor
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("permitAll()");
        //.tokenKeyAccess("permitAll()")
                //.checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
        .withClient("coreProductId")
        .authorizedGrantTypes("client_credentials")
        .authorities("ROLE_USER", "ROLE_ADMIN")
        .scopes("read", "write")
        .autoApprove(true)
        .secret(encoder.encode("coreProductSecret"))
        .and()
        .withClient("coreCategoryId")
        .authorizedGrantTypes("client_credentials")
        .authorities("ROLE_USER", "ROLE_ADMIN")
        .scopes("read", "write")
        .autoApprove(true)
        .secret(encoder.encode("coreCategorySecret"))
        .and()
        .withClient("coreUserId")
        .authorizedGrantTypes("client_credentials")
        .authorities("ROLE_USER", "ROLE_ADMIN")
        .scopes("read", "write")
        .autoApprove(true)
        .secret(encoder.encode("coreUserSecret"))
        .and()
        .withClient("compositeId")
        .authorizedGrantTypes("client_credentials")
        .authorities("ROLE_CORE_PRODUCT", "ROLE_CORE_CATEGORY")
        .scopes("read", "write")
        .autoApprove(true)
        .secret(encoder.encode("compositeSecret"));
        
                /*
                .withClient("frontendId")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "client_credentials")
                .authorities("ROLE_USER", "ROLE_ADMIN")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("frontendSecret"))
                //TODO: einfügen der anderen Services
                
                .and()
                .withClient("coreUserId")
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("coreUserSecret"))
                .and()
                .withClient("coreRoleId")
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("coreRoleSecret"))
                .and()
                .withClient("coreProductId")
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("coreProductSecret"))
                .and()
                .withClient("coreCategoryId")
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("coreCategorySecret"))
                .and()
                .withClient("compUserRoleId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_USER", "ROLE_CORE_ROLE")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("compUserRoleSecret"))
                .and()
                .withClient("compProductCategoryId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_PRODUCT", "ROLE_CORE_CATEGORY")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("compProductCategorySecret"))
                .and()
                .withClient("apiUserId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_USER", "ROLE_COMP_USER_ROLE")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("apiUserSecret"))
                .and()
                .withClient("apiRoleId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_Role")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("apiRoleSecret"))
                .and()
                .withClient("apiProductId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_PRODUCT","ROLE_COMP_PRODUCT_CATEGORY")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("apiProductSecret"))
                .and()
                .withClient("apiCategoryId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_CATEGORY","ROLE_COMP_PRODUCT_CATEGORY")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("apiCategorySecret"))
                .and()
                .withClient("oauthId")
                .authorizedGrantTypes("client_credentials")
                .authorities("ROLE_CORE_USER")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("oauthSecret"));
                */


                /*
                .and()
                .withClient("zuulId")
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("zuulSecret"))
                .and()
                .withClient("oauthId")
                .authorizedGrantTypes("client_credentials")
                .scopes("read", "write")
                .autoApprove(true)
                .secret(encoder.encode("oauthSecret")); */
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore);
                //.userDetailsService(userDetailsService);
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}