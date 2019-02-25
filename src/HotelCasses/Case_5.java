package HotelCasses;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import final_proyect.Costumer;
import final_proyect.Data;

class Data_5 extends Data {
	Data_5(){
		params.put("hotelName", "Swissotel Le Plaza Basel");
		params.put("checkin", "22/02/2019");
		params.put("checkout", "25/02/2019");
		params.put("people", "2 Adult 0 Child");
	}
}

public class Case_5 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		Data_5 data = new Data_5();
		Costumer costumer = new Costumer();
		
		costumer.login(driver);
		costumer.checkAmenities(driver, data);
		
		driver.close();
		
	}
	
}
