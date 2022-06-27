package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialsForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final UserService userService;
    private final NotesService notesService;
    private final CredentialsService credentialsService;
    private final FileService fileService;
    private final EncryptionService encryptionService;

    public HomeController(UserService userService, NotesService notesService, CredentialsService credentialsService, FileService fileService, EncryptionService encryptionService) {
        this.userService = userService;
        this.notesService = notesService;
        this.credentialsService = credentialsService;
        this.fileService = fileService;
        this.encryptionService = encryptionService;
    }


    @GetMapping
    public String homeView(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, @ModelAttribute("credentialsForm") CredentialsForm credentialsForm, @ModelAttribute("fileForm") FileForm fileForm, Model model) {
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        model.addAttribute("encryptionService", encryptionService);

        model.addAttribute("notes", notesService.getNotes(userid));
        model.addAttribute("credentials", credentialsService.getCredentials(userid));
        model.addAttribute("files", fileService.getFiles(userid));
        return "home";
    }
}
