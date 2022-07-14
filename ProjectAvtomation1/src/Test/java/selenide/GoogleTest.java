package selenide;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleTest {
   @BeforeClass
    public void setUp() {
       Configuration.holdBrowserOpen = true;
       Configuration.savePageSource = false;
   }

    @Test
    public void userShouldSearch() {
        open("https://google.com/"); //по умолчанию открывает хром
        $("[name='q']")    //метод $
                .shouldBe(visible)
                .setValue("Selenide")
                .pressEnter();
        $$x("//h3[contains(text(), 'Selenide')]") //метод $$ - возвращает ElementCollection, $$x - для xpath
                .filter(visible)
                .shouldHave(sizeGreaterThanOrEqual(7))
                .get(0)
                .click();
        $(".donate_header")
                .shouldHave(text("Селенид поддерживает Украину \uD83C\uDDFA\uD83C\uDDE6"));

        $x("//a[contains(text(), 'Блог')]")
                .click();

        $x("//h3[contains(text(), 'Блог Selenide')]")
                .shouldBe(visible);
    }
}




