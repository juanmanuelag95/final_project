package HotelCasses;

	import final_proyect.Costumer;
	import final_proyect.Data;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;


class Data_4 extends Data {
	Data_4(){
		params.put("name", "Hotel Cancun");
		params.put("description", "Best Hotel EVER!!!");
		params.put("location", "Cancun");
	}
}

public class Case_4 {

	public static void main (String[] args) throws InterruptedException  {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Costumer customer = new Costumer ();
		Data_4 data = new Data_4 ();
		
		customer.login(driver);
		//customer.makeTheBook(driver, data);
		
	}
	
}
