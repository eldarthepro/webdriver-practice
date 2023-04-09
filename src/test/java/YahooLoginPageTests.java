import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class YahooLoginPageTests {
    public WebDriver driver;
    @BeforeMethod
    void beforeMethod(){
        driver = new ChromeDriver();
        driver.get("https://login.yahoo.com/config/login_verify2?.intl=za&.src=ym");
    }
    @AfterMethod
    void afterMethod(){
        driver.quit();
    }
    @Test(dataProvider = "validLoginData", priority = 2)
    public void yahooLoginValidUserTest(String username, String password){
        Assert.assertTrue((new YahooLoginPage(driver).loginValidUser(username, password)).driver.getTitle().contains(username));
    }
    @Test(dataProvider = "invalidUsernameData", priority = 2)
    public void  loginInvalidUserNameTest(String wrongUsername, String wrongPassword){
        Assert.assertTrue(new YahooLoginPage(driver).loginInvalidUserName(wrongUsername, wrongPassword).driver.findElement(YahooLoginPage.getWrongUsernameMessage()).isDisplayed());
    }
    @Test(dataProvider = "invalidPasswordData", priority = 2)
    public void  loginInvalidPasswordTest(String username, String wrongPassword){
        Assert.assertTrue(new YahooLoginPage(driver).loginInvalidPassword(username, wrongPassword).driver.findElement(YahooLoginPage.getWrongPasswordMessage()).isDisplayed());
    }
    @DataProvider (name = "validLoginData")
    private String[][] getCorrectLoginData(){
       return new String[][]{{"testmailgor33@yahoo.com", "testmail123123"}};
    }
    @DataProvider (name = "invalidUsernameData")
    private String[][] getIncorrectCorrectUsernameData(){
        return new String[][]{{"", ""},{"thisistestmail7798@yahoo.com", "wrongpass"}};
    }
    @DataProvider (name = "invalidPasswordData")
    private String[][] getIncorrectCorrectPasswordData(){
        return new String[][]{{"testmailgor33@yahoo.com", "what a day!"}};
    }
}
