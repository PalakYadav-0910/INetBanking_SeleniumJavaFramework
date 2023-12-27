package com.inetBanking.testCases;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class TC_LoginDDT_002 extends BaseClass
{
    @Test(dataProvider = "getData")
    public void loginDDT(String user, String pwd) throws IOException, InterruptedException {
         LoginPage lp = new LoginPage(driver);
         lp.setUsername(user);
         log.info("Username Provided.");

         lp.setPassword(pwd);
         log.info("Password Provided.");

         lp.clickSubmit();
         if(isAlertPresent()==true)
         {
             //driver.switchTo().alert();
             log.info(driver.switchTo().alert().getText());
             captureScreenshot(driver,"loginDDT");
             Thread.sleep(2000);
             driver.switchTo().alert().accept();
             driver.switchTo().defaultContent();
             Assert.assertTrue(false);
             log.warn("Login Failed.");
         }
         else
         {
             Assert.assertTrue(true);
             log.info("Login Passed.");

             lp.clickLogout();
             driver.switchTo().alert().accept();
             driver.switchTo().defaultContent();
         }
    }

    public boolean isAlertPresent()
    {
        boolean flag = false;
        try
        {
            Alert alert = driver.switchTo().alert();
            System.out.println(alert.getText());
            if(alert.getText().contains("User or Password is not valid")) {
                flag = true;
            }

            return flag;
        }
        catch(NoAlertPresentException e)
        {
            flag = false;
            return flag;
        }
    }
        @DataProvider(name = "getData")
        public Object [][] getData() throws IOException
        {
            String path = "C:\\Users\\palayadav\\Documents\\Selenium Webdriver-Java_Automation_Framework\\LoginData.xlsx";
            int rownum = XLUtils.getRowCount(path, "Sheet1");
            int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

            Object logindata[][] = new String[rownum][colcount];

            for (int i = 1; i <= rownum; i++)
            {
                for (int j = 0; j < colcount; j++)
                {
                    logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
                }

            }
            return logindata;
    }
}
