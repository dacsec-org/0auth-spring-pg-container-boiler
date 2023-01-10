package org.dacsec.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

/**
 * {@link LogoutHandler} is needed to perform SSO logout with Auth0. By
 * default, Spring will clear the SecurityContext and the session.
 * This controller will also log users out of Auth0 by calling the Auth0 logout endpoint.
 */
@Controller
public class LogoutHandler extends SecurityContextLogoutHandler {
    
    private final ClientRegistrationRepository clientRegistrationRepository;
    
    /**
     * {@link LogoutHandler} constructor so that we can look up information
     * about the client registration.
     * @param clientRegistrationRepository the client registration repository
     * {@link ClientRegistrationRepository} is a Spring interface that provides access to the
     * client registration information.
     */
    @Autowired
    public LogoutHandler(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    
    /**
     * {@link LogoutHandler#logout(HttpServletRequest, HttpServletResponse, Authentication)} delegates to
     * {@linkplain SecurityContextLogoutHandler} to log the user
     * out of the application, and then logs the user out of Auth0.
     * @param httpServletRequest the request.
     * @param httpServletResponse the response.
     * @param authentication the current authentication.
     */
   public void logout(
     HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
     Authentication authentication) {
        super.logout(httpServletRequest, httpServletResponse, authentication);
        ClientRegistration clientRegistration =
          clientRegistrationRepository.findByRegistrationId("auth0");
        String logoutUrl = clientRegistration
                             .getProviderDetails()
                             .getConfigurationMetadata()
                             .get("end_session_endpoint").toString();
        httpServletResponse.setHeader("Location", logoutUrl);
        httpServletResponse.setStatus(302);
    }
    
    /**
     *
     * {@link ClientRegistration} Gets the Spring ClientRegistration, which
     * we use to get the registered client ID and issuer for building the
     * {@code returnTo} query parameter when calling the Auth0 logout API.
     * @return the {@code ClientRegistration} for this application.
     */
    private ClientRegistration getClientRegistration() {
        return this.clientRegistrationRepository.findByRegistrationId("auth0");
    }
}
