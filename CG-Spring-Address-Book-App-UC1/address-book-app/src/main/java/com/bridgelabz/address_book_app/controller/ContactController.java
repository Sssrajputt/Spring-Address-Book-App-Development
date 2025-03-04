package com.bridgelabz.address_book_app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressbook")
public class ContactController {

    @GetMapping("/")
    public String getAllContacts() {
        return "Getting all contacts";
    }

    @GetMapping("/get/{id}")
    public String getContactById(@PathVariable Long id) {
        return "Getting contact with ID: " + id;
    }

    @PostMapping("/create")
    public String createContact(@RequestBody String contact) {
        return "Creating contact: " + contact;
    }

    @PutMapping("/update")
    public String updateContact(@RequestBody String contact) {
        return "Updating contact: " + contact;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        return "Deleting contact with ID: " + id;
    }
}
