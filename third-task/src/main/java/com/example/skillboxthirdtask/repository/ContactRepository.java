package com.example.skillboxthirdtask.repository;
import com.example.skillboxthirdtask.model.Contact;

import java.util.List;

public interface ContactRepository {
    List<Contact> findAll();
    Contact findById(Long id);
    void save(Contact contact);
    void update(Contact contact);
    void deleteById(Long id);
    boolean existsById(Long id);
}
