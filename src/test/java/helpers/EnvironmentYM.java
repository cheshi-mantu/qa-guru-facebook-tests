package helpers;

public class EnvironmentYM {
    // config is common for all the tests
    public final static String
            //
            url = System.getProperty("url", "http://market.yandex.ru"),
            searchstring = System.getProperty("searchstring", "Холодильник");
}
