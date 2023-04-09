import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProtonLoginPage extends CommonMethods{
    protected final WebDriver driver;
    private static final By usernameField = By.xpath("//*[@id=\"username\"]");
    private static final By passwordField = By.xpath("//*[@id=\"password\"]");
    private static final By signInButton = By.xpath("//*[@type=\"submit\"]");

    public ProtonLoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().equals("https://account.proton.me/login")) {
            throw new IllegalStateException("This is not a Proton Sign In Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
    public ProtonHomePage loginValidUser(String username, String password){
        inputUsername(username);
        inputPassword(password);
        clickSignInButton(signInButton, driver);
        waitForElement(ProtonHomePage.getNewMessageButtonLocator(), driver);
        return new ProtonHomePage(driver);
    }
    private void inputUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }
    private void inputPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public static By getUsernameFieldLocator(){
        return usernameField;
    }
}
