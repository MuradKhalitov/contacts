package com.example.skillboxthirdtask.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.skillboxthirdtask.model.Contact;
import com.example.skillboxthirdtask.repository.ContactRepository;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> getAllContacts() {

        return contactRepository.findAll();
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public void createContact(Contact contact) {
        contactRepository.save(contact);
    }

    public void updateContact(Contact contact) {
        if (contactRepository.existsById(contact.getId())) {

            contactRepository.update(contact);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }

    public void deleteContactById(Long id) {
        contactRepository.deleteById(id);
    }
}
