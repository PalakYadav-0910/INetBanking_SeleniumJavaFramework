package com.inetBanking.testCases;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;
import net.bytebuddy.utility.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.UUID;

public class TC_AddCustomerTest_003 extends BaseClass
{
    @Test
    public void addNewCustomer() throws InterruptedException, IOException {
        LoginPage lp = new LoginPage(driver);
        lp.setUsername(username);
        log.info("Username is Provided.");

        lp.setPassword(password);
        log.info("Password is Provided.");
        lp.clickSubmit();

        Thread.sleep(2000);

        AddCustomerPage addcust = new AddCustomerPage(driver);
        addcust.clickAddNewCustomer();
        log.info("Providing Customer Details....");

        addcust.custName("Palak");
        addcust.custGender("Female");
        addcust.custdob("10","09","1999");

        Thread.sleep(2000);

        addcust.custAddress("INDIA");
        addcust.custcity("HYD");
        addcust.custState("AP");
        addcust.custPinno("5000074");
        addcust.custtelephoneno("987654321");

        String email = randomeString()+ "@gmail.com";
        addcust.custEmailid(email);
        addcust.custPassword("abcdef");
        addcust.custSubmit();

        Thread.sleep(2000);

        log.info("Validation Started....");

        boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");

        if(res==true)
        {
            Assert.assertTrue(true);
            log.info("Test Case Passed....");
        }
        else
        {
            log.info("Test Case Failed....");
            captureScreenshot(driver,"addNewCustomer");
            Thread.sleep(2000);
            Assert.assertTrue(false);
        }
    }

}
