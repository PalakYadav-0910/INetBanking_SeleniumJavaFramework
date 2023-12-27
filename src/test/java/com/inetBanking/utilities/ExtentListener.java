package com.inetBanking.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetBanking.testCases.BaseClass;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import static com.inetBanking.testCases.BaseClass.driver;

public class ExtentListener implements ITestListener
{
    ExtentSparkReporter htmlReporter;
    ExtentReports reports;
    ExtentTest test;

    @Parameters("browser")
    public void configureReport()
    {
        String timestamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
        String repName = "Test-Report- " + timestamp + ".html";

        htmlReporter = new ExtentSparkReporter("./Reports/" + repName);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);

        //Add System Information/Environment Info To Reports
        reports.setSystemInfo("Machine", "testpc1");
        reports.setSystemInfo("OS", "Windows 11");
        reports.setSystemInfo("browser", "browser");
        reports.setSystemInfo("user name", "Palak");

        //Configuration to change look and feel of Report
        htmlReporter.config().setDocumentTitle("InetBankingV1_Report");
        htmlReporter.config().setReportName("This is my First Report");
        htmlReporter.config().setTheme(Theme.DARK);
    }

    @Override
    public void onStart(ITestContext context)
    {
        configureReport();
        System.out.println("On Start Method Invoked...");
    }

    @Override
    public void onTestSuccess(ITestResult result)
    {
        System.out.println("Name of test method successfully executed:" + result.getName());
        test = reports.createTest(result.getName());
        test.log(Status.PASS, MarkupHelper.createLabel("Name of the passed test case is : " + result.getName(), ExtentColor.GREEN));
    }

    @Override
    public void onFinish(ITestContext context)
    {
        System.out.println("On Finished Method Invoked...");
        reports.flush();
    }

    @Override
    public void onTestStart(ITestResult result)
    {
        System.out.println("Name of test method started:" + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        System.out.println("Name of test method passed:" + result.getName());
        test = reports.createTest(result.getName());
        test.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is : " + result.getName(), ExtentColor.RED));
        test.log(Status.FAIL, "Test Case Failed due to: " + result.getThrowable());

        String screenShotPath = System.getProperty("user.dir") + "//ScreenShots//" + result.getName() + ".png";

        File screenShotFile = new File(screenShotPath);

        if(screenShotFile.exists())
        {
            byte[] fileContent = new byte[0];
            try {
                fileContent = FileUtils.readFileToByteArray(new File(String.valueOf(screenShotFile)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            test.addScreenCaptureFromBase64String(encodedString);
        }

}

    @Override
    public void onTestSkipped(ITestResult result)
    {
        System.out.println("Name of test method skipped:" + result.getName());
        test = reports.createTest(result.getName());
        test.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped test case is : " + result.getName(), ExtentColor.YELLOW));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result)
    {
        ITestListener.super.onTestFailedWithTimeout(result);
    }
}
