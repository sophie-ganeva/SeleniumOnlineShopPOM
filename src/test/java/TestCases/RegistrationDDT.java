import AutoFramework.MainTestSetUp;
import org.junit.*;
import org.openqa.selenium.By;

import java.io.IOException;

public class RegistrationDDT extends MainTestSetUp {

    @Before
    public void SetUp()  {
        try {
            this.mainSetUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        this.mainTestTearDown();
    }

    /**************************************************************************************************
     In order to register a new user go to excel file "UserAccounts" and choose an email
     then go to excel file "Credentials" and change the email for RegistrationDDT
     **************************************************************************************************/
    @Test
    public void registrationNewUser() throws InterruptedException, IOException {
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        RegistrationPage registrationPage = loginPage.createAccount(this.getUsername());
        registrationPage.registerWithDataFromFile();
    }

    /**************************************************************************************************
     Check the error message if the user is already registered
     **************************************************************************************************/
    @Test
    public void registrationExistingUser() throws InterruptedException, IOException {
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        RegistrationPage registrationPage = loginPage.createAccount(this.getUsername());
        String expectedMessage = "An account using this email address has already been registered. Please enter a valid password or request a new one.";
        loginPage.checkExistingUser(expectedMessage);
    }
    
}
