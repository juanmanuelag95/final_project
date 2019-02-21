package HotelCasses;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Costumer;
	import final_proyect.Data;

class Data_8_Hotel extends Data {
	Data_8_Hotel(){
		params.put("cupon", "Hotel Cancun");
	}
}

public class Case_8 {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Costumer costumer = new Costumer();
		costumer.login(driver);
		
		if (costumer.couponAvailable(driver))
			System.out.println("Cupon Available");
		else
			System.out.println("Cupon Not Available");
			
	}
}