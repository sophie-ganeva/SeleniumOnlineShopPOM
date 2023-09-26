import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class TC_Register {
    WebDriver driver;
    String url = "http://www.automationpractice.pl/index.php";

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

    public void register(String firstName, String lastName,String email, String password) {
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(this.url);
        LoginPage loginPage = homePage.openSignInPage();
        RegistrationPage registrationPage = loginPage.createAccount(email);
        registrationPage.register(firstName,lastName,email,password);
    }

    @Test
    public void SuccessfulRegistration() {
        String email = "rere@abv.bg";
        String pass = "123456";
        String firstName = "Ivan Ivanov";
        String lastName = "Ivan Ivanov";
        register(firstName,lastName,email,pass);

    }
}
