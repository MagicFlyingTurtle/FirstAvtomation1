package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailinatorInboxPage extends BasePage {
    @FindBy(xpath = "//td[contains(text(),'MySubject')]")
    private WebElement lastLetter;

    @FindBy(id = "inbox_field")
    private WebElement inboxField;

    @FindBy(className = "primary-btn")
    private WebElement goButton;
    public MailinatorInboxPage(WebDriver driver) {
        super(driver);
        pageUr1 = "https://www.mailinator.com/v4/public/inboxes.jsp";
    }

    public void clickLastLetter(){
        webDriverWait.until(ExpectedConditions.visibilityOf(lastLetter));
        lastLetter.click();
    }

    public void goToInbox(String inbox){
        inboxField.sendKeys(inbox);
        goButton.click();
    }
}




