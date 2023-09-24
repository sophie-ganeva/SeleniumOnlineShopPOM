package AutoFramework.Utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Screenshot {
    private static final String FOLDER_PATH = "C:\\Webdriver\\screenshots\\";

    /**************************************************************************************************
     Take a screenshot of the page or log the error
     Write screenshot in a file
     **************************************************************************************************/
    public static BufferedImage takePageScreenshot(WebDriver dr, String screenshotName) throws IOException {
        File screenshot = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenshot,new File(FOLDER_PATH + getFileNameWithCurrentDate(screenshotName)) );
        }catch(IOException e){
            Log.error(e.getMessage());
            throw e;
        }
        BufferedImage image = ImageIO.read(screenshot);
        return image;
    }
    /**************************************************************************************************
     Take a screenshot of an element or log the error
     **************************************************************************************************/
    public static BufferedImage takeWebElementScreenShot(WebElement el, String elementName) throws IOException {
        File file = el.getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(file,new File(FOLDER_PATH + getFileNameWithCurrentDate(elementName)) );
        }catch(IOException e){
            Log.error(e.getMessage());
            throw e;
        }
        BufferedImage image = ImageIO.read(file);
        return image;
    }

    public static boolean compareImages(BufferedImage image, BufferedImage imageOut){
        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(image,imageOut);
        if(diff.hasDiff()==true){
            System.out.println("Images are Not the Same");
            return false;
        }
        System.out.println("Images are the Same");
        return true;
    }

    private static String getFileNameWithCurrentDate(String step){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String fileName = step + "_" +formatter.format(date).toString()+".png";
        return fileName;
    }
}
