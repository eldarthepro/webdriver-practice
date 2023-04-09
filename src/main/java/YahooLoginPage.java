import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class YahooLoginPage extends CommonMethods{
    protected final WebDriver driver;
    private static final By usernameField = By.xpath("//input[@id=\"login-username\"]");
    private static final By passwordField = By.xpath("//*[@id=\"login-passwd\"]");
    private static final By signInButton = By.xpath("//*[@id=\"login-signin\"]");
    private static final By wrongUsernameMessage = By.xpath("//*[@id=\"username-error\"]");
    private static final By wrongPasswordMessage = By.xpath("//*[@data-error=\"messages.ERROR_INVALID_PASSWORD\"]");


    public static By getWrongUsernameMessage() {
        return wrongUsernameMessage;
    }

    public static By getWrongPasswordMessage() {
        return wrongPasswordMessage;
    }

    public YahooLoginPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getTitle().equals("Yahoo")) {
            throw new IllegalStateException("This is not a Sign In Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }
    //Login page has only one input field at a time
    public YahooHomePage loginValidUser(String username, String password){
        submitUsername(username);
        waitForElement(passwordField, driver);
        submitPassword(password);
        waitForElement(YahooHomePage.getYahooHomePageLogo(), driver);
        return new YahooHomePage(driver);
    }

    public YahooLoginPage loginInvalidPassword(String username, String wrongPassword) {
        submitUsername (username);
        if (elementDisplayed (wrongUsernameMessage)) {
            throw new IllegalStateException ("Username was incorrect or not found, current page is: " + driver.getCurrentUrl ());
        } else
            waitForElement (passwordField, driver);
        submitPassword (wrongPassword);
        waitForElement (wrongPasswordMessage, driver);
        if (elementDisplayed (wrongPasswordMessage)) {
            return this;
        } else
            throw new IllegalStateException ("Password was accepted, or captcha triggered, current page is: " + driver.getCurrentUrl ());
    }
    public YahooLoginPage loginInvalidUserName(String wrongUsername, String wrongPassord) {
        submitUsername(wrongUsername);
        clickSignInButton(signInButton, driver);
        waitForElement(wrongUsernameMessage, driver);
        if (elementDisplayed(wrongUsernameMessage)) {
            return this;
        } else
            throw new IllegalStateException("Username was correct or found, current page is: " + driver.getCurrentUrl());
    }
    public void submitUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
        clickSignInButton(signInButton, driver);
    }
    public void submitPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
        clickSignInButton(signInButton, driver);
    }
    public boolean elementDisplayed(By by){
        return driver.findElement(by).isDisplayed();
    }
}
