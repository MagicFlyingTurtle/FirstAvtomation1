package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected  String pageUr1;
    protected WebDriverWait webDriverWait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(4));
        PageFactory.initElements(driver, this);
    }

    public String getPageUr1() {
        return pageUr1;
    }

    public void waitUntilLoaded(){
        webDriverWait.until(ExpectedConditions.urlContains(pageUr1));
    }

    public void navigatore() {
        driver.get(pageUr1);
    }
}
