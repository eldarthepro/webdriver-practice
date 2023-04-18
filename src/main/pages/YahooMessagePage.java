import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YahooMessagePage {
    protected final WebDriver driver;
    private static final By messageField = By.cssSelector("div[style*='sans-serif;font-size:14px;color:rgb(0, 0, 0);'] ");
    private static final By senderIcon = By.xpath("//*[@class=\"e_e5X\"]");
    public YahooMessagePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.findElement(senderIcon).isDisplayed()) {
            throw new IllegalStateException("This is not a Message reading page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
    public String getMessageBody(){
        return driver.findElement(messageField).getText();
    }

    public static By getSenderIconLocator(){
        return senderIcon;
    }
}
