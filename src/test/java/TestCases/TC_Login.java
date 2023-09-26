//package TestCases;

//import Pages.HomePage;
//import Pages.LoginPage;
import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

public class TC_Login {
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

    public void login(String email, String password, String userInfo) throws IOException {
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        //return login page
        LoginPage loginPage = homePage.openSignInPage();
        //login with email and pass
        loginPage.login(email,password);
        //check user info with the login info
        loginPage.checkAccountInfoByText(userInfo);
        loginPage.logOut();
        homePage.closePage();
    }
    public void loginWithIncorrectCredentials(String email, String password) throws IOException {
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        //return login page
        LoginPage loginPage = homePage.openSignInPage();
        //login with email and pass
        loginPage.login(email,password);
        //check user info with the login info
        homePage.closePage();
    }


    @Test
    public void SuccessfulLoginAndLogOut() {
        String email = "test123@abv.bg";
        String pass = "123456";
        String userInfo = "Test Testing";
        try {
            login(email,pass,userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginWithWrongCredentials() {
        String email = "test123@abv.bg85";
        String password = "1234584512ouy";
        try {
            loginWithIncorrectCredentials(email,password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void forgottenPasswordWithNonExistingEmail(){
        String email = "s1234@gmail.com";
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        ForgottenPasswordPage forgottenPasswordPage = loginPage.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithInvalidEmail(email);
        forgottenPasswordPage.closePage();
    }

    //TODO: this test is failing - need to investigate
    @Test
    public void forgottenPasswordWithAnExistingEmail(){
        String email = "test123@abv.bg";
        HomePage homePage = new HomePage(driver,"");
        homePage.navigateTo(url);
        LoginPage loginPage = homePage.openSignInPage();
        ForgottenPasswordPage forgottenPasswordPage = loginPage.openForgottenPasswordPage();
        forgottenPasswordPage.RetrievePasswordWithValidEmail(email);
        forgottenPasswordPage.closePage();
    }



}
