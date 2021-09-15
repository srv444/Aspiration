package aspiration.green.financial.services;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.Base;



public class AspirationGreenFinancialServices extends Base{
	
	
	@FindBy(xpath="//ul[@display='none,none,none,inline-flex']/li")
	List<WebElement> list_principalMenu;
	
	/**
	 * @Description Select a menu from the principal menu at the header
	 * @Author Sergio Ramones
	 * @Date 14-SEP-2021
	 * @Parameter String
	 * @return N/A
	 * @throws Exception 
	 **/
	public void selectMenu(String menu) throws Exception {
		reviewElement(list_principalMenu.get(0));
		selectElementByValue(list_principalMenu,menu);
		takeScreenShot();
	}
	
	

}
