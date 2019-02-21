package CouponCasses;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Admin;

public class Case_2 {
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		DataC 	data = new DataC();
		Admin 	admin = new Admin();
		
		admin.login(driver);
		admin.CouponToMultiple(driver, data);
		
		if (admin.validateCuponCreated(driver, data))
			System.out.println("Cupon has been Created");
		else
			System.out.println("Cupon has not been Created");
		
		admin.logout(driver);
		driver.close();
	}
}