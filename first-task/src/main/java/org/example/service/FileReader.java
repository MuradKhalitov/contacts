package org.example.service;

import org.example.exception.ReadFileException;
import org.example.model.User;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class FileReader {

    public List<User> readFileFrom(String resourcePath) {
        return collectFromString(readeResource(resourcePath));
    }

    private List<User> collectFromString(String text) {
        List<User> users = new ArrayList<>();
        String[] data = text.split("\n");

        for (String userInfo : data) {
            users.add(User.parse(userInfo));
        }

        return users;
    }

    private String readeResource(String resourcePath) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(MessageFormat.format("classpath:{0}", resourcePath));

        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new ReadFileException("Ошибка при чтении файла!");
        }
    }
}
