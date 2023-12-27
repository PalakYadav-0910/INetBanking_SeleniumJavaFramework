package com.inetBanking.testCases;
import com.inetBanking.utilities.ReadConfig;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.apache.logging.log4j.*;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

import static org.openqa.selenium.UnexpectedAlertBehaviour.ACCEPT;

public class BaseClass
{
    ReadConfig readConfig = new ReadConfig();
    public String baseURL = readConfig.getApplicationURL();
    public String username = readConfig.getUsername();
    public String password = readConfig.getPassword();
    public static WebDriver driver;

    public static Logger log;

    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("chrome") String br)
    {
        log = LogManager.getLogger("inetBankingv1");

       //System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//Drivers//chromedriver.exe");

        if(br.equals("chrome"))
        {
            System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
            ChromeOptions options = new ChromeOptions();
            options.setUnhandledPromptBehaviour(ACCEPT);
            driver = new ChromeDriver(options);
        }
        else if(br.equals("firefox"))
        {
            System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
            driver = new FirefoxDriver();
        }
        else
        {
            System.setProperty("webdriver.edge.driver", readConfig.getEdgePath());
            driver = new EdgeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(baseURL);
    }

    @AfterClass
    public void tearDown()
    {
       driver.quit();
    }

    public void  captureScreenshot(WebDriver driver, String testName) throws IOException
    {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File( System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");
        FileUtils.copyFile(src,dest);
    }
    public String randomeString()
    {
        UUID randomUUID = UUID.randomUUID();
        String generatedString = randomUUID.toString();
        return generatedString;
    }

}
