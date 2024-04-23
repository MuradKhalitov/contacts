package com.example.skillboxthirdtask.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.skillboxthirdtask.model.Contact;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contacts";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class));
    }

    @Override
    public Contact findById(Long id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Contact.class));
    }

    @Override
    public void save(Contact contact) {
        String sql = "INSERT INTO contacts (id, firstName, lastName, email, phone) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, ThreadLocalRandom.current().nextInt(9999), contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
    }


    @Override
    public void update(Contact contact) {
        String sql = "UPDATE contacts SET firstName = ?, lastName = ?, email = ?, phone = ? WHERE id = ?";
        jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone(), contact.getId());
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM contacts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM contacts WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

}
