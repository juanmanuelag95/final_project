package final_proyect;
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

public class Admin extends User {
	public Admin() {
		this.user = "admin@phptravels.com";
		this.pass = "demoadmin";
		this.url  = "https://www.phptravels.net/admin";
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
		// TODO Auto-generated method stub
		
	}
}