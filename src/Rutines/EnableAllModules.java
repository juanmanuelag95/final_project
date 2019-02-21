package Rutines;

	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	import final_proyect.Admin;

public class EnableAllModules {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Admin admin = new Admin();
		admin.enableAllModules(driver);
		
	}
	
}
