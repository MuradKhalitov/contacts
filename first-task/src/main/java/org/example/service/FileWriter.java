package org.example.service;

import org.example.exception.WriteDataException;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Component
public class FileWriter {
    @Value("${app.file-for-save}")
    private String filePath;

    public void storeToFile(List<User> users) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < users.size(); i++) {
            builder.append(users.get(i).toUserInfoString());
            if (i < users.size() - 1) {
                builder.append("\n");
            }
        }

        writeFile(builder.toString());
    }

    private void writeFile(String data) {
        try {
            Files.writeString(Path.of(filePath), data, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new WriteDataException("Ошибка при попытке записи данных в файл!");
        }
    }
}
