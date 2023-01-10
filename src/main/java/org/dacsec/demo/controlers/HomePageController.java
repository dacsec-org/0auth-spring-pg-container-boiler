package org.dacsec.demo.controlers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * {@link HomePageController} is a controller for the home page.
 */
@RestController
@RequestMapping("/")
public class HomePageController {
    
    /**
     * {@link HomePageController#home(Model, OidcUser)} show profile
     * information associated with the logged-in users
     *
     * @param model the model
     * @param principal the authentication principal
     * @return the home page index
     */
    @GetMapping
    public String home(Model model, @AuthenticationPrincipal OidcUser principal) {
        if (principal != null) {
            model.addAttribute("profile", principal.getClaims());
        }
        return "index";
    }
}
