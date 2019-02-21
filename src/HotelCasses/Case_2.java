//----------------------------------------------------------------------------------//  
//     Hotel                                    								    //
//     Test Case 2                            	 							        //
//     If the hotel is enabled, then the user should be able to book.               //
//----------------------------------------------------------------------------------//


package HotelCasses;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import final_proyect.Costumer;
import final_proyect.Data;

class DataHC2 extends Data {
	DataHC2() {
		params.put("HotelName", "Grand Plaza Serviced Apartments");
		params.put("CheckIn", "22/02/2019");
		params.put("CheckOut", "25/02/2019");
		params.put("People", "1 Adult 1 Child");
	}
}

public class Case_2 {

	public static void main(String[] args) throws InterruptedException {
		DataHC2 datahc2 = new DataHC2();
		WebDriver driver = new ChromeDriver();
		Costumer cust = new Costumer();
		
		cust.login(driver);
		
		cust.bookHotel(driver, datahc2);
		

	}

}
