package final_proyect;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

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

	public boolean couponAvailable(WebDriver driver, Data data) throws InterruptedException {
		Thread.sleep(3000);
        makeTheBook(driver,data);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript("window.scrollBy(0,1300)");
        
        List<WebElement> listOptions = driver.findElements(By.xpath("//div[@class='control__indicator']"));

        for (WebElement option: listOptions) {
        	option.click();  
        	break;
        }
        
        driver.findElement(By.xpath("//*[@id='ROOMS']/div/button")).click();
        driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[2]/input")).sendKeys(data.params.get("coupon"));
        driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span")).click();
        driver.findElement(By.name("logged")).click();
        driver.findElement(By.xpath("//*[@class=\"btn btn-default arrivalpay\"]")).click();
       
        Alert simpleAlert = driver.switchTo().alert();
        simpleAlert.accept();
        Thread.sleep(2000);
       
        WebElement text = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[1]/td/div/b"));
        boolean result;
		
        if (text.getText().equalsIgnoreCase("RESERVED")) 
        	result = true;
        else 
        	result = false;
        
        driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
        return result;
	}

	private void makeTheBook(WebDriver driver, Data data) {
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[2]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen9\"]")).sendKeys(data.params.get("hotel"));
        List<WebElement> listOptions = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
        
        for (WebElement option: listOptions)
        	if (option.getText().equalsIgnoreCase(data.params.get("location"))) 	
        		option.click();
        
        driver.findElement(By.name("checkin")).sendKeys(data.params.get("checkin"));
        driver.findElement(By.name("checkout")).sendKeys(data.params.get("checkout"));
        driver.findElement(By.xpath("//*[@id=\"hotels\"]/form/div[5]/button")).click();
	}
}