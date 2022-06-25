package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.google.common.net.HttpHeaders;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/file")
public class FileController {
    private UserService userService;
    private FileService fileService;

    public FileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    /**
     * need to write the logic to get the files based on user id, insert the file and delete the file
     get --> @GetMapping("/view/fileId")
     get -->@GetMapping("/view/userid/files")
     insert --> @PostMapping
     delete --> @Delete("/delete/fileId")
     */

    @GetMapping

    public String getFileView(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm, Model model){
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();
        model.addAttribute("files", this.fileService.getFiles(userid));
        return "home";
    }

    @GetMapping("/view/{filename}")


    public ResponseEntity getFileByName(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm, Model model, HttpServletResponse httpServletResponse, @PathVariable String filename){
         Files f = fileService.getFileByName(filename);
         byte[] fileData = f.getFiledata();
         return ResponseEntity.ok()
                         .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+filename+"\"")
                                 .contentType(MediaType.parseMediaType(f.getContenttype()))
                                         .contentLength(Long.parseLong(f.getFilesize()))
                                                 .body(fileData);



    }

    @PostMapping
    public String uploadFile(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm, Model model, @RequestParam("file") MultipartFile file) throws IOException {
        String username = authentication.getName();
        int userid = userService.getUser(username).getUserId();


            if(file.isEmpty()){

                model.addAttribute("result","error");
                return "result";
            }

            // check if a file already exists with the name
            boolean isDuplicate=false;
            String actualfilename = file.getOriginalFilename();
            List<Files> allFiles = fileService.getFiles(userid);
            for(Files currentFile : allFiles){
                if(currentFile.getFilename().equals(actualfilename)){
                    isDuplicate = true;
                    break;
                }
            }

            if(isDuplicate) {
                model.addAttribute("result", "error");
                return "result";
            }
            else {

                String filename = file.getOriginalFilename();
                String contenttype = file.getContentType();
                String filesize = String.valueOf(file.getSize());
                byte[] filedata = file.getBytes();

                fileService.insertFile(new Files(0,filename, contenttype, filesize,userid, filedata));
                model.addAttribute("result","success");

            }


        return "result";

    }
    @GetMapping("/remove/{filename}")

    public String deleteFile(Authentication authentication, @ModelAttribute("fileForm") FileForm fileForm, Model model,@PathVariable String filename ){

        try{

            String username = authentication.getName();
            int userid = userService.getUser(username).getUserId();

            fileService.deleteFile(filename);


            model.addAttribute("files", this.fileService.getFiles(userid));
            model.addAttribute("result", "deletion-success");
        }
        catch(Exception e){
            model.addAttribute("result","failed");


        }
        return "result";

    }



}
