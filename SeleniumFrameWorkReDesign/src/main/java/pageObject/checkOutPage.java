package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponant.AbstractComponant;

public class checkOutPage extends AbstractComponant {
	
	WebDriver driver;
	public checkOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//input[@type='text'])[2]") 
    WebElement cvv;
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	By selectCountryButton=By.xpath("//div[@class='payment__shipping']//button[2]");
	
	@FindBy(xpath="//div[@class='payment__shipping']//button[2]")
	WebElement shippingButton;
	
	@FindBy(xpath="//a[@class='btnn action__submit ng-star-inserted']")
	WebElement placeOrder;
	
	public void selectCountry(String cvvNumber, String country) 
	{
		
		cvv.sendKeys(cvvNumber);
		selectCountry.sendKeys(country);
		WaitForvisibilityOfElementLocated(selectCountryButton);
		shippingButton.click();
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrder);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);
		
	}

	

}
