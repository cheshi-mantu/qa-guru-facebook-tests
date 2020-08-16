package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideDriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.PressEscape;
import helpers.GetTimeStampHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pages.FacebookPage;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import static helpers.GetTimeStampHelper.*;
import static io.qameta.allure.Allure.step;


@Epic("QA.GURU QA automation course")
@Story("Facebook tests")
@Tag("facebook_test")
class FacebookTests extends TestBase {
    private String strTimeStamp = "";
    @Test
    @Description("Positive log in test with data-testid")
    void successfulLoginWithTestId() {
        step ("PREP: Open facebook page via http link", () -> {
            open(fbUrlStr);
        });
        step ("ACT: Fill log-in data using data-testid attributes", () -> {
            $(by("data-testid", "royal_email")).setValue(fbNameStr);
            $(by("data-testid", "royal_pass")).setValue(fbPasswordStr);
            $(by("data-testid", "royal_login_button")).click();
        });
        step ("CHECK: Opened page should contain name and surname " + fbNameSurnameStr, () -> {
            $(".linkWrap.noCount").shouldHave(text(fbNameSurnameStr));
        });
    }

    @Test
    @Description("Successfull Facebook Test with PageObject and Env")
    void successfulLoginWithPageObject() {
        FacebookPage facebookPage = new FacebookPage();
        step ("PREP: Open fb log-in page using page objects", ()->{
            open(fbUrlStr);
        });
        step ("ACT: Enter log-in data and press submit button using page object", ()->{
            facebookPage.typeEmail(fbNameStr);
            facebookPage.typePassword(fbPasswordStr);
            facebookPage.clickSubmit();
        });
        step ("CHECK: Verify log in is successful and there is uder name and surname on the page", ()->{
            facebookPage.verifyLoggedInAsUser(fbNameSurnameStr);
        });
    }

    @Test
    @Story("Facebook profile update test")
    @Description("Open page, click on profile, click on Edit profile, update some item, and check")
    void userProfileUpdate() {
        step("PREP: build a message to use to update the page", () -> {
            strTimeStamp = getTimeStamp();
        });
        FacebookPage facebookPage = new FacebookPage();
        step ("PREP: Open facebook page via http link using page object", () -> {
            open(fbUrlStr);
        });
        step("ACT: login using page object", () -> {
            facebookPage.typeEmail(fbNameStr);
            facebookPage.typePassword(fbPasswordStr);
            facebookPage.clickSubmit();
        });
        step("CHECK: verify successful log in", () -> {
            facebookPage.verifyLoggedInAsUser(fbNameSurnameStr);
        });
        step("PREP: Go to user's profile", () -> {
            $(byAttribute("title", "Profile")).click();
        });
        step("PREP: Click Edit Profile", ()->{
            $("[label='Create Post']").click();
        });
        step("PREP: Click on What's on your mind", ()->{
            $("[role='textbox']").click();
        });
        //TODO: to finalize
        step("ACT: Click on Add a life event and add life event", ()->{
            $(":focus").setValue("Today is " + strTimeStamp + " nothing is happening");
            $("[type='submit']").click();
        });
        step("CHECK: homepage should have Relationship update with " + strTimeStamp, ()->{
            $(byLinkText("Home")).click(); // works
            $("body").shouldHave(text(strTimeStamp));
        });

    }
//#TODO: add custom webdriver to prepare to run in CI
}
