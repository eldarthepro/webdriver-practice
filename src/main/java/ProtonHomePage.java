import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProtonHomePage extends CommonMethods{
    protected final WebDriver driver;
    private static final By newMessageButton = By.xpath("//*[@data-testid=\"sidebar:compose\"]");

    public ProtonHomePage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().contains("@protonmail")) {
            throw new IllegalStateException("This is not a Proton Mail Home Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
    public ProtonMessagePage composeNewMessage(){
        driver.findElement(newMessageButton).click();
        waitForElement(ProtonMessagePage.getSendMessageButtonLocator(), driver);
        return new ProtonMessagePage(driver);
    }
    public static By getNewMessageButtonLocator(){
        return newMessageButton;
    }
}
