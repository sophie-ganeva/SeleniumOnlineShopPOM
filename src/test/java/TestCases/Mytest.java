import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Mytest {
    By sighInBtn = By.className("login");
    By loginForm = By.id("login_form");
    By emailField = By.id("email");
    By passwordField = By.id("passwd");
    By submitBtn = By.id("SubmitLogin");
    By logOutBtn = By.xpath("//div[@class='header_user_info']/a[@class='logout']");

    WebDriver driver;

    @Before
    public void SetUp(){
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "C://Webdriver//chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("http://www.automationpractice.pl/index.php?");
    }
    @Test
    public void SuccessfulLoginAndLogOut() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sighInBtn)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys("hoho@gmail.com");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys("123456");
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
        System.out.println("Login passed");


        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
    }
}
