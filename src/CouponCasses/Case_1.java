package CouponCasses;

import java.text.ParseException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import final_proyect.Costumer;
import final_proyect.Data;

class DataC8 extends Data {
	DataC8() {
		params.put("", "");
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
			
			
		
		}
	}
	
	
