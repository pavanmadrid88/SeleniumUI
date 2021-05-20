package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties properties;

    public BaseClass() throws IOException {

        //read data from properties file
        properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\pavan.vasu\\pavan\\projects\\maven\\Selenium\\worldometer\\src\\main\\resources\\testData\\config.properties");
        properties.load(fileInputStream);



    }

    //Init
    public static void initialize(){

        String browserName = properties.getProperty("browserName");

        switch (browserName.trim().toUpperCase()) {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                System.out.println("invalid browser name!");
                return;

        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(properties.getProperty("implicitWait")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(properties.getProperty("pageLoadTimeout")), TimeUnit.SECONDS);

        driver.get(properties.getProperty("url"));

    }





}
