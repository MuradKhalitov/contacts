package org.example.model;

import org.example.exception.UserParseException;

public class User {

    private String fullName;

    private String phoneNumber;

    private String email;

    public User() {}

    private User(String fullName, String phoneNumber, String email) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public static User of(String fullName, String phoneNumber, String email) {
        return new User(fullName, phoneNumber, email);
    }

    public String toUserInfoString() {
        StringBuilder builder = new StringBuilder();
        builder.append(fullName)
                .append(";")
                .append(phoneNumber)
                .append(";")
                .append(email);

        return builder.toString();
    }

    public static User parse(String userInfo) {
        String[] values = userInfo.split(";");
        if (values.length != 3) {
            throw new UserParseException("Ошибка при попытке создания пользователя!");
        }

        return new User(values[0].trim(), values[1].trim(), values[2].trim());
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
