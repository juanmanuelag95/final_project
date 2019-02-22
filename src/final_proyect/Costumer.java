package final_proyect;
	import java.util.List;
	import org.openqa.selenium.Alert;
	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;

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
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a")).click();
		Thread.sleep(2000);
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

	private void makeTheBook(WebDriver driver, Data data) throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"s2id_autogen9\"]")).sendKeys(data.params.get("hotelName"));
		Thread.sleep(2000);
		
        List<WebElement> listOptions = driver.findElements(By.xpath("//div[@class='select2-result-label']"));
		for (WebElement option: listOptions){
            if (option.getText().contains(data.params.get("hotelName")))   
                option.click();
		}
        
		Thread.sleep(2000);
		driver.findElement(By.name("checkin")).sendKeys(data.params.get("checkin"));
		driver.findElement(By.xpath("//*[@id=\"dpd2\"]/div/input")).sendKeys(data.params.get("checkout"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"travellersInput\"]")).clear();
		driver.findElement(By.xpath("//*[@id=\"travellersInput\"]")).sendKeys(data.params.get("people"));
		Thread.sleep(2000); 
		driver.findElement(By.xpath("//*[@id=\"hotels\"]/form/div[5]/button")).click();
	}
	
	public void bookHotel(WebDriver driver, Data data) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		makeTheBook(driver, data);
		
		//At hotel's page
		
		Thread.sleep(4000);
		js.executeScript("window.scrollBy(0,1400)");

		// @Rodrigo *** Let's check if there are available rooms 
		
		WebElement roomsTable = driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table"));
		List<WebElement> rooms = roomsTable.findElements(By.tagName("tr"));
		if (rooms.size() == 0)
		{
			
			System.out.println("No rooms vailable");
			
		} else { 
			
			// *** Continue if it's true
			
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
			
		}
		
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[1]/li[1]/a")).click();
	}

	public boolean checkFacilities(WebDriver driver, Data data) throws InterruptedException {
		Thread.sleep(3000);
        makeTheBook(driver,data);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        
        WebElement dateWidget = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div[5]/div/div[3]/div[1]/div/table"));
		List<WebElement> listOptions = dateWidget.findElements(By.tagName("td"));
            
        for (WebElement option: listOptions) {
        	js.executeScript("window.scrollBy(0,200)");
        	if (option.findElement(By.tagName("b")).getText().contains(data.params.get("toSearch"))) {
        		List<WebElement> listFacilities = option.findElements(By.tagName("img"));
        		boolean result;
        		
        		if (listFacilities.size() > 1)
        			result = true;
        		else
        			result = false;
        		
        		js.executeScript("window.scrollBy(0,2000)");
                return result;
	    	}
        }
        
        return false; 
	}
}