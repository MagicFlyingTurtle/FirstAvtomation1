package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class HomePage extends  BasePage{
    private By writeLetterButton = By.cssSelector(".primary.compose");
    private By toInput = By.name("toFieldInput");
    private By subjectInput = By.name("subject");
    private By letterBody = By.id("tinymce");
    private By sendButton = By.cssSelector(".screen__head .send.button");
    private By bodyFrame = By.cssSelector("#mce_0_ifr");
    private By letterIsSend = By.cssSelector(".sendmsg__ads-ready"); //Лист надіслано

    public HomePage(WebDriver driver) {
        super(driver);
        pageUr1 = "https://mail.ukr.net/desktop";
    }

    public void  clickWriteLetter(){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(writeLetterButton));
        driver.findElement(writeLetterButton).click();
    }

    public  void writeLetter(String to, String subject, String body) {
        driver.findElement(toInput).sendKeys(to);
        driver.findElement(subjectInput).sendKeys(subject);
        try {
            driver.switchTo().frame(driver.findElement(bodyFrame));
            driver.findElement(letterBody).sendKeys(body);
        }
        finally {
            driver.switchTo().parentFrame();
        }

    }

    public void sendLetter(){

        driver.findElement(sendButton).click();
    }

    public boolean getTextLetterIsSend(String expectedText) {
        return webDriverWait.until(ExpectedConditions.textMatches(letterIsSend, Pattern.compile("^"+
                expectedText + "\n*")));
    }

}
