import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;
    String email;
    String url = "http://www.automationpractice.pl/index.php?";
    By sighInBtn = By.className("login");
    By contactUsBtn = By.xpath("//div[@id='contact-link']/a[@title='Contact us']");

    //constructor; must have driver and wait;
    public HomePage(WebDriver dr,String email){
        this.driver = dr;
        wait = new WebDriverWait(dr, Duration.ofSeconds(10));
        this.email = email;
    }

    public void navigateTo(String url){
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public LoginPage openSignInPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(sighInBtn)).click();
        return new LoginPage(driver,email);

    }
    public ContactUsPage openContactUsPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(contactUsBtn)).click();
        return new ContactUsPage(driver);
    }

    public void closePage(){
        driver.close();
    }
}
