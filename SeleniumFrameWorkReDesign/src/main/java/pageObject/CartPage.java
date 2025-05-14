package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractComponant.AbstractComponant;

public class CartPage extends AbstractComponant{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	By cardproductsLoad=By.xpath("//div[@class='cartSection']/h3");
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cardProducts;
	
	@FindBy(xpath="//button[normalize-space()='Checkout']")
	WebElement checkoutPage;
	

	public boolean verifyCartProducts(String productName)
	{
		WaitForvisibilityOfElementLocated(cardproductsLoad);
		return cardProducts.stream()
				.anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));

	}
	
	public checkOutPage checkOutPage()
	{
		checkoutPage.click();
		return new checkOutPage(driver);
		
	}

}
