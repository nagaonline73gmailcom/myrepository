package com.k2js.hybridframework.hybridframework;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

public class appkeywords extends GenericKeywords {
	
	
	public appkeywords(ExtentTest test)
	{
		super(test);
	}
	
	public String flipKartLogin(Hashtable<String,String> data)
	{
		return Login(data.get("username"),data.get("password"));
		
	}
	
	public String Login(String username,String password)
	{
		super.getElemement("username_xpath").sendKeys(username);
		super.getElemement("password_xpath").sendKeys(password);
		super.getElemement("loginbutton_xpath").click();
		
		return "pass";
	}
	
	public void getWindowHandles()
	{
		Set<String> s=super.driver.getWindowHandles();
		String mainWindow="";
		String otherWindow="";
		System.out.println(s.size());
		
		Iterator<String> p=s.iterator();
		
		while(p.hasNext())
		{
			mainWindow=p.next();
			otherWindow=p.next();
		}
		driver.switchTo().window(otherWindow);
		
		
	}
	
	public String clickOneElement(String Locatorkey) throws InterruptedException
	{
		List<WebElement> e=getElements(Locatorkey);
		System.out.println("total number of elements "+e.size());
		e.get(1).click();
		Thread.sleep(4000);
		getWindowHandles();
		
		
		
		return "pass";
	}

}
