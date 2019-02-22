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
		params.put("HotelName", "Grand Plaza");
		params.put("CheckIn", "22/02/2019");
		params.put("CheckOut", "25/02/2019");
		params.put("People", "1 Adult 1 Child");
	}
}

public class Case_2 {

	 static String HotelNameToSearch = "Grand Plaza";

	public static void main(String[] args) throws InterruptedException, ParseException {
		DataHC2 datahc2 = new DataHC2();
		WebDriver driver = new ChromeDriver();
		Costumer cust = new Costumer();
		Admin admin = new Admin();

		admin.login(driver);

		if (admin.validateHotelIsAble(driver, HotelNameToSearch, datahc2)) {
			
			System.out.println("Hotel is available");
			admin.logout(driver);
			driver.close();
			Thread.sleep(6000);
			
			WebDriver driver2 = new ChromeDriver();
			cust.login(driver2);
			cust.bookHotel(driver2, datahc2);
			cust.logout(driver2);
			driver2.close();
		} else {
			System.out.println("Hotel is not available");
			admin.logout(driver);
			driver.close();
		}

	}

}
