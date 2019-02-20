package final_proyect;

import org.openqa.selenium.WebDriver;

public class Supplier extends User {
	Supplier() {
		this.user = "supplier@phptravels.com";
		this.pass = "demosupplier";
		this.url  = "https://www.phptravels.net/supplier";
	}

	@Override
	public void logout(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
}