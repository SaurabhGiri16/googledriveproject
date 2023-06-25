package com.mountblue.mygoogledrive.controllers;

import com.mountblue.mygoogledrive.entities.Contact;
import com.mountblue.mygoogledrive.services.ContactService;
import com.mountblue.mygoogledrive.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private FileService fileService;

    @PostMapping("/drive/create-contact")
    @PreAuthorize("authentication.name == authentication.name")
    public String createContact(@RequestParam("c-name") String name , @RequestParam("c-number") String number, Authentication authentication){
            contactService.addNewContact(name, number, authentication);
            return "redirect:/drive";
    }

//    @PostMapping("/drive/show-contacts")
//    @PreAuthorize("authentication.name == authentication.name")
//    public String showContacts(Authentication authentication, Model model){
//        List<Contact> contacts = contactService.showContacts(authentication);
//        model.addAttribute("contacts",contacts);
//        return "redirect:/drive";
//    }

}
