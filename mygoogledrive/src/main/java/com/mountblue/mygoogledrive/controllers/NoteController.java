package com.mountblue.mygoogledrive.controllers;

import com.mountblue.mygoogledrive.entities.Note;
import com.mountblue.mygoogledrive.services.NoteService;
import com.mountblue.mygoogledrive.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;
    @Autowired
    private FileService fileService;

    @PostMapping("/drive/save-note")
   @PreAuthorize("authentication.name == authentication.name")
   public String createNote(@RequestParam  ("note") String note , Authentication authentication){
        noteService.addNewNote(note, authentication);
       return "redirect:/drive";
    }
}
