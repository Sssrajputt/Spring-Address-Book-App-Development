package com.bridgelabz.address_book_app.service;

import com.bridgelabz.address_book_app.dto.ContactDTO;
import com.bridgelabz.address_book_app.model.Contact;

import java.util.List;
import java.util.Optional;

public interface IContactService {
    Contact createContact(ContactDTO contactDTO);
    Optional<Contact> getContactById(Long id);
    List<Contact> getAllContacts();
    Contact updateContact(Long id, ContactDTO contactDTO);
    void deleteContact(Long id);
}
