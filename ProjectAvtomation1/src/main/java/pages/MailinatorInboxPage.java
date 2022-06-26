package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MailinatorInboxPage extends BasePage {
    private By lastLetter = By.xpath("//td[contains(text(),'MySubject')]");
    private  By inboxField = By.id("inbox_field");
    private  By goButton = By.className("primary-btn");

    public MailinatorInboxPage(WebDriver driver) {
        super(driver);
        pageUr1 = "https://www.mailinator.com/v4/public/inboxes.jsp";
    }

    public void clickLastLetter(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(lastLetter));
        driver.findElement(lastLetter).click();
    }

    public void goToInbox(String inbox){
        driver.findElement(inboxField).sendKeys(inbox);
        driver.findElement(goButton).click();
    }
}




