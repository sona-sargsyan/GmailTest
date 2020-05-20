package service;

import model.User;

public class UserBuilder {
    public static final String USERNAME = "test.user.name";
    public static final String PASSWORD = "test.user.password";

    public static User withCredentialsFromProperty(){
        return new  User(TestDataReader.getTestData(USERNAME),TestDataReader.getTestData(PASSWORD));
       // return new User("mentor.mentroevich","mentroevich12");
    }

//    public static User withEmptyUsername(){
//        return new User("",TestDataReader.getTestData(PASSWORD));
//    }
//
//    public static User withEmptyPassword(){
//        return new User(TestDataReader.getTestData(USERNAME), "");
//    }
}
