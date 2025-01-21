package in.niteshramola.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {
        if(authentication instanceof OAuth2AuthenticationToken){
            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oAuth2User = oAuth2AuthenticationToken.getPrincipal();
            String username = "";

            if(clientId.equalsIgnoreCase("google")){
                System.out.println("Logging in with Google");
                username = oAuth2User.getAttribute("email");
            }else if(clientId.equalsIgnoreCase("github")){
                System.out.println("Logging in with Github");
                username = oAuth2User.getAttribute("email") != null
                        ? oAuth2User.getAttribute("email")
                        : oAuth2User.getAttribute("login") + "@gmail.com";
            }

            return username;
        }

        System.out.println("Logged in as normal without OAuth user");
        return authentication.getName();
    }
}
