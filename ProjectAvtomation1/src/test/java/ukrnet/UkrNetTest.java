package ukrnet;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MailinatorCheckLetter;
import pages.MailinatorInboxPage;
import testdata.User;

public class UkrNetTest {
   private WebDriver driver;
   private String login = "turtlemagic@ukr.net";
    private String password = "qwerty15";

    private String bodyLetter = "Test body";
    private String subjectLetter = "MySubject";
    private String loginMailinator = "turtlemagic";

    @BeforeClass
    public void setUp() {
        System.setProperty("selenium.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void sendEmailToMailinatorTest() {
        User user = new User(login, password);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigatore();
        loginPage.login(user);
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilLoaded();

        homePage.clickWriteLetter();
        homePage.writeLetter(loginMailinator+"@mailinator.com", subjectLetter, bodyLetter);
        homePage.sendLetter();

        Assert.assertTrue(homePage.getTextLetterIsSend("Ваш лист надіслано"));

        MailinatorInboxPage mailinatorInboxPage = new MailinatorInboxPage(driver);
        mailinatorInboxPage.navigatore();
        mailinatorInboxPage.goToInbox(loginMailinator);
        mailinatorInboxPage.clickLastLetter();

        MailinatorCheckLetter mailinatorCheckLetter = new MailinatorCheckLetter (driver);
        Assert.assertTrue(mailinatorCheckLetter.getSubjectReceivedEmail(subjectLetter));
        Assert.assertEquals(mailinatorCheckLetter.getFromReceivedEmail(),login);
        Assert.assertEquals(mailinatorCheckLetter.getBodyReceivedEmail(),bodyLetter);
    }
}


