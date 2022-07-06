import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AppearPage;
import pages.DisappearPage;
import pages.TextChangePage;

public class TestWaiters {
    private WebDriver driver;

    @BeforeClass
    public  void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void checkDisappear () {
        DisappearPage disappearPage = new DisappearPage(driver);
        disappearPage.navigatore();
        disappearPage.waitUntilLoaded();
        Assert.assertTrue(disappearPage.checkDisappeared());
    }

    @Test
    public void checkAppear () {
        AppearPage appearPage = new AppearPage(driver);
        appearPage.navigatore();
        appearPage.waitUntilLoaded();
        Assert.assertTrue(appearPage.checkAppeared().isDisplayed());
    }

    @Test
    public void checkChangeText () {
        TextChangePage textChangePage = new TextChangePage(driver);
        textChangePage.navigatore();
        textChangePage.waitUntilLoaded();
        Assert.assertTrue(textChangePage.checkChangeText("Click ME!"));
    }
}
