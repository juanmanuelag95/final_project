package HotelCasses;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Costumer;
import final_proyect.Data;
	
class Data_3_Hotel extends Data {
	Data_3_Hotel(){
		params.put("hotel", "mex");
		params.put("checkin", "28/02/2019");
		params.put("checkout", "10/03/2019");
		params.put("location", "Mexico City, Mexico");
		params.put("toSearch", "Rendezvous Hotels");
	}
}

public class Case_3 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Data_3_Hotel data = new Data_3_Hotel();
		Costumer costumer = new Costumer();
		costumer.login(driver);
		if (costumer.checkFacilities(driver, data))
			System.out.println("Facilities are displayed");
		else
			System.out.println("Facilities are not displayed");
		
		costumer.logout(driver);
		driver.close();
	}
}