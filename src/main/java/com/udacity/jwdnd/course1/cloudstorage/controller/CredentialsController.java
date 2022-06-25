package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {

    private CredentialsService credentialsService;
    private UserService userService;

    public CredentialsController(CredentialsService credentialsService, UserService userService) {
        this.credentialsService = credentialsService;
        this.userService = userService;
    }

    /**
     * need to write the logic to get the credentials based on user id, create and update the credentials and delete it
     get --> @GetMapping("/view/userid")
     insert/update --> @PostMapping
     delete --> @Delete("/credentialId")
     */

    @GetMapping
    public String getCredentialView(){
        return "home";
    }

}
