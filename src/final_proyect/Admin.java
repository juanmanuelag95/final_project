package final_proyect;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Admin extends User {
	public Admin() {
		this.user = "admin@phptravels.com";
		this.pass = "demoadmin";
		this.url  = "https://www.phptravels.net/admin";
	}
	
	public void createHotel(WebDriver driver, Data data) {
		
		driver.findElement(By.xpath("//*[@id=\"social-sidebar-menu\"]/li[7]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"Hotels\"]/li[1]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/button")).click();
		
		driver.findElement(By.name("hotelname")).sendKeys(data.params.get("name"));
//		driver.findElement(By.name("hoteldesc")).sendKeys(data.params.get("name"));
		
		
		driver.switchTo().frame(1);
		driver.findElement(By.xpath("/html/body/p")).sendKeys(data.params.get("name"));
		
		
	}
}