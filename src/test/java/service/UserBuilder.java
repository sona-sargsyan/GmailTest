package service;

import model.User;

import static service.TestDataReader.getTestData;
import static util.Constants.Properties.PASSWORD;
import static util.Constants.Properties.USERNAME;

public class UserBuilder {

    public static User withCredentialsFromProperty() {
        return new User(getTestData(USERNAME), getTestData(PASSWORD));
    }

}
