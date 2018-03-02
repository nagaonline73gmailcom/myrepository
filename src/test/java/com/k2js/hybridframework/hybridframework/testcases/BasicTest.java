package com.k2js.hybridframework.hybridframework.testcases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.k2js.hybridframework.hybridframework.util.DataUtil;
import com.k2js.hybridframework.hybridframework.util.ExtentManager;
import com.k2js.hybridframework.hybridframework.util.Xls_Reader;
import com.k2js.hybridframework.hybridframework.Keywords;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasicTest extends BaseTest{


	
	@BeforeTest
	public void init()
	{
		System.out.println("nagarjuna");
		testname = "logintest";
		xls = new Xls_Reader(".//src//test//resources//suitea.xlsx");
		
	}

	@Test(dataProvider = "getData")
	public void doLogin(Hashtable<String, String> data) throws IOException, InterruptedException {
		test = rep.startTest("Addtocarttest");
		if (data.get("runmode").equalsIgnoreCase("n")) {
			test.log(LogStatus.SKIP, "skipping the test");
			throw new SkipException("skipping the test as run mode is n");

		}
		 key = new Keywords(test);
		test.log(LogStatus.INFO, "starting the login test");
		System.out.println(key.p);
		key.executekeywords(testname, xls,data);

		System.out.println(key.p);
		key.getGenericKeywords().reportFailure("error");
		
	}
	
	

	

}
