package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.FacebookPage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static io.qameta.allure.Allure.step;


@Epic("QA.GURU QA automation course")
@Story("Facebook tests")
@Tag("facebook_test")
class FacebookTests extends TestBase {
    @Test
    @Description("Positive test with data-testid")
    void successfulLoginWithTestId() {
        open(fbUrlStr);
        $(by("data-testid", "royal_email")).setValue(fbNameStr);
        $(by("data-testid", "royal_pass")).setValue(fbPasswordStr);
        $(by("data-testid", "royal_login_button")).click();
        $(".linkWrap.noCount").shouldHave(text(fbNameSurnameStr));
    }
    @Test
    @Description("Successfull Facebook Test with PageObject and Env")
    void successfulLoginWithPageObject() {
        FacebookPage facebookPage = new FacebookPage();
        open(fbUrlStr);
        facebookPage.typeEmail(fbNameStr);
        facebookPage.typePassword(fbPasswordStr);
        facebookPage.clickSubmit();
        facebookPage.verifyLoggedInAsUser(fbNameSurnameStr);
    }

    @Test
    @Story("Facebook profile update test")
    @Description("Open page, click on profile, click on Edit profile, update some item, and check")
    void userProfileUpdate() {
        FacebookPage facebookPage = new FacebookPage();
            step ("Open facebook page via http link", () -> {
            open(fbUrlStr);
            facebookPage.typeEmail(fbNameStr);
            facebookPage.typePassword(fbPasswordStr);
            facebookPage.clickSubmit();
            facebookPage.verifyLoggedInAsUser(fbNameSurnameStr);
        });

            step("Enter the user profile", () -> {
                $(byAttribute("title", "Profile")).click(); // works
            });
            step("Click Edit Profile", ()->{
                $(byPartialLinkText("Edit Profile")).click();
            });

            step("Edit about info, Life Events link must be available on the page", ()->{
                $(byText("Edit your about info")).click();
            });
            step("Go to Life Events and check if 'Add a life event' exists", ()->{
                $(byLinkText("Life Events")).click();

            });
            step("Click on Add a life event", ()->{
                $(byText("Add a life event")).click();
            });
            step("Click on Relationship, then click on New Relationship, then click on SaySomething, then set stupid string, then click share", ()->{
                $(byText("Relationship")).click();
                $(byText("New Relationship")).click();
                $(withText("Say something")).click();
                $(getFocusedElement()).sendKeys("112233445566");
                $(byText("Share")).click();

            });
            step("homepage and check we have Relationship update in the timeline", ()->{

                $(byLinkText("Home")).click(); // works
                $("body").shouldHave(text("112233445566"));
            });

    }

}
