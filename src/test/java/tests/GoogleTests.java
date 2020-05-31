package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import pages.GooglePage;

@Epic("QA.GURU automation course")
@Story("Google tests")
@Tag("google_test")
class GoogleTests extends TestBase {

    @Test
    @Description("Positive test without PO and without Env")

// without page object
    void successfulSearch() {
        Configuration.browser = "opera";
        GooglePage googlePage = new GooglePage();
        open("http://google.com");

        $(byName("q")).val("lepra").pressEnter();

        $("html").shouldHave(text("Лепрозорий: вход"));
    }

    //    with page object but no environment!
    @Test
    @Description("Positive test with page Page Object enabled")

    void successfulSearchWithPO() {
        Configuration.browser = "opera";
        GooglePage googlePage = new GooglePage();

        open("http://google.com");

        googlePage.typeSearch("lepra");

        googlePage.verifySearchHasResult("Лепрозорий: вход");
    }


}