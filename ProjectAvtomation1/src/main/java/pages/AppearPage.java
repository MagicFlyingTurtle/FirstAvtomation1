package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AppearPage extends BasePage{
    @FindBy(id = "btn")
    private WebElement appearElement;

    public AppearPage (WebDriver driver) {
        super(driver);
        pageUr1 = "http://www.leafground.com/pages/appear.html";
    }

    public WebElement checkAppeared() {
        return webDriverWait.until(ExpectedConditions.visibilityOf(appearElement));
    }
}
