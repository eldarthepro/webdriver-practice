import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProtonMessagePage extends AbstractPage{
    protected final WebDriver driver;
    private static final By sendMessageButton = By.cssSelector(".button.composer-send-button");
    private static final By subjectField = By.cssSelector("div[class*='flex-item-fluid'] input[data-testid='composer:subject']");
    private static final By recipientField = By.cssSelector("div[class*='composer-addresses-autocomplete'] input.field");
    private static final By messageBodyField = By.xpath("//*[@id=\"rooster-editor\"]");
    private static final By messageSentNotification = By.xpath("//*[@class=\"notification__content\"]");
    private static final By messageWindowIframe = By.xpath("//*[contains(@data-testid, 'rooster-iframe')]");


    public ProtonMessagePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.findElement(sendMessageButton).isDisplayed()) {
            throw new IllegalStateException("This is not a Message composing page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
    public void inputMessageSubject(String messageSubject){
        driver.findElement(subjectField).sendKeys(messageSubject);
    }
    public void inputRecipientAddress(String recipientAddress){
        driver.findElement(recipientField).sendKeys(recipientAddress);
    }
    public void inputMessageBody(String messageBody){
        driver.findElement(messageBodyField).clear();
        driver.findElement(messageBodyField).sendKeys(messageBody);
    }
    public void sendMessage(){
        driver.findElement(sendMessageButton).click();
        waitForElement(messageSentNotification, driver);
    }
    public static By getSendMessageButtonLocator(){
        return sendMessageButton;
    }
    public static By getMessageWindowIframeLocator(){
        return messageWindowIframe;
    }
}
