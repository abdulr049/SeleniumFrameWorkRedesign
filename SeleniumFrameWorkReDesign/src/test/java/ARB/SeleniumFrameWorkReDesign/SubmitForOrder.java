package ARB.SeleniumFrameWorkReDesign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import pageObject.CartPage;
import pageObject.LandingPage;
import pageObject.ProductCatalog;
import pageObject.checkOutPage;

public class SubmitForOrder {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		LandingPage landingPage = new LandingPage(driver);
		landingPage.login("abdulr049@gmail.com", "Ascent@123");

		ProductCatalog productCatalog = new ProductCatalog(driver);
		productCatalog.getProductByName(productName);
		productCatalog.addToCard(productName);

		Thread.sleep(2000);

		productCatalog.goToCartPage();

		CartPage cartPage = new CartPage(driver);
		Boolean match = cartPage.verifyCartProducts(productName);
		Assert.assertTrue(match);
		checkOutPage checkOut = cartPage.checkOutPage();
		checkOut.selectCountry("123", "Ind");
		productCatalog.WaitForvisibilityOfElementLocated(By.cssSelector(".hero-primary"));

		String confirmMessageElement = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmMessageElement);

	}

}
