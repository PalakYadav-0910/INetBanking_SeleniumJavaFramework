package com.inetBanking.testCases;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.testCases.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC_LoginTest_001 extends BaseClass
{
    @Test
    public void loginTest() throws IOException {
       log.info("URL is Opened.");

       LoginPage lp = new LoginPage(driver);
       lp.setUsername(username);
       log.info("Entered Username");

       lp.setPassword(password);
       log.info("Entered Password");

       lp.clickSubmit();

       if(driver.getTitle().equals("Guru Bank Manager HomePage"))
       {
           Assert.assertTrue(true);
           log.info("Login Test Passed.");
       }
       else
       {
           captureScreenshot(driver,"loginTest");
           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
           Assert.assertTrue(false);
           log.info("Login Test Failed.");
       }
    }
}
