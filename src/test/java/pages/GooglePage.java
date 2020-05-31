package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

//
public class GooglePage {
    // Здесь храним элементы и css/xpath локаторы к ним

    SelenideElement searchInput = $(byName("q")),
            htmlBody = $("html");


    // Здесь реализована логика работы со страницей
    @Step("Type search string {searchstring} and press Enter")
    public void typeSearch(String text) {
        searchInput.val("lepra").pressEnter();
    }
    @Step("Chech search string is present {searchstring}")
    public void verifySearchHasResult(String text) {
        htmlBody.shouldHave(text(text)); // тоже самое, что и $(byText(text)).shouldBe(visible);
    }

}


