package final_proyect;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
}