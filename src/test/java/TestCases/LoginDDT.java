import org.apache.log4j.BasicConfigurator;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.io.IOException;

public class LoginDDT extends MainTestSetUp{
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

    @Ignore
    @Test
    public void forTheTest(){
        System.out.println(this.getUsername());
    }

    @Test
    public void simpleLogin() {
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(this.getUsername(),this.getPassword());
        loginPage.checkAccountInfoByText(this.getGetUsernameLoggedInfo());
        loginPage.logOut();
        homePage.closePage();
    }
}
