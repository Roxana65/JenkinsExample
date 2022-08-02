package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;

public class BaseTest {

public static WebDriver driver;
public BasePage page;

	@Parameters({"browser"})
	@BeforeClass
	public void setUp(String browser) {
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
		System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");
		
		ChromeOptions option = new ChromeOptions();
		//option.addArguments("--headless");
		option.addArguments("start-maximized");
		
		FirefoxBinary firefoxBinary = new FirefoxBinary();
		//firefoxBinary.addCommandLineOptions("--headless");
		FirefoxOptions foption = new FirefoxOptions();
		foption.setBinary(firefoxBinary);
		
		if(browser !=""&& browser !=null) {
			if(browser.equalsIgnoreCase("chrome")) {
				driver= new ChromeDriver(option);
			}else if (browser.equalsIgnoreCase("firefox")) {
				driver= new FirefoxDriver(foption);
			}
		}
		
		
		

	   // driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	 
	    //driver.manage().window().setSize(new Dimension(1440, 900));
	    
	    driver.get("https://keybooks.ro/");
	    //driver.get("https://the-internet.herokuapp.com/javascript_alerts");
	    page= new BasePage();
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(5000);
		driver.quit();
	}
	
	@AfterMethod
	public void recordFailure(ITestResult result) throws IOException {
		
		if(ITestResult.FAILURE==result.getStatus()) {
			TakesScreenshot poza = (TakesScreenshot)driver;
			File picture =poza.getScreenshotAs(OutputType.FILE);
			String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			
			Files.copy(picture, new File("screenshots/"+result.getName()+"-"+timeStamp + ".png"));
			
		}
	}
}
