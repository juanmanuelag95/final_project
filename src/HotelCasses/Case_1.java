package HotelCasses;
	import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Admin;
	import final_proyect.Data;

class Data_1 extends Data {
	Data_1(){
		params.put("name", "Hotel Cancun");
		params.put("description", "Best Hotel EVER!!!");
		params.put("location", "Mexico City, Mexico");
	}
}


public class Case_1 {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		Data_1 data = new Data_1();
		Admin admin = new Admin();
		
		admin.login(driver);
		admin.createHotel(driver, data);
		
//		validateHotelIsCreated
//		Logout
//		driver.close();
		
	}
}