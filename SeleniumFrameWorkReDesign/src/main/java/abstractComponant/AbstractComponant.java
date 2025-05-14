package abstractComponant;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponant {
	
	WebDriver driver;
	
	public AbstractComponant(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By goTocard=By.xpath("//button[@routerlink='/dashboard/cart']");
	
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cardButton;
	
	
	public void WaitForvisibilityOfElementLocated(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitForInvisibilityOfElementLocated(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void goToCart()
	{
		WaitForvisibilityOfElementLocated(goTocard);
		cardButton.click();
	}
	

}
