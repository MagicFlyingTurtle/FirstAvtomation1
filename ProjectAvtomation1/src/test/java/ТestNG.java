import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ТestNG {
    String baseUr1 = "http://www.leafground.com/pages/checkbox.html";
    private By selectLanguageLocator = By.xpath("//label[text()='Select the languages that you know?']" +
            "/following-sibling::input");
    private By seleniumLocator = By.xpath("//label[text()='Confirm Selenium is checked']/following-sibling::" +
            "input[@type='checkbox']");
    private By deSelectLocator = By.xpath("//*[@id=\"contentblock\"]/section/div[3]/input[2]");
    private By selectAllCheckboxesLocator = By.xpath ("//label[text()='Select all below checkboxes ']/" +
            "following-sibling::input");

    private WebDriver driver;
    private WebElement selectLanguage;
    private WebElement selenium;
    private WebElement deSelect;
    private WebElement selectAllCheckboxes;


    @BeforeClass
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(baseUr1);

        selectLanguage = driver.findElement(selectLanguageLocator);
        selenium = driver.findElement(seleniumLocator);
        deSelect = driver.findElement(deSelectLocator);
        selectAllCheckboxes = driver.findElement(selectAllCheckboxesLocator);
    }

    @Test
    public void selectLanguageTests() {
        selectLanguage.click();
        Assert.assertTrue(selectLanguage.isSelected());
    }

    @Test
    public void seleniumTests() {
        Assert.assertTrue(selenium.isSelected());
    }

    @Test
    public void deSelectTests() {
        deSelect.click();
        Assert.assertFalse(deSelect.isSelected());
    }

    @Test
    public void selectAllCheckboxesTests() {
        List<WebElement> allchecks = driver.findElements(selectAllCheckboxesLocator);
        for (WebElement check : allchecks) {
            check.click();
        }
        for (WebElement check : allchecks) {
            Assert.assertTrue(check.isSelected());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.close();
    }
}
