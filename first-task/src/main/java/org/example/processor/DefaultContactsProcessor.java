package org.example.processor;

import org.example.service.FileReader;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("init")
public class DefaultContactsProcessor {

    @Value("${app.default-contacts-path}")
    private String filePath;

    private final FileReader fileReader;

    private final UserService userService;

    public DefaultContactsProcessor(FileReader fileReader, UserService userService) {
        this.fileReader = fileReader;
        this.userService = userService;
    }

    @PostConstruct
    public void saveDefaultContacts() {
        fileReader.readFileFrom(filePath).forEach(userService::save);
    }

}
