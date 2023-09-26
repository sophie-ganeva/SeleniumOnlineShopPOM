import AutoFramework.MainTestSetUp;
import AutoFramework.Utilities.Log;
import AutoFramework.Utilities.Screenshot;
import org.junit.*;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LoginDDT extends MainTestSetUp {
    @Before
    public void SetUp() {
        try {
            this.mainSetUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        this.mainTestTearDown();
    }

    @Ignore
    @Test
    public void forTheTest() {
        System.out.println(this.getUsername());
    }

    /* this test should pass as the email to login is valid
    check the log in logfile
     */
    @Test
    public void simpleLogin() throws IOException{
        try{
            Log.startTestDetails(this.getClass().getSimpleName());
            Log.info("Open new page");
            HomePage homePage = new HomePage(driver, this.getUsername());
            homePage.navigateTo(this.getMainURL());
            LoginPage loginPage = homePage.openSignInPage();
            Log.info("Login with " + this.getUsername());
            loginPage.login(this.getUsername(), this.getPassword());
            loginPage.checkAccountInfoByText(this.getGetUsernameLoggedInfo());
            loginPage.logOut();
            homePage.closePage();
            Log.endTestDetails(this.getClass().getSimpleName());
        }catch(IllegalArgumentException e){
            //if email or password is null
            Log.error(e.getMessage());
        }
    }

    /**************************************************************************************************
     Test should fail as the login email is null
     Check the log in logfile to see the error
     Make a screenshot
     **************************************************************************************************/
    /* this test should fail as the email to login is null
        check the log in logfile also to see the error
    */
    @Test
    public void negativeLoginTest() throws IOException {
        try{
            Log.startTestDetails(this.getClass().getSimpleName());
            Log.info("Open new page");
            HomePage homePage = new HomePage(driver, this.getUsername());
            homePage.navigateTo(this.getMainURL());
            LoginPage loginPage = homePage.openSignInPage();
            Log.info("Login with " + this.getUsername());
            loginPage.login(null, this.getPassword());
            loginPage.checkAccountInfoByText(this.getGetUsernameLoggedInfo());
            loginPage.logOut();
            homePage.closePage();
            Log.endTestDetails(this.getClass().getSimpleName());
        }catch(RuntimeException | IOException e){
            //if email or password is null
            Log.error(e.getMessage());
            Screenshot.takePageScreenshot(this.driver,"LoginFail");
            throw e;
        }
    }

    /**************************************************************************************************
     Test should pass as the login email is valid
     Check the log in logfile
     Make a screenshot
     Possible to compare 2 screenshots
     **************************************************************************************************/
    @Test
    public void simpleLoginWithScreenshots() throws IOException{
        try{
            Log.startTestDetails(this.getClass().getSimpleName());
            Log.info("Open new page");
            HomePage homePage = new HomePage(driver, this.getUsername());
            homePage.navigateTo(this.getMainURL());
            LoginPage loginPage = homePage.openSignInPage();

            //make a single screenshot
            Screenshot.takePageScreenshot(this.driver,"LoginPass");
            //compare images
//            BufferedImage image1 = Screenshot.takePageScreenshot(this.driver,"Login");
//            BufferedImage imageOut = ImageIO.read(new File("C:\\Webdriver\\screenshots\\testImg.png"));
//            Screenshot.compareImages(image1,imageOut);

            Log.info("Login with " + this.getUsername());
            loginPage.login(this.getUsername(), this.getPassword());
            loginPage.checkAccountInfoByText(this.getGetUsernameLoggedInfo());
            loginPage.logOut();
            homePage.closePage();
            Log.endTestDetails(this.getClass().getSimpleName());
        }catch(IllegalArgumentException e){
            //if email or password is null
            Log.error(e.getMessage());
        }
    }


}
