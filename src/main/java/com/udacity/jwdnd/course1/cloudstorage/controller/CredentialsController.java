package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialsService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String getCredentialView(Authentication authentication, @ModelAttribute("credentials") Credentials credentials, @ModelAttribute("noteForm") NoteForm noteForm, @ModelAttribute("files") Files files, Model model){
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        model.addAttribute("credentials", this.credentialsService.getCredentials(userid));
        return "home";
    }

    @PostMapping
    public String createAndUpdateCredential(Authentication authentication, @ModelAttribute("credentialsForm") CredentialsForm credentialsForm, Model model){
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        String url = credentialsForm.getUrl();
        String usernameCred = credentialsForm.getUsername();
        String password =  credentialsForm.getPassword();
        if(credentialsForm.getCredentialId().isEmpty()){
            credentialsService.insertCredentials(new Credentials(null, url, usernameCred, credentialsForm.getKey(), password, userid));
        }
        else{
            credentialsService.updateCredentials(new Credentials(Integer.parseInt(credentialsForm.getCredentialId()),url, usernameCred, credentialsForm.getKey(), password, userid));
        }
        model.addAttribute("credentials", credentialsService.getCredentials(userid));
        model.addAttribute("result","success");
        return "result";

    }

    @GetMapping("/remove/{credentialId}")
    public String deleteCredential(Authentication authentication, @ModelAttribute("credentialsForm") CredentialsForm credentialsForm, Model model){
        try{

            String username = authentication.getName();
            int userid = userService.getUser(username).getUserId();

            credentialsService.deleteCredentials(Integer.parseInt(credentialsForm.getCredentialId()));


            model.addAttribute("credentials", this.credentialsService.getCredentials(userid));
            model.addAttribute("result", "deletion-success");
        }
        catch(Exception e){
            model.addAttribute("result","failed");


        }

        return "result";
    }



}
