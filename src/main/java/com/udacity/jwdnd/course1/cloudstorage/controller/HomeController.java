package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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

    public HomeController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }


    @GetMapping
    public String homeView(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Model model) {
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        model.addAttribute("notes",notesService.getNotes(userid));

        return "home";
    }
}
