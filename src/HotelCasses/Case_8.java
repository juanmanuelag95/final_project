package HotelCasses;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Costumer;
	import final_proyect.Data;

class Data_8_Hotel extends Data {
	Data_8_Hotel(){
		params.put("hotelName", "Ödeme Seçenekleri");
		params.put("checkin", "28/02/2019");
		params.put("checkout", "10/03/2019");
		params.put("people", "2 Adult 0 Child");
		params.put("coupon", "wWIw");
	}
}

public class Case_8 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Data_8_Hotel data = new Data_8_Hotel();
		Costumer costumer = new Costumer();
		costumer.login(driver);
		
		if (costumer.couponAvailable(driver, data))
			System.out.println("Cupon Available");
		else
			System.out.println("Cupon Not Available");
		
		driver.close();
	}
}