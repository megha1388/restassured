package com.example.restassured.test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;

public class TestReportConfig {
    public static ExtentReports reports;
    ExtentTest test;

    public static ExtentReports getReportInstance() {
        if(reports==null){
            reports = new ExtentReports("extentReports.html",true);
            reports.loadConfig(new File("extents-config.xml"));
        }
        return reports;
    }


    @BeforeMethod
    public void beforeMethod(Method method) {
        test = getReportInstance().startTest(this.getClass().getName()+"::"+method.getName());
        test.log(LogStatus.PASS,"test passed");
    }

    @AfterMethod
    public void afterMethod() {
        reports.endTest(test);
    }

    @AfterSuite
    public void afterSuite(){
        reports.flush();
        reports.close();
    }

}

