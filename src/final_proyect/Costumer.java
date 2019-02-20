package final_proyect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Costumer extends User {
	public Costumer() {
		this.user 		 = "user@phptravels.com";
		this.pass 		 = "demouser";
		this.url  		 = "https://www.phptravels.net/login";
		this.inputName   = "username";
		this.pathToLogin = "//*[@id=\"loginfrm\"]/button";
	}

	@Override
	public void logout(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[2]/a")).click();
	}
}