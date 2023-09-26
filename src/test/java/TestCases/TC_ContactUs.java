import org.apache.log4j.BasicConfigurator;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class TC_ContactUs {
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


    @Test
    public void SendUsMessageTest(){
        String contactEmail = "s2@gmail.com";
        String orderId = "15894";
        String userMessage = "This is a test. I will send a test to the custom services.";
        String subject = "Customer service";
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        ContactUsPage contactPage = homePage.openContactUsPage();
        contactPage.contactUs(contactEmail,orderId,userMessage,subject);
        contactPage.closePage();
    }
}
