package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextChangePage extends BasePage {
    @FindBy(id = "btn")
    private WebElement ChangeElement;

    public TextChangePage(WebDriver driver) {
        super(driver);
        pageUr1 = "http://www.leafground.com/pages/TextChange.html";
    }

    public boolean checkChangeText(String expectedText) {
        return webDriverWait.until(ExpectedConditions.textToBePresentInElement(ChangeElement, expectedText));
    }
}