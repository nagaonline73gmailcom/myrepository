package com.k2js.hybridframework.hybridframework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class GenericKeywords {
	
	public WebDriver driver = null;
	public Properties prop=null;
	ExtentTest test;
	
	public GenericKeywords(ExtentTest test)
	{
		this.test=test;
		prop=new Properties();
		try {
			FileInputStream fis=new FileInputStream(".//src//test//resources//locators.properties");
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void navigate(String url)
	{
		
	}
	

	public void getLocalBrowser(String bn) {
		if (bn.equalsIgnoreCase("firefox")) {
			System.out.println("hello firefox");
			System.setProperty("webdriver.gecko.driver", "..\\browserexes\\geckodriver.exe");
			driver = new FirefoxDriver();

		} else if (bn.equalsIgnoreCase("chrome")) {
			System.out.println("hellochrome");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", "..\\browserexes\\chromedriver.exe");
			driver = new ChromeDriver(options);
		} else if (bn.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "..\\browserexes\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

	}
	
	
	public void closeBrowser()
	{
		System.out.println("closingthebrowser");
		driver.quit();
	}

	private  void getRemoteBrowser(String bn) throws IOException {
   DesiredCapabilities cap=null;
		if (bn.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "..\\browserexes\\geckodriver.exe");
			FirefoxOptions fx = new FirefoxOptions();
			/*FirefoxOptions options = new FirefoxOptions()
					String strFFBinaryPath = "C:/Users/username/AppData/Local/Mozilla Firefox/firefox.exe";
					options.setBinary(strFFBinaryPath);*/
			
			/*cap=DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setJavascriptEnabled(true);
			cap.setCapability("marionette", true);
			cap.setPlatform(Platform.WINDOWS);*/
			//String node2 = CommonUtil.getPropertyValue("config", "client1");
			//System.out.println(node2);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), fx);

		} else if (bn.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "..\\browserexes\\chromedriver.exe");
			cap=DesiredCapabilities.firefox();
			cap.setBrowserName("firefox");
			cap.setJavascriptEnabled(true);
			cap.setPlatform(Platform.WINDOWS);
			//String node2 = CommonUtil.getPropertyValue("config", "client1");
			//System.out.println(node2);
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			
		} else if (bn.equalsIgnoreCase("ie")) {
			/*System.setProperty("webdriver.ie.driver", "..\\browserexes\\IEDriverServer.exe");
			
			 * dc=DesiredCapabilities.internetExplorer();
			 * dc.setBrowserName("internetExplorer");
			 
			
			 * dc.setPlatform(Platform.WINDOWS); driver=new
			 * InternetExplorerDriver(dc);*/
			 
		}

	}

	public String getBrowser(String bn, String rm) throws IOException {
		if (rm.equalsIgnoreCase("remote")) {
			System.out.println("hello");
			getRemoteBrowser(bn);
		}
		if (rm.equalsIgnoreCase("local")) {
			getLocalBrowser(bn);
		}
		return "pass";
	}

	public  String launchApp(String url) {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		driver.get(url);
		return "pass";
	}

	public  WebDriver getBrowser() {
		return driver;
	}

	
	public String click(String locator)
	{
		WebElement e=getElemement(locator);
		e.click();
		
		return "pass";
	}
	public String input(String locator,String data)
	{
		WebElement e=getElemement(locator);
		if(data.equalsIgnoreCase("enter"))
		{
			e.sendKeys(Keys.ENTER);
			
			
		}
		else
		{
		e.sendKeys(data);
		
		}
		return "pass";
		
		
	}
	
	public void verifyText(String locator,String expectedtext)
	{
		
	}
	
	public String verifyElementPresent(String locatorkey)
	{
		boolean result=isElementPresent(locatorkey);
		if(result)
		{
			return "pass";
		}
		else
		{
			return "fail"+locatorkey;
		}
	}
	
	public boolean isElementPresent(String locatorkey)
	{
		List<WebElement> e=null;
		
			if(locatorkey.endsWith("_id"))
			{
				e=driver.findElements(By.id(prop.getProperty(locatorkey)));
			}
			if(locatorkey.endsWith("_name"))
			{
				e=driver.findElements(By.name(prop.getProperty(locatorkey)));
			}
			if(locatorkey.endsWith("_xpath"))
			{
				e=driver.findElements(By.xpath(prop.getProperty(locatorkey)));
			}
			if(e.size()==0)
			{
				return false;
			}
			else
			{
				return true;
			}
		
		}
	
	public WebElement getElemement(String locatorkey)
	{
		WebElement e=null;
		System.out.println("clicking on Element"+locatorkey);
		try
		{
			if(locatorkey.endsWith("_id"))
			{
				e=driver.findElement(By.id(prop.getProperty(locatorkey)));
			}
			if(locatorkey.endsWith("_name"))
			{
				
				e=driver.findElement(By.name(prop.getProperty(locatorkey)));
			}
			if(locatorkey.endsWith("_xpath"))
			{
				System.out.println("entered here");
				e=driver.findElement(By.xpath(prop.getProperty(locatorkey)));
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception ex)
		{
			reportFailure("failure in element extraction"+locatorkey);
			Assert.fail("failure in element extraction"+locatorkey);
		}
		
		return e;

	}
	
	public List<WebElement> getElements(String locatorkey)
	{
		
		List<WebElement> e=null;
		test.log(LogStatus.INFO, "locating the element"+locatorkey);
		try
		{
			if(locatorkey.endsWith("_id"))
			{
				e=driver.findElements(By.id(prop.getProperty(locatorkey)));
			}
			if(locatorkey.endsWith("_name"))
			{
				e=driver.findElements(By.name(prop.getProperty(locatorkey)));
			}
			if(locatorkey.endsWith("_xpath"))
			{
				e=driver.findElements(By.xpath(prop.getProperty(locatorkey)));
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(Exception ex)
		{
			reportFailure("failure in element extraction"+locatorkey);
			Assert.fail("failure in element extraction"+locatorkey);
		}
		
		return e;

		
	}
	
	
	
	public String sleep() throws InterruptedException
	{
		Thread.sleep(8000);
		
		return "pass";
	}
	

	
	public void reportFailure(String errormessage)
	{
		takeScreenShot();
		test.log(LogStatus.FAIL, errormessage);
		
	}
	
	public void takeScreenShot()
	{
		Date d=new Date();
		String fileName=d.toString().replace(":", "_").replace(" ", "_")+".png";
		
		File srcfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path="D:\\reports\\"+fileName;
		try
		{
			FileUtils.copyFile(srcfile,new File(path));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		test.log(LogStatus.INFO, test.addScreenCapture(path));
	
	}
	
	

}
