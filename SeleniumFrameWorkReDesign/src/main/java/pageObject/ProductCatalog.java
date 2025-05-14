package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponant.AbstractComponant;

public class ProductCatalog extends AbstractComponant {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By findby = By.xpath("//div[@class='card-body']");
	By productsBy = By.cssSelector(".mb-3 h5");
	By toastcontainer = By.cssSelector("#toast-container");
	By animating = By.cssSelector(".ng-animating");

	@FindBy(css = ".mb-3 h5")
	List<WebElement> productList;

	By addToCard = By.xpath(".//following-sibling::button[text()=' Add To Cart']");

	public List<WebElement> getProductList() {
		WaitForvisibilityOfElementLocated(productsBy);
		return productList;
	}

	public WebElement getProductByName(String productName) {

		WebElement prod = getProductList().stream().filter(product -> product.getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return prod;

	}

	public void addToCard(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCard).click();
		WaitForvisibilityOfElementLocated(toastcontainer);
		WaitForInvisibilityOfElementLocated(animating);
	}
	
	public void goToCartPage()
	{
		goToCart();
	}

}
