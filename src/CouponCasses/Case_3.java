package CouponCasses;
	import java.text.ParseException;
import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;
	import final_proyect.Admin;

public class Case_3 {
	public static void main(String[] args) throws InterruptedException, ParseException{
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Admin 	admin = new Admin();
		
		admin.login(driver);
		
		if (admin.validateCuponAvailable(driver, "QJpk"))
			System.out.println("Cupon is available");
		else
			System.out.println("Cupon is not available");
		
		admin.logout(driver);
		driver.close();
	}
}