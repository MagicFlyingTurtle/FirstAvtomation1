package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import testdata.User;

public class LoginPage extends BasePage{
    private By loginField = By.name("login");
    private By passwordField = By.cssSelector("[name='password']");
    private By submitButton = By.cssSelector("[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
        pageUr1 = "https://mail.ukr.net/";
    }

    public void login(User user){
        driver.findElement(loginField).sendKeys(user.getLogin());
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
    }
}
