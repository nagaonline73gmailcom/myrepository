package com.k2js.hybridframework.hybridframework;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;

import com.k2js.hybridframework.hybridframework.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentTest;

public class Keywords {
	ExtentTest test;
	public appkeywords gn;
	public int p;
	
	public Keywords(ExtentTest test)
	{
		this.test=test;
	}
	
	public void executekeywords(String testname,Xls_Reader xls, Hashtable<String, String> data2) throws IOException, InterruptedException {
		 gn=new appkeywords(test);
		 p=3;
		/*gn.getBrowser("firefox", "local");
		gn.launchApp("url");*/
		int rowcount=xls.getRowCount("sheet1");
		for(int rnum=2;rnum<=rowcount;rnum++)
		{
			String tcid=xls.getCellData("sheet1", "tcid", rnum);
			if(tcid.equalsIgnoreCase(testname))
			{
			System.out.println(tcid);
			String keyword=xls.getCellData("sheet1", "keyword",rnum);
			System.out.println(keyword);
			String object=xls.getCellData("sheet1", "object",rnum);
			String key=xls.getCellData("sheet1", "data",rnum);
			String data=data2.get(key);
			System.out.println(data);
			String result="";
			if(keyword.equalsIgnoreCase("getbrowser"))
			{
			result=	gn.getBrowser(data, "local");
			}
			if(keyword.equalsIgnoreCase("launchapp"))
			{
			result=	gn.launchApp(data);
			}
			if(keyword.equalsIgnoreCase("input"))
			{
				result=gn.input(object, data);
			}
			
			if(keyword.equalsIgnoreCase("click"))
			{
				result=gn.click(object);
			}
			
			if(keyword.equalsIgnoreCase("flipKartLogin"))
			{
				result=gn.flipKartLogin(data2);
			}
			if(keyword.equalsIgnoreCase("sleep"))
			{
				result=gn.sleep();
			}
			if(keyword.equalsIgnoreCase("clickOneElement"))
			{
				result=gn.clickOneElement(object);
			}
			if(!result.equals("pass"))
			{
				gn.reportFailure(result);
				Assert.fail(result);
			}
		}
		}
		
	}
	
	
	
	public appkeywords getGenericKeywords()
	{
		return gn;
	}

}
