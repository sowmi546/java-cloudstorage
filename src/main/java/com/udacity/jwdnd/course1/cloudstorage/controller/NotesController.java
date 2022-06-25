package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {
    private UserService userService;
    private NotesService notesService;

    public NotesController(UserService userService, NotesService notesService) {
        this.userService = userService;
        this.notesService = notesService;
    }

    /**
     * need to write the logic to get the notes based on user id, create and update the notes and delete it
     get --> @GetMapping("/view/fileId")
     get -->@GetMapping("/view/userid/notes")
     insert/update --> @PostMapping
     delete --> @Delete("/delete/noteid")
     */

//    @GetMapping
//    public String getHomePage(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm)
//    {
//        return "home";
//    }
//
    @GetMapping
    public String getHomeView(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Notes notes, Model model) {
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        model.addAttribute("notes", this.notesService.getNotes(userid));
        return "home";

    }
    @GetMapping("/{noteid}")
    public Notes getNote(Integer noteid){
        return notesService.getNotesByNoteId(noteid);

    }
//    //
////
    @GetMapping("/users/{userid}")
    public List<Notes> getNotesByUser(Integer userid){
        return notesService.getNotes(userid);
    }
//
//    /**
//     * for inserting a new note we need note title, note description and the user id details
//
//     */
//
    @PostMapping
    public String createAndUpdateNote(Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm, Notes notes, Model model){


        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        String notedescription = noteForm.getNotedescription();
        String notetitle = noteForm.getNotetitle();

        if(noteForm.getNoteid().isEmpty()){
            notesService.createNote(notetitle, notedescription,userid);
        }
        else {
            notesService.updateNotes(Integer.parseInt(noteForm.getNoteid()),notetitle, notedescription );


        }

        model.addAttribute("notes", notesService.getNotes(userid));
        model.addAttribute("result","success");
        return "result";
    }
//
//
//    //
//
    @GetMapping("/remove/{noteid}")
    public String deleteNote(Model model, Authentication authentication, @ModelAttribute("noteForm") NoteForm noteForm){
        try{
            //check whether logged in user is deleting the record or not
//            String username =  authentication.getName();
//            String loggedInUser = userService.getUser(username);
            String username = authentication.getName();
            int userid = userService.getUser(username).getUserId();

            notesService.deleteNote(Integer.parseInt(noteForm.getNoteid()));


            model.addAttribute("notes", this.notesService.getNotes(userid));
            model.addAttribute("result", "success");
        }
        catch(Exception e){
            model.addAttribute("result","failed");


        }

        return "result";

    }





}
