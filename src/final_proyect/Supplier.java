package final_proyect;

	import java.util.List;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.support.ui.Select;

public class Supplier extends User {
	public Supplier() {
		this.user 		= "supplier@phptravels.com";
		this.pass 		= "demosupplier";
		this.url  		= "https://www.phptravels.net/supplier";
		this.inputName  = "email";
		this.pathToLogin = "/html/body/div/form[1]/button";
	}

	@Override
	public void logout(WebDriver driver) {
		driver.findElement(By.xpath("/html/body/div[2]/aside/div/div[2]/div/div[2]/div/a")).click();
	}
	
	public int countBookings(WebDriver driver) throws InterruptedException{
		//Expecting logged in and at https://www.phptravels.net/supplier
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div[1]/div[3]/div/button[4]")).click();
		Thread.sleep(2000);
		WebElement initialTable = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div[1]/div[2]/table/tbody"));
		return (initialTable.findElements(By.tagName("tr")).size());
		
	}
	
	public void bookHotel(WebDriver driver, Data data) throws InterruptedException{
		//Expecting logged in and at https://www.phptravels.net/supplier
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Quick Booking
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[1]/button")).click();
		
		Thread.sleep(2000);
		
		//Pop Up
		WebElement servicetype = driver.findElement(By.xpath("//*[@id=\"servicetype\"]"));	
		Select select_servicetype = new Select(servicetype);
		select_servicetype.selectByIndex(1);
		driver.findElement(By.xpath("//*[@id=\"quickbook\"]/div[2]/div/form/div[3]/button[2]")).click();
		
		//Quick Booking form
			
		WebElement hotelName = driver.findElement(By.xpath("//*[@id=\"s2id_autogen3\"]/a/span[1]"));
		hotelName.click();
		WebElement hotelNameInput = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
		hotelNameInput.sendKeys(data.params.get("hotel"));
		hotelNameInput.sendKeys(Keys.ENTER);
		
		Thread.sleep(2000);
		
		WebElement roomName = driver.findElement(By.xpath("//*[@id=\"s2id_poprooms\"]/a/span[1]"));
		roomName.click();
		WebElement roomNameInput = driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input"));
		roomNameInput.sendKeys(data.params.get("room"));
		roomNameInput.sendKeys(Keys.ENTER);
		
		WebElement checkinDate = driver.findElement(By.xpath("/html/body/div[3]/div[1]/table/tbody"));
		List<WebElement> checkinDays = checkinDate.findElements(By.tagName("td"));
		for (WebElement day: checkinDays){
			if (day.getAttribute("class").contains("active")) {
				day.click();
				break;
			}
		}
		
		Thread.sleep(1000);
		
		WebElement checkoutDate = driver.findElement(By.xpath("/html/body/div[4]/div[1]/table/tbody"));
		List<WebElement> checkoutDays = checkoutDate.findElements(By.tagName("td"));
		checkoutDays.get(checkoutDays.size()-1).click();
		
		WebElement bookNow = driver.findElement(By.xpath("//*[@id=\"bookingform\"]/div[5]/div/input[2]"));
		bookNow.click();
		
	}
	
}