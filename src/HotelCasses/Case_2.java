//----------------------------------------------------------------------------------//  
//     Hotel                                    								    //
//     Test Case 2                            	 							        //
//     If the hotel is enabled, then the user should be able to book.               //
//----------------------------------------------------------------------------------//

package HotelCasses;

import java.text.ParseException;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import final_proyect.Admin;
import final_proyect.Costumer;
import final_proyect.Data;

class DataHC2 extends Data {
	DataHC2() {
		params.put("hotelName", "Paseos de la Cascada");
		params.put("checkin", "22/02/2019");
		params.put("checkout", "25/02/2019");
		params.put("people", "1 Adult 1 Child");
	}
}

public class Case_2 {

	public static void main(String[] args) throws InterruptedException, ParseException {
		DataHC2 datahc2 = new DataHC2();
		WebDriver driver = new ChromeDriver();
		Costumer cust = new Costumer();
		Admin admin = new Admin();

		admin.login(driver);

		if (admin.validateHotelIsAble(driver, datahc2)) {
			
			System.out.println("Hotel is available");
			admin.logout(driver);
			
			cust.login(driver);
			cust.bookHotel(driver, datahc2);

		} else {
			System.out.println("Hotel is not available");
		}
		
		driver.close();

	}

}
