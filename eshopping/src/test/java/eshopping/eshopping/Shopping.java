package eshopping.eshopping;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class Shopping {
	
	WebDriver driver;
	
   	@BeforeMethod
	public void setUp() 
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//src//test//java//Exe//chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); //Page load timeout
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS); //Implicit wait
		driver.get("http://automationpractice.com/index.php"); //Enter url
	}
	
	@Test
	public void Register() throws IOException, InterruptedException 
	{
		//Extracting time-stamp for unique email id
		DateFormat df= new SimpleDateFormat("ddMMyyHHmmss");
	    Date dateobj = new Date();
	    String date=df.format(dateobj);
	    System.out.println(date);
		
	    System.out.println("***Home screen****");
	    driver.findElement(By.xpath("//a[@class='login']")).click();
	    
	    System.out.println("***Sign In screen****");
		driver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(date+"@test.com");
		driver.findElement(By.xpath("//*[@id='SubmitCreate']/span")).click();
		
		driver.findElement(By.xpath("//*[@id='id_gender1']")).click();
		driver.findElement(By.xpath("//*[@id='customer_firstname']")).sendKeys("FirstName");
		driver.findElement(By.xpath("//*[@id='customer_lastname']")).sendKeys("LastName");
		driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("Test1234");
		
		Select day=new Select(driver.findElement(By.xpath("//*[@id='days']")));
		day.selectByValue("19");
		
		Select mon=new Select(driver.findElement(By.xpath("//*[@id='months']")));
		mon.selectByValue("7");
		
		Select yr=new Select(driver.findElement(By.xpath("//*[@id='years']")));
		yr.selectByValue("1994");
		
		driver.findElement(By.xpath("//*[@id='company']")).sendKeys("Company");
		driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("Line 1, PO -1234, Company");
		driver.findElement(By.xpath("//*[@id='city']")).sendKeys("NeyYork");
		
		Select state=new Select(driver.findElement(By.xpath("//*[@id='id_state']")));
		state.selectByValue("11");
		
		driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("12345");
		
		Select con=new Select(driver.findElement(By.xpath("//*[@id='id_country']")));
		con.selectByValue("21");
		
		driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("1234567890");
		driver.findElement(By.xpath("//*[@id='alias']")).clear();
		driver.findElement(By.xpath("//*[@id='alias']")).sendKeys("FirstName");
		
		driver.findElement(By.xpath("//*[@id='submitAccount']/span")).click();
	    System.out.println("***Sign In screen completed****");

		
		System.out.println("***Account screen****");
		driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[1]/a")).click();
		
		System.out.println("***Women section screen****");
		driver.findElement(By.xpath("//img[@class='replace-2x img-responsive' and @title='Faded Short Sleeve T-shirts']")).click();
		
		driver.switchTo().frame(0);
		System.out.println("***Women apparel pop up****");
		driver.findElement(By.xpath("//*[@id='quantity_wanted']")).clear();
		driver.findElement(By.xpath("//*[@id='quantity_wanted']")).sendKeys("2");
		driver.findElement(By.xpath("//*[@id='add_to_cart']/button/span")).click();
		System.out.println("***Women apparel added to cart****");
		
		Thread.sleep(3000l);
		System.out.println("***Women apparel cart summary****");
		
		String Total_products=driver.findElement(By.xpath("//div[@class='layer_cart_row']/span[@class='ajax_block_products_total']")).getText();
		String total_prod=Total_products.substring(1);
		float tot_prod=Float.parseFloat(total_prod);
		
		String Total_shipping=driver.findElement(By.xpath("//div[@class='layer_cart_row']/span[@class='ajax_cart_shipping_cost']")).getText();
		String total_ship=Total_shipping.substring(1);
		float tot_ship=Float.parseFloat(total_ship);
		
		String Total=driver.findElement(By.xpath("//div[@class='layer_cart_row']/span[@class='ajax_block_cart_total']")).getText();
		String total=Total.substring(1);
		float tot=Float.parseFloat(total);
		
		//Verifying the Total amount
		Assert.assertEquals(tot,tot_prod+tot_ship);
		
		
		System.out.println(Total_products+" + "+Total_shipping+" = "+Total); 
		driver.findElement(By.xpath("//a[@class='btn btn-default button button-medium']")).click();
		
		System.out.println("***Women apparel cart summary check-out****");
		
		Thread.sleep(3000l);
		
		System.out.println("***SHOPPING-CART SUMMARY screen****");
		
		String total_summ=driver.findElement(By.xpath("//span[@id='total_price']")).getText();
		String tot_summ=total_summ.substring(1);
		float total_summary=Float.parseFloat(tot_summ);
		System.out.println("Total amount on summary screen- "+total_summary);
		
		//Verifying the total price on Summary screen
		Assert.assertEquals(total_summary, tot);

		driver.findElement(By.xpath("//a[@class='button btn btn-default standard-checkout button-medium']")).click();
		System.out.println("***SHOPPING-CART SUMMARY screen completed****");
		
		Thread.sleep(3000l);
		System.out.println("***ADDRESSES screen****");
		driver.findElement(By.xpath("//button[@name='processAddress']")).click();
		System.out.println("***ADDRESSES screen completed****");
		
		System.out.println("***Shipping screen*****");
		driver.findElement(By.xpath("//input[@id='cgv']")).click(); //Check-box
		driver.findElement(By.xpath("//button[@name='processCarrier']")).click(); //Proceed to check-out
		System.out.println("***Shipping screen completed****");
		
		Thread.sleep(3000);
		System.out.println("***Payment screen*****");
		
		
		String total_pay=driver.findElement(By.xpath("//span[@id='total_price']")).getText();
		String tot_pay=total_pay.substring(1);
		float total_payment=Float.parseFloat(tot_pay);
		System.out.println("Total Amount payment - "+total_payment);
		
		//Verifying the total price on Payment screen
		Assert.assertEquals(total_payment, tot);
		
		System.out.println("***Payment option selection****");
		driver.findElement(By.xpath("//a[@class='bankwire']")).click(); 
		
		driver.findElement(By.xpath("//button[@class='button btn btn-default button-medium']")).click(); //Click on Confirm order
		
		Thread.sleep(3000);
		System.out.println("***Payment screen completed****");
		
		System.out.println("***Naviagting to Order history screen****");
		driver.findElement(By.xpath("//a[@class='account']")).click();
		
		driver.findElement(By.xpath("//ul[@class='myaccount-link-list']//li//a/span")).click();
		
		System.out.println("***Order history screen****");
		
		Thread.sleep(3000);
		String price=driver.findElement(By.xpath("//*[@id='order-list']/tbody/tr/td[3]/span")).getText();
		String price_tot=price.substring(1);
		float price_total=Float.parseFloat(price_tot);
		System.out.println("Total price on Order history screen- "+price_total);
		
		//Verifying the total price on Order history screen
		Assert.assertEquals(price_total,tot);
		
	}
	
	@AfterMethod
	public void tearDown() 
	{
		driver.quit();
	}

}
