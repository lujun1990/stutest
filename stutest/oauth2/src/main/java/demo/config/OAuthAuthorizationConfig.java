package demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class OAuthAuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    public static final String OAUTH_CLIENT_ID = "oauth_client";
    public static final String OAUTH_CLIENT_SECRET = "oauth_client_secret";
    public static final String RESOURCE_ID = "my_resource_id";
    public static final String[] SCOPES = { "read", "write" };

    /*.redirectUris("http://default-oauth-callback.com")*/
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(OAUTH_CLIENT_ID)
                .secret(OAUTH_CLIENT_SECRET)
                .resourceIds(RESOURCE_ID)
                .scopes(SCOPES)
                .authorities("ROLE_USER")
                .authorizedGrantTypes("authorization_code", "refresh_token","password","implicit","client_credentials")
                .accessTokenValiditySeconds(60*30) // 30min
                .refreshTokenValiditySeconds(60*60*24); // 24h
    }
}
