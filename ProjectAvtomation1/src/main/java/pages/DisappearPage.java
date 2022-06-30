package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DisappearPage extends  BasePage {
    @FindBy(id = "btn")
    private WebElement disappearingElement;

    public DisappearPage (WebDriver driver) {
        super(driver);
        pageUr1 = "http://www.leafground.com/pages/disapper.html";
    }

    public boolean checkDisappeared() {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(disappearingElement));
    }
}
