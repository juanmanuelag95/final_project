package CouponCasses;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Costumer;
	import final_proyect.Data;

class Data_4_Coupon extends Data {
	Data_4_Coupon(){		
		params.put("hotelName", "Ödeme Seçenekleri");
		params.put("checkin", "28/02/2019");
		params.put("checkout", "10/03/2019");
		params.put("location", "Ödeme Seçenekleri , Singapore");
		params.put("coupon", "QJpk"); // this coupon is not available
		params.put("people", "2 Adult 0 Child");
	}
}

public class Case_4 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Data_4_Coupon data = new Data_4_Coupon();
		Costumer costumer = new Costumer();
	
		costumer.login(driver);
		if (costumer.couponNotAvailable(driver, data))
			System.out.println("Coupon NotAvailable is not available");
		else
			System.out.println("Coupon NotAvailable is available");
		
		driver.close();
	}
}