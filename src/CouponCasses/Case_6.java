package CouponCasses;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Admin;
	import final_proyect.Data;

class DataC extends Data {
	DataC() {
		params.put("status", "Enable");
		params.put("percentage", "10");
		params.put("max", "2");
		params.put("startdate", "12/11/2019");
		params.put("expdate", "12/12/2019");
		params.put("CuponCode", "TestCode");
		params.put("Assign_hotel", "hotel");
		params.put("Assign_Tours", "6 Days Around Thailand");
		params.put("Assign_Cars", "Kia Pacanto 2014");
	}
}

public class Case_6 {
	
	public static void main(String[] args) throws InterruptedException {
		DataC datac = new DataC();
		Admin admin = new Admin();	
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		admin.login(driver);
		admin.generateCupon(driver, datac);
		
		if (admin.validateCuponCreated(driver, datac))
			System.out.println("Cupon has been Created");
		else
			System.out.println("Cupon has not been Created");
		
		admin.logout(driver);
		driver.close();
	}
}