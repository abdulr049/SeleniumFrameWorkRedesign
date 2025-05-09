package ARB.SeleniumFrameWorkReDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObject.LandingPage;

public class SubmitForOrder {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		LandingPage landingPage=new LandingPage(driver);
		landingPage.login("abdulr049@gmail.com", "Ascent@123");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']")));

		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3 h5"));

		WebElement prod = productList.stream().filter(product -> product.getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);

		prod.findElement(By.xpath(".//following-sibling::button[text()=' Add To Cart']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@routerlink='/dashboard/cart']")))
				.click();

		driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cartSection']/h3")));

		List<WebElement> cardProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		Boolean match = cardProducts.stream()
				.anyMatch(cardProduct -> cardProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();

		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("132");

		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");

		WebElement shippingButton = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@class='payment__shipping']//button[2]")));
		shippingButton.click();

		WebElement placeOrder = driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']"));

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", placeOrder);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrder);
		
		String confirmMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary"))).getText();
		
		//String cofirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmMessageElement);

	}

}
