package HotelCasses;
	import final_proyect.Costumer;
	import final_proyect.Data;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;


class Data_4 extends Data {
	Data_4(){
		params.put("hotelName", "Paseos de la Cascada");
		params.put("checkin", "22/02/2019");
		params.put("checkout", "25/02/2019");
		params.put("people", "2 Adult 1 Child");
	}
}

public class Case_4 {

	public static void main (String[] args) throws InterruptedException  {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Costumer customer = new Costumer ();
		Data_4 data= new Data_4 ();
		
		customer.login(driver);
		customer.compareInformation(driver, data);

		driver.close();
		
	}
	
}
