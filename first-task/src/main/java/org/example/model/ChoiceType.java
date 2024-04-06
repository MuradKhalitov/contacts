package org.example.model;

import org.example.exception.InvalidChoiceCodeException;

import java.util.Arrays;

public enum ChoiceType {
    ALL_CONTACTS(1),
    ADD_CONTACT(2),
    DELETE_CONTACT(3),
    STORE_CONTACTS_TO_FILE(4),
    EXIT(0);

    private final Integer choiceCode;

    ChoiceType(int choiceCode) {
        this.choiceCode = choiceCode;
    }

    public static ChoiceType from(String choice) {
        try {
            Integer code = Integer.parseInt(choice);
            return Arrays.stream(values())
                    .filter(type -> type.choiceCode.equals(code))
                    .findFirst()
                    .orElseThrow(() -> new InvalidChoiceCodeException("Действие не найдено!"));
        } catch (NumberFormatException ex) {
            throw new InvalidChoiceCodeException("Неверный формат!");
        }
    }
}
