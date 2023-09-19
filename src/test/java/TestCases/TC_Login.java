import org.apache.log4j.BasicConfigurator;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class TC_Login {
    WebDriver driver;

    @Before
    public void SetUp(){
        BasicConfigurator.configure();
        String driverExecutablePath = "C://Webdriver//chromedriver.exe";
        System.getProperty("web-driver.chrome.driver",driverExecutablePath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown(){
        driver.quit();
    }


//    public void login(String email, String password, String userInfo) throws IOException {
//        HomePage homePage = new HomePage(driver,"");
//        homePage.navigateTo(url);
//        //return login page
//        LoginPage loginPage = homePage.openSignInPage();
//        //login with email and pass
//        loginPage.login(email,password);
//        //check user info with the login info
//        loginPage.checkAccountInfoByText(userInfo);
//        loginPage.logOut();
//        homePage.closePage();
//    }


    @Test
    public void SuccessfulLoginAndLogOut() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login("hoho@gmail.com","123456");
        //check user info with the login info
        loginPage.checkAccountInfoByText("Ddf dsdf");
        loginPage.logOut();
        homePage.closePage();
    }

}
