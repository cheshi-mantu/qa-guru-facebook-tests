package helpers;

public class Environment {
    // config is common for all the tests
    public final static String

        url = System.getProperty("url", "http://facebook.com"),
        email = System.getProperty("email", "empty_string_returned"),
        password = System.getProperty("password", "empty_string_returned"),
        namesurname =  System.getProperty("namesurname", "Cheshi Mantu");

}

