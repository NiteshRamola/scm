package in.niteshramola.scm.config;

import in.niteshramola.scm.entities.Providers;
import in.niteshramola.scm.entities.User;
import in.niteshramola.scm.helpers.AppConstants;
import in.niteshramola.scm.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("OAuth Authentication success");

        var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

        String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

        var oauth2User = (DefaultOAuth2User) authentication.getPrincipal();

        oauth2User.getAttributes().forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEnabled(true);
        user.setEmailVerified(true);
        user.setPassword("password");

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")) {

            user.setName(oauth2User.getAttribute("name"));
            user.setEmail(oauth2User.getAttribute("email"));
            user.setProfilePic(oauth2User.getAttribute("picture"));
            user.setAbout("User created using Google OAuth");
            user.setProvider(Providers.GOOGLE);
            user.setProviderId(oauth2User.getName());
        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

            user.setName(oauth2User.getAttribute("name"));
            user.setEmail(oauth2User.getAttribute("email"));
            user.setProfilePic(oauth2User.getAttribute("avatar_url"));
            user.setAbout("User created using Github OAuth");
            user.setProvider(Providers.GITHUB);
        } else {
            logger.info("Unknown OAuth provider");
        }

        User existingUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if (existingUser == null) {
            userRepository.save(user);
            logger.info("User created successfully" + user.getEmail());
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");

    }
}
