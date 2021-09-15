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
	
	@FindBy(className="plan plan-a-plus")
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
	
	@FindBy(className="email")
	WebElement btn_close;
	
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

		if (verifyElementExist(icon_aspirationPlus) == true) {
			reporter("Aspiration Plus card exist");
			takeScreenShot();
		} else {
			Assert.fail("Aspiration Plus card don't exist in the page");
		}
		
		validateListOfValues(list_aspirationDetials, detailsAspirationCard());
		validateListOfValues(list_aspirationPlusDetials, detailsAspirationCard());
	}//end method
	
	/**
	 * @Description Verify both Plan exist Aspiration/ Aspiration Plus
	 * @Author Sergio Ramones
	 * @Date 14-SEP-2021
	 * @Parameter N/A
	 * @return N/A
	 * @throws Exception
	 **/
	public void verifyModals() throws Exception {
		reviewElement(btn_getAspiration);
		click(btn_getAspiration);
		if (verifyElementExist(txt_email) == true) {
			reporter("Email for Aspiration card exist");
			takeScreenShot();
		} else {
			Assert.fail("Email for Aspiration don't exist in the page");
		}
		click(btn_close);
		if (verifyElementExist(txt_email) == true) {
			reporter("Email for Aspiration Plus card exist");
			takeScreenShot();
		} else {
			Assert.fail("Email for Aspiration Plus don't exist in the page");
		}
		click(btn_close);
	}//verifyModals
	
	

}//end 
