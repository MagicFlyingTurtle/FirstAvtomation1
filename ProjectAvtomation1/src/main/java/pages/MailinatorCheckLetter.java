package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.regex.Pattern;

public class MailinatorCheckLetter extends BasePage {
    private By subjectandFromReceivedEmail = By.cssSelector(".fz-20.ng-binding");
    private By bodyReceivedEmail = By.cssSelector(".xfmc1");
    private By frameidBodyReceivedEmail = By.id("html_msg_body");

    private String textBody;

    public MailinatorCheckLetter(WebDriver driver) {
        super(driver);
    }

    public boolean getSubjectReceivedEmail(String expectedText) {
        return  webDriverWait.until(ExpectedConditions.textMatches(subjectandFromReceivedEmail,
                Pattern.compile(expectedText)));
    }

    public String getFromReceivedEmail() {
        List<WebElement> fromEmail = driver.findElements(subjectandFromReceivedEmail);
        return fromEmail.get(2).getText();
    }

    public String getBodyReceivedEmail() {
        driver.switchTo().frame(driver.findElement(frameidBodyReceivedEmail));
        textBody = driver.findElement(bodyReceivedEmail).getText();
        driver.switchTo().parentFrame();
        return textBody;

    }
}
