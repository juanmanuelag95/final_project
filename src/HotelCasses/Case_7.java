package HotelCasses;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	import final_proyect.Data;
	import final_proyect.Supplier;
	
class Data_7 extends Data {
	Data_7(){
		params.put("hotel", "Paseos de la Cascada");
		params.put("room", "Presidential Suite");
		
	}
}

public class Case_7 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		Data_7 data = new Data_7();
		Supplier supp= new Supplier();
		
		supp.login(driver);
		int bookings_before = supp.countBookings(driver);
		supp.bookHotel(driver, data);
		int bookings_after = supp.countBookings(driver);
		
		if (bookings_after > bookings_before) {
			System.out.println("Hotel succesfully booked");
		}
		else {
			System.out.println("Hotel booking failed");
		}
		
		driver.close();
	}
	
}
