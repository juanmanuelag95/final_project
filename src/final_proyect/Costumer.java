package final_proyect;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;


public class Costumer extends User {
	public Costumer() {
		this.user 		 = "user@phptravels.com";
		this.pass 		 = "demouser";
		this.url  		 = "https://www.phptravels.net/login";
		this.inputName   = "username";
		this.pathToLogin = "//*[@id=\"loginfrm\"]/button";
	}

	@Override
	public void logout(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[2]/a")).click();
	}

	public boolean couponAvailable(WebDriver driver) {
		// TODO Auto-generated method stub
		
		return true;
		
	}
	
	public void bookHotel(WebDriver driver, Data datahc2) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		Thread.sleep(6000);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen9\"]")).sendKeys(datahc2.params.get("HotelName"));
		Thread.sleep(3000);
		List<WebElement> listOptions = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
		for (WebElement option: listOptions){
            if (option.getText().equalsIgnoreCase("Grand Plaza Serviced Apartments, London"))    
                option.click();
		}
		Thread.sleep(3000);
		driver.findElement(By.name("checkin")).sendKeys(datahc2.params.get("CheckIn"));
		driver.findElement(By.xpath("//*[@id=\"dpd2\"]/div/input")).sendKeys(datahc2.params.get("CheckOut"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"travellersInput\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"travellersInput\"]")).sendKeys(datahc2.params.get("People"));
		Thread.sleep(5000); 
		driver.findElement(By.xpath("//*[@id=\"hotels\"]/form/div[5]/button")).click();
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,1400)");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr[1]/td/div[2]/div[2]/div/div[3]/div/label/div")).click();
		driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/button")).click();
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,15000)");
		driver.findElement(By.name("logged")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@class=\"btn btn-default arrivalpay\"]")).click();
		Thread.sleep(4000);
		Alert simpleAlert = driver.switchTo().alert();
		simpleAlert.accept();
		Thread.sleep(6000);
		WebElement text = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[1]/td/div/b"));
		String AutoCode = text.getText();
		System.out.println("YOUR BOOKING STATUS IS : " + AutoCode);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
		
		
	}
}