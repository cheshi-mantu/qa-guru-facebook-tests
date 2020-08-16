package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static helpers.DriverHelper.configureSelenide;
import static helpers.Environment.*;
import static helpers.LoadCredentialsHelper.*;

public class TestBase {
    static String fbNameStr = "";
    static String fbPasswordStr = "";
    static String fbNameSurnameStr = "";
    static String fbUrlStr = "";


    @BeforeAll
    public static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        configureSelenide();
        Configuration.startMaximized = true;
        Configuration.timeout = 10000; // facebook sometimes is quite slow

        if (isCiBuild) {
        fbNameStr = fbUserName;
        fbPasswordStr = fbPassword;
        fbNameSurnameStr = fbNameSurname;
        fbUrlStr = fbUrl;
        } else {
            fbNameStr = getCredentialsFromJson("fb_credentials.secret", "fb_username");
            fbPasswordStr = getCredentialsFromJson("fb_credentials.secret", "fb_password");
            fbNameSurnameStr = getCredentialsFromJson("fb_credentials.secret", "fb_name_surname");
            fbUrlStr = fbUrl;
        }
    }

    @AfterEach
    public void cleanUp(){
        closeWebDriver();
    }
}
