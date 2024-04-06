package org.example.service;

import org.example.exception.UserAlreadyExistsException;
import org.example.exception.UserNotFoundException;
import org.example.model.User;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserService {

    private final Map<String, User> userStorage = new HashMap<>();

    private final FileWriter fileWriter;

    public UserService(FileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public Collection<User> getAll() {
        return userStorage.values();
    }

    public void save(User user) {
        if (userStorage.containsKey(user.getEmail())) {
            throw new UserAlreadyExistsException(MessageFormat.format("Пользователь с email {0} уже существует!",
                    user.getEmail()));
        }
        userStorage.put(user.getEmail(), user);
    }

    public void delete(String email) {
        if (!userStorage.containsKey(email)) {
            throw new UserNotFoundException(MessageFormat.format("Пользователь с email {0} не найден!", email));
        }
        userStorage.remove(email);
    }

    public void storeToFile() {
        fileWriter.storeToFile(new ArrayList<>(userStorage.values()));
    }

}
