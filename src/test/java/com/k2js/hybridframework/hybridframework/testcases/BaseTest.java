package com.k2js.hybridframework.hybridframework.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;

import com.k2js.hybridframework.hybridframework.Keywords;
import com.k2js.hybridframework.hybridframework.util.DataUtil;
import com.k2js.hybridframework.hybridframework.util.ExtentManager;
import com.k2js.hybridframework.hybridframework.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseTest {
	
	public ExtentReports rep = ExtentManager.getInstance();
	public String testname;
	public ExtentTest test;
	public Xls_Reader xls = null;
	public Keywords key=null;

	
	@DataProvider
	public Object[][] getData() {
		return DataUtil.getdata(xls, testname);
	}
		
		@AfterTest
		public void quit()
		{
			rep.endTest(test);
			rep.flush();
			if(key!=null)
			{
				key.getGenericKeywords().closeBrowser();
			}
		}

	
	
}
