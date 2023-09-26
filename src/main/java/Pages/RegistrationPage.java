import AutoFramework.Utilities.Log;
import AutoFramework.Utilities.ReadFromXML;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;
    String email;
    private final String personXmlPath = "C:\\Webdriver\\inits\\UserAccounts.xlsx";

    By customer_firstname = By.id("customer_firstname");
    By customer_lastname = By.id("customer_lastname");
    By passwd = By.id("passwd");
    By days = By.id("days");
    By dropdown_days = By.id("uniform-days");
    By months = By.id("months");
    By dropdown_months = By.id("uniform-months");
    By years = By.id("years");
    By dropdown_years = By.id("uniform-years");
    By newsletterBox = By.id("uniform-newsletter");
    By offersBox = By.id("optin");
    By address1 = By.id("address1");
    By city = By.id("city");
    By id_state = By.id("id_state");
    By dropdown_id_state = By.id("uniform-id_state");
    By postcode = By.id("postcode");
    By phone_mobile = By.id("phone_mobile");
    By alias = By.id("alias");
    By RegisterBtn = By.id("submitAccount");


    public RegistrationPage(WebDriver dr, String email){
        this.driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.email = email;
    }

    /* fill out only mandatory fields for registration */
    public void registerWithDataFromFile() throws InterruptedException {
        List<String> details = new ArrayList<>();
        try {
            details = ReadFromXML.readFromXmlFile(personXmlPath,email);
        } catch (IOException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(customer_firstname)).sendKeys(details.get(0));
        Thread.sleep(2000L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(customer_lastname)).sendKeys(details.get(1));
        Thread.sleep(2000L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwd)).sendKeys(details.get(2));
//        Thread.sleep(2000L);
//        String myDay = details.get(3);
//        String myMonth = details.get(4);
//        String myYear = details.get(5);
//        Birthdate(myDay,myMonth,myYear);

        WebElement checkBox = driver.findElement(newsletterBox);
        if(!checkBox.isSelected()){
            checkBox.click();
        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(address1)).sendKeys(details.get(7));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(city)).sendKeys(details.get(8));
        //select the state
//        WebElement dropdownState = driver.findElement(dropdown_id_state);
//        dropdownState.click();
//        Select state = new Select(driver.findElement(id_state));
//        state.selectByVisibleText(details.get(9));
//        Thread.sleep(2000L);
//
//        //format the postcode and phone number fields in the excel file as TEXT !!!
//        wait.until(ExpectedConditions.visibilityOfElementLocated(postcode)).sendKeys(details.get(10));
//        Thread.sleep(2000L);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(phone_mobile)).sendKeys(details.get(11));
//        Thread.sleep(2000L);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(alias)).sendKeys(details.get(12));
//        Thread.sleep(2000L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RegisterBtn)).click();
    }

    private void Birthdate(String myDay, String myMonth, String myYear) throws InterruptedException {
        Select day = new Select(driver.findElement(days));
        Select month = new Select(driver.findElement(months));
        Select year = new Select(driver.findElement(years));
        driver.findElement(days).click();
        Thread.sleep(2000L);
        System.out.println(myDay);
        day.selectByValue(myDay);

        driver.findElement(months).click();
        Thread.sleep(2000L);
        System.out.println(myMonth);
        month.selectByValue(myMonth);

        driver.findElement(years).click();
        Thread.sleep(2000L);
        System.out.println(myYear);
        year.selectByValue(myYear);
        Thread.sleep(2000L);
    }

    public void register(String firstName, String lastName,String email, String password ) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(customer_firstname)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(customer_lastname)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwd)).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(RegisterBtn)).click();

    }
    public void submitRegistrationForm(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(RegisterBtn)).click();
        System.out.println("Registration of an user passed");
    }


}
