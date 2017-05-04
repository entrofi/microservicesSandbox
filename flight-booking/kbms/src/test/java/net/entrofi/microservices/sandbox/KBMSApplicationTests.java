package net.entrofi.microservices.sandbox;

import net.entrofi.microservices.sandbox.model.Greeting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KBMSApplicationTests {

    private static final String HOST = "http://localhost:8080";

	@Test
	public void contextLoads() {
	}

    @Test
	public void testSecureService() {
        final String credentials = "user:password";
        final String encodedCredentials = new String(Base64.encode((credentials.getBytes())));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Basic" + encodedCredentials );

        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Greeting> responseEntity = restTemplate.exchange(HOST, HttpMethod.GET, requestEntity, Greeting.class);
        assertEquals("Hello ", responseEntity.getBody().getGreetingMessage());
    }

    @Test
    public void testOAuthService() {
        /*
        security.oauth2.client.client-id=oauthclient
        security.oauth2.client.client-secret=oauthclientsecret
        security.oauth2.client.authorized-grant-types=authorization_code,refresh_token,password
        security.oauth2.client.scope=openid
         */
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setUsername("user");
        resource.setPassword("pass");
        resource.setAccessTokenUri(HOST + "/oauth/token");
        resource.setClientId("oauthclient");
        resource.setClientSecret("oauthclientsecret");
        resource.setGrantType("password");

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource, clientContext);

        Greeting greeting = oAuth2RestTemplate.getForObject(HOST, Greeting.class);

        assertEquals(Greeting.GREETING_BODY, greeting.getGreetingMessage() );
    }

}
