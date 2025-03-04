package com.bridgelabz.address_book_app.service;

import com.bridgelabz.address_book_app.dto.ContactDTO;
import com.bridgelabz.address_book_app.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService implements IContactService {

    private final List<Contact> contacts = new ArrayList<>();
    private long currentId = 1;

    @Override
    public Contact createContact(ContactDTO contactDTO) {
        Contact contact = new Contact();
        contact.setId(currentId++);
        contact.setName(contactDTO.getName());
        contact.setPhoneNumber(contactDTO.getPhoneNumber());
        contact.setEmail(contactDTO.getEmail());
        contact.setAddress(contactDTO.getAddress());
        contacts.add(contact);
        return contact;
    }

    @Override
    public Optional<Contact> getContactById(Long id) {
        return contacts.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    @Override
    public List<Contact> getAllContacts() {
        return contacts;
    }

    @Override
    public Contact updateContact(Long id, ContactDTO contactDTO) {
        Optional<Contact> contactOptional = getContactById(id);
        if (contactOptional.isPresent()) {
            Contact contact = contactOptional.get();
            contact.setName(contactDTO.getName());
            contact.setPhoneNumber(contactDTO.getPhoneNumber());
            contact.setEmail(contactDTO.getEmail());
            contact.setAddress(contactDTO.getAddress());
            return contact;
        } else {
            return null;
        }
    }

    @Override
    public void deleteContact(Long id) {
        contacts.removeIf(contact -> contact.getId().equals(id));
    }
}
