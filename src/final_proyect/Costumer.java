package final_proyect;

import org.openqa.selenium.WebDriver;

public class Costumer extends User {
	Costumer() {
		this.user = "user@phptravels.com";
		this.pass = "demouser";
		this.url  = "https://www.phptravels.net/";
	}

	@Override
	public void logout(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
}