package MakeMyTrip;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyTrip {
	WebDriver driver;
	String parent = "";


  @Test
  public void automate() throws InterruptedException {
	  WebDriverWait wait = new WebDriverWait(driver,30);
	  WebElement login = driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[1]/ul[1]/li[6]/div[1]"));
	  wait.until(ExpectedConditions.elementToBeClickable(login));
		Actions action =new Actions(driver);
		action.moveToElement(login).click().build().perform();
		driver.findElement(By.id("username")).sendKeys("aparnarekas@gmail.com");
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[2]/section[1]/form[1]/div[2]/button[1]")).click();
		driver.findElement(By.id("password")).sendKeys("AparnaRekas@2015");
		driver.findElement(By.xpath("//*[@id=\"SW\"]/div[1]/div[2]/div[2]/section/form/div[2]/button")).submit();
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[1]/div[1]/div[2]/div[2]/section[1]/div[1]/div[1]/span[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@class='   menu_Hotels ']/a")));
		driver.findElement(By.xpath("//li[@class='   menu_Hotels ']/a")).click();
		driver.findElement(By.id("city")).click();
		driver.findElement(By.xpath("//body/div[@id='root']/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/input[1]")).sendKeys("chennai");
		driver.findElement(By.xpath("//p[contains(text(),'Chennai, Tamil Nadu, India')]")).click();
		driver.findElement(By.xpath("//div[@class='DayPicker-Body']/div[2]/div[6]")).click();
		driver.findElement(By.xpath("//div[@class='DayPicker-Body']/div[4]/div[6]")).click();
		driver.findElement(By.id("guest")).click();
		driver.findElement(By.xpath("//div[@class='addRooomDetails']/ul[1]/li[4]")).click();
		driver.findElement(By.xpath("//div[@class='roomsGuestsBottom']/button[2]")).click();
		driver.findElement(By.id("hsw_search_button")).click();
		Actions drag = new Actions(driver);
		WebElement slider;
		slider= driver.findElement(By.className("input-range__slider"));
		wait.until(ExpectedConditions.elementToBeClickable(slider));
		int xCoord = slider.getLocation().getX();	
		drag.moveToElement(slider).click().dragAndDropBy(slider, xCoord+40,0 ).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'Kodambakkam')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[contains(text(),'Hotel')]")).click();
		driver.findElement(By.id("htl_id_seo_20070503193908940")).click();
		 parent = driver.getWindowHandle();
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		while (it.hasNext()){
			driver.switchTo().window(it.next());
			System.out.println(driver.getTitle());
		}
		WebElement button= driver.findElement(By.cssSelector("#detpg_book_combo_btn"));
		wait.until(ExpectedConditions.elementToBeClickable(button));
		button.click();
		driver.findElement(By.id("fName")).sendKeys("Ann");
		driver.findElement(By.id("lName")).sendKeys("John");
		driver.findElement(By.id("mNo")).sendKeys("8547227528");
		driver.findElement(By.xpath("//label[contains(text(),'Room on a high floor')]")).click();
		driver.findElement(By.xpath("//div[contains(text(),'Pay Now')]")).click();
		WebElement payMode = driver.findElement(By.xpath("//span[contains(text(),'Credit/Debit Cards')]"));
		wait.until(ExpectedConditions.elementToBeClickable(payMode));
		payMode.click();
		driver.findElement(By.id("PAYMENT_cardNumber")).sendKeys("3474736985512261");
		driver.findElement(By.id("PAYMENT_nameOnCard")).sendKeys("Ann John");
		Select month = new Select(driver.findElement(By.id("PAYMENT_expiryMonth")));
		month.selectByValue("05");
		Select year = new Select(driver.findElement(By.id("PAYMENT_expiryYear")));
		year.selectByValue("2023");
		driver.findElement(By.name("PAYMENT_cvv")).sendKeys("137");
		driver.findElement(By.xpath("//span[contains(text(),'Make Payment ')]")).click();
	  
  }
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", "C:\\Users\\Sony vaio\\Desktop\\Selenium\\chromedriver.exe");
	  driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
	 
  }
  
  @AfterMethod
  public void afterMethod() throws IOException {
	  File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String imagePath=System.getProperty("user.dir")+"/screenshot/image.png";
		FileUtils.copyFile(screenshot, new File(imagePath));
		driver.close();
		driver.switchTo().window(parent);
		System.out.println(driver.getTitle());
		driver.findElement(By.id("loginTrigger")).click();
	
		driver.findElement(By.xpath("//*[@data-cy='userMenuLogout']/div")).click();
  }
  @AfterClass
  public void afterClass() {
	  driver.close();

  }
  


}
