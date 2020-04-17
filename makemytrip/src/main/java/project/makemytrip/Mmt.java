package project.makemytrip;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Mmt {
	
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		//1 Go to https://www.makemytrip.com/
		driver.get("https://www.makemytrip.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		driver.findElement(By.className("menu_Hotels")).click();
		
		//2 Click Hotels, Enter city as Goa, and choose Goa, India
		driver.findElement(By.id("city")).click();
		driver.findElement(By.xpath("//input[@placeholder='Enter city/ Hotel/ Area/ Building']")).sendKeys("Goa",Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//p[contains(text(),'Goa, India')]")).click();		
		
		
		//4 Enter Check in date as Next month 15th (May 15) and Check out as start date+5 (may 19th)
		String inDate = "May 15 2020";
		String outDate = "May 19 2020";
		
		driver.findElement(By.xpath("//input[@id='checkin']")).click();
		driver.findElement(By.xpath("//div[contains(@aria-label,'"+ inDate +"')]")).click();
		driver.findElement(By.xpath("//div[contains(@aria-label,'"+ outDate +"')]")).click();
				
		//5 Click on ROOMS & GUESTS 
		driver.findElement(By.id("guest")).click();
		driver.findElement(By.xpath("//li[@data-cy='adults-2']")).click(); //apply adults
		driver.findElement(By.xpath("//li[@data-cy='children-1']")).click(); //apply children
		driver.findElement(By.xpath("//button[contains(@class,'primaryBtn btnApply')]")).click(); //Click search button
		driver.findElement(By.id("hsw_search_button")).click(); 
	
		driver.findElement(By.xpath("//div[contains(@class,'mmBackdrop wholeBlack')]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		
		driver.findElement(By.xpath("//label[@for='mmLocality_checkbox_35']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[text()='5 Star']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[contains(text(),'Acron Waterfront Resort')]")).click();
		
		//Switching to the new window
		String parentWindow = driver.getWindowHandle();
		
		Set<String> mulWindows = driver.getWindowHandles();
		List<String> listOfWindow = new ArrayList<String>(mulWindows);
		listOfWindow.addAll(mulWindows);
		
		WebDriver hotelWindow = driver.switchTo().window(listOfWindow.get(1));
		System.out.println("SWITCHED TO THE WINDOWS : "+ driver.getTitle());
		
		//10 Print the Hotel Name
		String hotelName = driver.findElement(By.id("detpg_hotel_name")).getText(); //Print the Hotel Name 
		System.out.println("HOTEL NAME IS : " + hotelName);
		
		//11 Click MORE OPTIONS link and Select 3Months plan and close
		driver.findElement(By.xpath("//span[contains(@class,'latoBold font10 blueText pointer')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='emiOptionModalContentRight']//tr[2]/td[6]")).click();
		Thread.sleep(3000);
		driver.findElement(By.className("close")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("detpg_headerright_book_now")).click();
		
		//13 Print the Total Payable amount
		String totalPayAmt = driver.findElement(By.id("revpg_total_payable_amt")).getText();
		System.out.println("TOTAL NEED TO BE PAIED : "+totalPayAmt);
		
		//14 Close the browser
		hotelWindow.close();
		driver.switchTo().window(parentWindow).close();
	}
	
}
