import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ContactUsPage {
        private WebDriver driver;
        private WebDriverWait wait;

        By subjectHeading = By.xpath("//div[@id='uniform-id_contact']/select");
        By dropdownSubject = By.id("uniform-id_contact");
        By email = By.id("email");
        By orderId = By.id("id_order");
        By message = By.xpath("//div[@class='form-group']/textarea");
        By sendBtn = By.id("submitMessage");
        By messageSucceessSend = By.xpath("//div[@id='center_column']/p");
        String expectedMessage = "Your message has been successfully sent to our team.";

        public ContactUsPage(WebDriver dr){
            this.driver = dr;
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }

        public void contactUs(String contactEmail,String order,String userMessage,String subjectCategory){
            //select a dropdown
            WebElement subjectElement = driver.findElement(subjectHeading);
            Select subject = new Select(subjectElement);
            subjectElement.click();
            subject.selectByVisibleText(subjectCategory);

            //select the dropdown with action
//            Actions actions = new Actions(driver);
//            WebElement subjectDD = driver.findElement(dropdownSubject);
//            actions.click(subjectDD).perform();
//            WebElement subjectElement = driver.findElement(subjectHeading);
//            Select subject = new Select(subjectElement);
//            subject.selectByVisibleText(subjectCategory);

            wait.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(contactEmail);
            wait.until(ExpectedConditions.visibilityOfElementLocated(orderId)).sendKeys(order);
            wait.until(ExpectedConditions.visibilityOfElementLocated(message)).sendKeys(userMessage);
            wait.until(ExpectedConditions.visibilityOfElementLocated(sendBtn)).click();

            WebElement alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(messageSucceessSend));
            Assert.assertEquals(expectedMessage, alertMsg.getText());

        }

        public void closePage(){
            driver.close();
        }

}
