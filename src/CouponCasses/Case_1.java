package CouponCasses;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import final_proyect.Costumer;
import final_proyect.Data;

class DataC8 extends Data {
	DataC8() {
		params.put("coupon", "Maxlimit");
		params.put("hotelName", "Ödeme Seçenekleri");
		params.put("checkin", "22/04/2019");
		params.put("checkout", "25/04/2019");
		params.put("people", "1 Adult 1 Child");
	}
}

	public class Case_1 {
		
		public static void main(String[] args) throws InterruptedException, ParseException {
			DataC8 data = new DataC8();
			WebDriver driver = new ChromeDriver();
			Costumer cust = new Costumer();
			
			cust.login(driver);
			cust.couponAvailable(driver, data);
			cust.couponAvailable(driver, data);
			cust.couponNotAvailable(driver, data);
			cust.logout(driver);
			driver.close();
			System.out.println("Coupone has exceeded all its uses");
		
		}
	}
	
	
