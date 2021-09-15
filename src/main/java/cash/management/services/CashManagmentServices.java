package cash.management.services;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import commons.Base;

public class CashManagmentServices extends Base {
	
	@FindBy(xpath="//div[@class='spend-save-plans']/h1")
	WebElement text_header;
	
	@FindBy(className="card-original")
	WebElement icon_aspiration;
	
	@FindBy(xpath="//div[@class='plan plan-a-plus']")
	WebElement icon_aspirationPlus;
	
	@FindBy(xpath="//div[@class='plan plan-original']//li")
	List<WebElement> list_aspirationDetials;
	
	@FindBy(xpath="//div[@class='plan plan-a-plus']//li")
	List<WebElement> list_aspirationPlusDetials;
	
	@FindBy(xpath="//div[@class='plan plan-original']//button")
	WebElement btn_getAspiration;
	
	@FindBy(xpath="//div[@class='plan plan-a-plus']//button")
	WebElement btn_getAspirationPlus;
	
	@FindBy(name="email")
	WebElement txt_email;
	
	@FindBy(className="close")
	WebElement btn_close;
	
	@FindBy(xpath="//div[@uib-modal-window='modal-window']")
	WebElement btn_closeModalPlus;
	
	@FindBy(xpath="//div[contains(@ng-click,'yearly')]//b[@class='ng-binding']")
	WebElement text_yearly;
	
	@FindBy(xpath="//div[contains(@ng-click,'monthly')]//b[@class='ng-binding']")
	WebElement text_monthly;
	
	/**
	 * @Description Verify both Plan exist Aspiration/ Aspiration Plus
	 * @Author Sergio Ramones
	 * @Date 14-SEP-2021
	 * @Parameter N/A
	 * @return N/A
	 * @throws Exception
	 **/
	public void verifyPlans() throws Exception {
		reviewElement(text_header);
		reporter(getText(text_header));
		if (verifyElementExist(icon_aspiration) == true) {
			reporter("Aspiration card exist");
			takeScreenShot();
		} else {
			Assert.fail("Aspiration card don't exist in the page");
		}
		reviewElement(text_header);
		if (verifyElementExist(icon_aspirationPlus) == true) {
			reporter("Aspiration Plus card exist");
			takeScreenShot();
		} else {
			Assert.fail("Aspiration Plus card don't exist in the page");
		}
		
		validateListOfValues(list_aspirationDetials, detailsAspirationCard());
		validateListOfValues(list_aspirationPlusDetials, detailsAspirationPlusCard());
	}//end method
	
	/**
	 * @Description Verify both Plan exist Aspiration/ Aspiration Plus
	 * @Author Sergio Ramones
	 * @Date 14-SEP-2021
	 * @Parameter N/A
	 * @return N/A
	 * @throws Exception
	 **/
	public void verifyModals(double yearlyPay, double monthlyPay) throws Exception {
		reviewElement(btn_getAspiration);
		clickJScript(btn_getAspiration);
		if (verifyElementExist(txt_email) == true) {
			reporter("Email for Aspiration card exist");
			takeScreenShot();
		} else {
			Assert.fail("Email for Aspiration don't exist in the page");
		}
		click(btn_close);
		reviewElement(btn_getAspirationPlus);
		clickJScript(btn_getAspirationPlus);
		
		if (verifyElementExist(text_yearly) == true) {
			reporter("Prices for Aspiration Plus card exist");
			sleep(2000);
			double actualYearlyPay = Double.parseDouble(getText(text_yearly).replaceAll("[^\\d.]", ""));
			double actualmonthlyPay = Double.parseDouble(getText(text_monthly).replaceAll("[^\\d.]", ""));
			takeScreenShot();
			if(actualYearlyPay==yearlyPay && actualmonthlyPay==monthlyPay) {
				reporter("Prices are matching with expected: yearly [ "+actualYearlyPay+" ] monthly [ "+actualmonthlyPay+" ]");
				reporter("Expeted Prices are not matching: yearly [ "+yearlyPay+" ] monthly [ "+monthlyPay+" ]");
			}else {
				Assert.fail("Actual prices are not matching: yearly [ "+actualYearlyPay+" ] monthly [ "+actualmonthlyPay+" ] "
						+ "\n Expeted Prices are not matching: yearly [ "+yearlyPay+" ] monthly [ "+monthlyPay+" ]");
			}
			
			
		} else {
			Assert.fail("Prices for Aspiration Plus card don't exist in the page");
		}
		keywordEsc(btn_closeModalPlus);
	}//verifyModals
	
	

}//end 
