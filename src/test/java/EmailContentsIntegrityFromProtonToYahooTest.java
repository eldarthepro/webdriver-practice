import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class EmailContentsIntegrityFromProtonToYahooTest {
    private final String messageBody = "What a nice day! This is a test message body!";
    private final String protonUsername = "testmailgor33";
    private YahooHomePage yahooHomePage;
    public WebDriver driver;

    @BeforeTest
    void setup()throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://account.proton.me/login");

        //send message from Protonmail
        AbstractPage.waitForElement(ProtonLoginPage.getUsernameFieldLocator(), driver);
        ProtonMessagePage messagePage = new ProtonLoginPage(driver).loginValidUser(protonUsername, "testmail123123").composeNewMessage();
        AbstractPage.waitForElement(ProtonMessagePage.getMessageWindowIframeLocator(), driver);
        messagePage.inputRecipientAddress("testmailgor33@yahoo.com");
        messagePage.inputMessageSubject("Hello there!");
        driver.switchTo().frame(driver.findElement(ProtonMessagePage.getMessageWindowIframeLocator()));
        messagePage.inputMessageBody(messageBody);
        driver.switchTo().defaultContent();
        messagePage.sendMessage();

        //wait until message arrives to Yahoo and login
        Thread.sleep(30000L);
        driver.get("https://login.yahoo.com/config/login_verify2?.intl=za&.src=ym");
        yahooHomePage = new YahooLoginPage(driver).loginValidUser("testmailgor33@yahoo.com", "testmail123123");
    }
    @Test
    public void messageSenderTest() {
        Assert.assertEquals(yahooHomePage.getFirstUnreadMessageSender(), protonUsername);
    }
    @Test
    public void messageUnreadTest() {
        Assert.assertTrue(yahooHomePage.getUnreadStatus());
    }
    @Test(dependsOnMethods = {"messageUnreadTest"})
    public void messageBodyIntegrityTest() {
        YahooMessagePage messagePage = yahooHomePage.readFirstMessage();
        Assert.assertEquals(messagePage.getMessageBody(), messageBody);
    }
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}


