import AutoFramework.MainTestSetUp;
import org.junit.*;

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


    /* in order to register a new user go to excel file "UserAccounts" and choose an email
        then go to excel file "Credentials" and change the email for RegistrationDDT
     */
    @Test
    public void registrationTest() throws InterruptedException, IOException {
        // proverka na ве4е registriran email ?????????????????????????
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        RegistrationPage registrationPage = loginPage.createAccount(this.getUsername());
        registrationPage.register();


    }
}
