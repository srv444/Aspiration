package sepend.save;

import java.net.SocketException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import aspiration.green.financial.services.AspirationGreenFinancialServices;
import cash.management.services.CashManagmentServices;
import commons.Base;
@Listeners({commons.ScreenshotOfFailedTest.class})
public class VerifySpendSavePlans extends Base{
	
	String url =  System.getProperty("URL");
	static WebDriver driver;
	
	@Parameters(value= {"browser"})
	@BeforeTest
	public void startBrowser(@Optional("chrome") String browser) {
		driver = startDriver(url,browser);
	}
	
	
	@Test(priority =1)
	public void verifyPlansSpendAndSave() throws Exception{
		page.getInitPage(AspirationGreenFinancialServices.class).selectMenu("SPEND & SAVE");
		page.getInitPage(CashManagmentServices.class).verifyPlans();
		page.getInitPage(CashManagmentServices.class).verifyModals(71.88, 7.99);
	}
	
	

	@AfterTest
	public void closeApplication() throws SocketException, InterruptedException {
		closeBrowser();
	}
	
	
	
}//end class
