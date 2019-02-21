package HotelCasses;
	import java.util.concurrent.TimeUnit;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.chrome.ChromeDriver;

	import final_proyect.Data;
	import final_proyect.Costumer;
	
class Data_6 extends Data {
	Data_6(){
		params.put("hotel", "Paseos del Manantial");
	}
}

public class Case_6 {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		Data_6 data = new Data_6();
		Costumer costumer = new Costumer();
		
		costumer.login(driver);
		
	}
	
}