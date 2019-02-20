package final_proyect;
	import java.util.List;
	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Admin extends User {
	public Admin() {
		this.user 		 = "admin@phptravels.com";
		this.pass 		 = "demoadmin";
		this.url  		 = "https://www.phptravels.net/admin";
		this.inputName   = "email";
		this.pathToLogin = "/html/body/div/form[1]/button";
	}
	
	public void createHotel(WebDriver driver, Data data) throws InterruptedException {
		// Path to get to Add Hotel
		driver.findElement(By.xpath("//*[@id=\"social-sidebar-menu\"]/li[7]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"Hotels\"]/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
		
		// Complete the form
		driver.findElement(By.name("hotelname")).sendKeys(data.params.get("name"));
		
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id=\"cke_1_contents\"]/iframe")));
		driver.findElement(By.cssSelector("body")).sendKeys(data.params.get("description"));
		driver.switchTo().defaultContent();
		
		driver.findElement(By.xpath("//*[@id=\"s2id_searching\"]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"select2-drop\"]/div/input")).sendKeys(data.params.get("location"));
		List<WebElement> listOptions = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
		listOptions.get(0).click();
		
		// Click on Add Button
		driver.findElement(By.xpath("//*[@id=\"add\"]")).click();
	}
	
	public boolean validateHotelIsCreated(WebDriver driver, Data data) {		
		 return data.params.get("name").contentEquals(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[2]/div/div/div[1]/div[2]/table/tbody/tr[1]/td[5]")).getText()) ? true : false ;
	}

	@Override
	public void logout(WebDriver driver) {
		driver.findElement(By.xpath("/html/body/div[2]/aside/div/div[2]/div/div[2]/div/a")).click();
	}

	
	public void generateCupon (WebDriver driver, Data datac) throws InterruptedException  {
		
		driver.findElement(By.xpath("//*[@id=\"social-sidebar-menu\"]/li[7]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"Hotels\"]/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"social-sidebar-menu\"]/li[31]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div[1]/button")).click();
		
		WebElement Status = driver.findElement(By.name("status"));
		Select selectStatus = new Select(Status);
		selectStatus  .selectByVisibleText(datac.params.get("status"));
		
		driver.findElement(By.name("rate")).sendKeys(datac.params.get("percentage"));
		driver.findElement(By.name("max")).sendKeys(datac.params.get("max"));
		driver.findElement(By.name("startdate")).sendKeys(datac.params.get("startdate"));
		driver.findElement(By.name("expdate")).sendKeys(datac.params.get("expdate"));
		driver.findElement(By.xpath("//*[@id=\"addcoupon\"]/div[2]/div[1]/div[2]/div/div[1]/ins")).click();
		driver.findElement(By.name("code")).sendKeys(datac.params.get("CuponCode"));
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen2\"]")).sendKeys(datac.params.get("Assign_hotel"));
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen2\"]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen4\"]")).sendKeys(datac.params.get("Assign_Tours"));
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen4\"]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen6\"]")).sendKeys(datac.params.get("Assign_Cars"));
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen6\"]")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//*[@class=\"btn btn-primary submitcoupon\"]")).click();
		Thread.sleep(6000);
	}
	
	public boolean validateCuponCreated(WebDriver driver, Data datac) {		
		 return datac.params.get("CuponCode").contentEquals(driver.findElement(By.cssSelector("div.panel.panel-default > div.panel-body > div.xcrud > div > div.xcrud-ajax > div.xcrud-list-container > table > tbody > tr:nth-child(1) > td:nth-child(4)")).getText()) ? true : false ;
	}
}