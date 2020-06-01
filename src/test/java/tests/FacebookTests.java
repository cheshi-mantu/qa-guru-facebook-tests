package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.FacebookPage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.Environment.*;
import static helpers.FileReadHelper.getStringFromFile;
import static io.qameta.allure.Allure.step;


@Epic("QA.GURU QA automation course")
@Story("Facebook tests")
@Tag("facebook_test")
class FacebookTests extends TestBase {
    private String finalEmail ="";
    private String finalPassword = "";
    @BeforeEach
    void setFinalEmail() {
        if (email.equals("empty_string_returned")) {
            finalEmail = getStringFromFile("D:\\code\\qa-guru-facebook-tests\\src\\test\\java\\tests\\facebookname.secret");
        } else {
            finalEmail = email;
        }
    }
    @BeforeEach
    void setFinalPassword(){
        if (password.equals("empty_string_returned")) {
            finalPassword = getStringFromFile("D:\\code\\qa-guru-facebook-tests\\src\\test\\java\\tests\\facebookpassword.secret");
        } else {
            finalPassword = password;
        }
    }

// all stuff that starts with @ is from JUnit - platform for tests
//    how to start the test from command line:
//    gradle facebook_tests -Durl=http://facebook.com -Demail=cheshi.mantu@gmail.com -Dpassword=”fuckoff” -Dnamesurname="Cheshi Mantu"
//    gradle - builder which runs all the stuff
//    facebook_tests is task from build.gradle it will select needed tasks by tag "@Test" used by JUnit and tag @Tag("facebook"), see above
    @Test
    @Description("Positive test with data-testid")
    void successfulLoginWithTestId() {
        System.out.println("Email: " + finalEmail);
        System.out.println("Password: " + finalPassword);

        Configuration.browser = "opera";
//      This bicth is loading so slow, so we need 10 seconds to wait, 4 by default is not enough
        Configuration.timeout = 10000;
//      we'll use systemProperty "url" defined in helpers.Environment instead of using hardcoded string with web-site address
        open(url);
//      we'll use systemProperty "email" defined in helpers.Environment instead of using hardcoded string with email address
        $(by("data-testid", "royal_email")).setValue(finalEmail); // Do not store private data in code!
        $(by("data-testid", "royal_pass")).setValue(finalPassword);
        $(by("data-testid", "royal_login_button")).click();
        $(".linkWrap.noCount").shouldHave(text(namesurname));
    }
    @Test
    @Description("Successfull Facebook Test with PageObject and Env")
    void successfulLoginWithPageObject() {
        Configuration.browser = "opera";
        FacebookPage facebookPage = new FacebookPage();

        open(url);

        facebookPage.typeEmail(finalEmail);

        facebookPage.typePassword(finalPassword);

        facebookPage.clickSubmit();

        facebookPage.verifyLoggedInAsUser(namesurname);
    }

    @Test
    @Story("Facebook profile update test")
    @Description("Open page, click on profile, click on Edit profile, update some item, and check")
    void userProfileUpdate() {
        Configuration.browser = "opera";
//        Configuration.browser = "edge";
//        Configuration.browser = "firefox";
//        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
        Configuration.timeout = 10000;
        //Configuration.browserSize = "1920x1080";
        FacebookPage facebookPage = new FacebookPage();
            step ("Open facebook page via http link", () -> {
            open(url);
            facebookPage.typeEmail(finalEmail);
            facebookPage.typePassword(finalPassword);
            facebookPage.clickSubmit();
            facebookPage.verifyLoggedInAsUser(namesurname);
    //            $(getFocusedElement()).pressEscape();
        }
        );

            step("Enter the user profile", () -> {
                $(byAttribute("title", "Profile")).click(); // works
                //$(byText("Cheshi")).click(); // works
//                $(byPartialLinkText("Edit Profile")).shouldBe(visible);
//                $(by("ajaxify","/profile/edit/public/show/")).should(exist); //worked
            }
            );
            step("Click Edit Profile", ()->{
//                $(byLinkText("Edit Profile")).shouldBe(visible);
//                $(byAttribute("ajaxify","/profile/edit/public/show/")).click(); // worked
                $(byPartialLinkText("Edit Profile")).click();
//                $(byText("Edit Profile")).should(exist);
//                $(byLinkText("Edit Profile")).click(); // works
            });

            step("Edit about info, Life Events link must be available on the page", ()->{
                $(byText("Edit your about info")).click();
//                $("#medley_header_about").should(exist);
//                $(byLinkText("About")).should(exist);
//                $(byLinkText("Life Events")).should(exist);
            });
            step("Go to Life Events and check if 'Add a life event' exists", ()->{
                $(byLinkText("Life Events")).click();
//                $("html").shouldHave(text("Add a life event"));

            });
            step("Click on Add a life event", ()->{
                $(byText("Add a life event")).click();
//                $(byText("Life Events")).should(exist);
//                $(byText("Relationship")).should(exist);
            });
            step("Click on Relationship, then click on New Relationship, then click on SaySomething, then set stupid string, then click share", ()->{
                Configuration.timeout = 10000;
                $(byText("Relationship")).click();
                $(byText("New Relationship")).click();
                $(withText("Say something")).click();
                $(getFocusedElement()).sendKeys("112233445566");
                $(byText("Share")).click();

            });
            step("homepage and check we have Relationship update in the timeline", ()->{

                $(byLinkText("Home")).click(); // works
                $("body").shouldHave(text("112233445566"));
//                $(byText("Timeline")).shouldBe(visible);
//                $(byText("Timeline")).click();
//                $(byTagName("div")).$(byAttribute("role", "feed")).shouldHave(text("112233445566"));
            });
//            step("", ()->{});
//            step("", ()->{});
//            step("", ()->{});

    }

}
