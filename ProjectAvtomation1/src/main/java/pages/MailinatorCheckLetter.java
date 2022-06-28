package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MailinatorCheckLetter extends BasePage {
    @FindBy(css = ".fz-20.ng-binding")
    List<WebElement> subjectAndFromReceivedEmail;

    @FindBy(css = ".xfmc1")
    private WebElement bodyReceivedEmail;

    @FindBy(id = "html_msg_body")
    private WebElement frameidBodyReceivedEmail;

    private String textBody;

    public MailinatorCheckLetter(WebDriver driver) {
        super(driver);
    }

    public boolean getSubjectReceivedEmail(String expectedText) {
        return webDriverWait.until(ExpectedConditions.textToBePresentInElement(subjectAndFromReceivedEmail.get(0), expectedText));
    }

    public String getFromReceivedEmail() {
        return subjectAndFromReceivedEmail.get(2).getText();
    }

    public String getBodyReceivedEmail() {
        driver.switchTo().frame(frameidBodyReceivedEmail);
        textBody = bodyReceivedEmail.getText();
        driver.switchTo().parentFrame();
        return textBody;
    }
}
