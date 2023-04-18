import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YahooHomePage extends AbstractPage {
    protected final WebDriver driver;
    private static final By unreadMessageIcon = By.xpath("//*[@data-test-id=\"icon-btn-read\"]");
    private static final By firstUnreadMessageSender = By.xpath("//strong[@class=\"u_Z13VSE6\"]");
    private static final By messageTextPreview = By.xpath("//*[@data-test-id=\"snippet\"]");
    private static final By yahooHomePageLogo = By.xpath("//*[@id=\"ybar-logo\"]/img[2]");
    public YahooHomePage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().contains("- Yahoo Mail")) {
            throw new IllegalStateException("This is not a Mail Home Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
    public boolean getUnreadStatus() {
        return driver.findElement(unreadMessageIcon).isDisplayed();
    }
    public YahooMessagePage readFirstMessage(){
        driver.findElement(messageTextPreview).click();
        waitForElement(YahooMessagePage.getSenderIconLocator(), driver);
        return new YahooMessagePage(driver);
    }
    public String getFirstUnreadMessageSender(){
        return driver.findElement(firstUnreadMessageSender).getText();
    }
    public static By getYahooHomePageLogo(){
        return yahooHomePageLogo;
    }
}
