package main.authentication;

import java.security.Principal;

public class AuthenticationController {

    public String currentUserName(Principal principal) {
        return principal.getName();
    }
	
}
