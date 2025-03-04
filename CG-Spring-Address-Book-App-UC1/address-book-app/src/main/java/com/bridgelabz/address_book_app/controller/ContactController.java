package com.bridgelabz.address_book_app.controller;

import com.bridgelabz.address_book_app.dto.ContactDTO;
import com.bridgelabz.address_book_app.model.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/addressbook")
public class ContactController {

    private final List<Contact> contacts = new ArrayList<>();
    private long currentId = 1;

    @GetMapping("/")
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable Long id) {
        Optional<Contact> contact = contacts.stream().filter(c -> c.getId().equals(id)).findFirst();
        return contact.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public Contact createContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setId(currentId++);
        contact.setName(contactDTO.getName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setEmail(contactDTO.getEmail());
        contact.setAddress(contactDTO.getAddress());
        contacts.add(contact);
        return contact;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        Optional<Contact> contactOptional = contacts.stream().filter(c -> c.getId().equals(id)).findFirst();
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            contact.setName(contactDTO.getName());
            contact.setPhoneNumber(contactDTO.getPhoneNumber());
            contact.setEmail(contactDTO.getEmail());
            contact.setAddress(contactDTO.getAddress());
            return new ResponseEntity<>(contact, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
