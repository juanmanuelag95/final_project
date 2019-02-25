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
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[2]/div[3]/span")).click();
        Thread.sleep(3000);
        driver.findElement(By.name("logged")).click();
        Thread.sleep(6000);
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

	public boolean checkDiscountCoupon(WebDriver driver, Data data) throws InterruptedException {
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
        
        String discount = driver.findElement(By.xpath("//*[@id=\"bookingdetails\"]/div[5]/div[2]/div[4]/div/strong")).getText();
        discount = discount.substring(0, discount.length() - 1);

        String price = driver.findElement(By.xpath("//*[@id=\"displaytotal\"]")).getText();
        
        driver.findElement(By.name("logged")).click();
        
        String actualPrice = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[3]")).getText();
        actualPrice = actualPrice.substring(5, actualPrice.length()).replace(",", "");

        int expectedPrice = ((100 - Integer.parseInt(discount)) * Integer.parseInt(price)) / 100;
        
        if (Integer.parseInt(actualPrice)	== expectedPrice) 
        	return true;
        else 
        	return false;
	}

	public boolean couponNotAvailable(WebDriver driver, Data data) throws InterruptedException {
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
        
        Thread.sleep(6000);
        Alert simpleAlert = driver.switchTo().alert();
        boolean result;
        
        if (simpleAlert.getText().equals("Invalid Coupon"))
        	result = true;
		else 
			result =  false;
        
        simpleAlert.accept();
		Thread.sleep(2000);
		return result;
	}
	
	public void compareInformation (WebDriver driver, Data data) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		String hotel = data.params.get("hotelName").toUpperCase();
		String checkin = data.params.get("checkin").toUpperCase();
		String checkout = data.params.get("checkout").toUpperCase();
		String people = data.params.get("people").toUpperCase();
		
		System.out.println("\n# INPUT DATA #\n");
		System.out.println("*hotel:\t" + hotel);
		System.out.println("*checkin:\t" + checkin);
		System.out.println("*checkout:\t" + checkout);
		System.out.println("*people:\t" + people);
		
		makeTheBook(driver, data);
		
		// *** At Holtel's page
		
		WebElement roomsTable = driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table"));
		List<WebElement> rooms = roomsTable.findElements(By.tagName("td"));
		if (rooms.size() == 0)
		{
			System.out.println("No rooms vailable");
		} else {
			WebElement room = rooms.get(0);
			//System.out.println(room);
			
			js.executeScript("window.scrollBy(0,1000)");
			Thread.sleep(2000);
			
			String price0 = room.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr/td/div[2]/div[2]/div/div[2]/h2")).getText().toUpperCase();
			String room0 = room.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr/td/div[2]/div[1]/div[1]/h4/a")).getText().toUpperCase();
			
			System.out.println("\n# FIRST ROOM'S DATA #\n");
			System.out.println("*room (name and capacity):\t" + room0);
			System.out.println("*price:\t" + price0);
			
			room.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/table/tbody/tr/td/div[2]/div[2]/div/div[3]/div/label")).click();
			driver.findElement(By.xpath("//*[@id=\"ROOMS\"]/div/button")).click();
			
			// *** At Confirmation/Summary
			
			boolean status1 = true;
			
			String checkin1 = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[2]/div[2]/div/ul/li[1]/span")).getText().toUpperCase();
			boolean checkin1_status = checkin1.contains(checkin);
			status1 &= checkin1_status;
			
			String checkout1 = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[2]/div[2]/div/ul/li[2]/span")).getText().toUpperCase();
			boolean checkout1_status = checkout1.contains(checkout);
			status1 &= checkout1_status;
			
			String people1 = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[2]/div[3]/div[2]/p/span")).getText().toUpperCase();
			boolean people1_status = people1.contains(people);
			status1 &= people1_status;
			
			String room1 = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[2]/div[3]/div[2]/p/strong")).getText().toUpperCase();
			boolean room1_status = room0.contains(room1);
			status1 &= room1_status;
			
			String payment1 = driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[2]/div[4]/table[2]/tbody/tr[2]/td[2]")).getText().toUpperCase();
			boolean payment1_status = payment1.contains(price0);
			status1 &= payment1_status;

			System.out.println("\n# DATA AT CONFIRMATION/SUMARY #\n");
			System.out.println("*chekin:\t" + checkin1 + "\t" + checkin1_status);
			System.out.println("*checkout:\t" + checkout1 + "\t" + checkout1_status);
			System.out.println("*people:\t" + people1 + "\t" + people1_status);
			System.out.println("*room:\t" + room1 + "\t" + room1_status);
			System.out.println("*payment:\t" + payment1+ "\t" + payment1_status);
			
			System.out.println("\nMATCHES:\t" + status1);
			
			driver.findElement(By.xpath("//*[@id=\"body-section\"]/div/div[1]/div/div[1]/div/div[3]/button")).click();
			
			// ** At Invoice
			
			boolean status2 = true;
			
			String hotel2 = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[1]/td[1]")).getText().toUpperCase();
			boolean hotel2_status = hotel2.contains(hotel);
			status2 &= hotel2_status;
			
			String room2 = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[3]/td[1]")).getText().toUpperCase();
			boolean room2_status = room2.contains(room1);
			status2 &= room2_status;
			
			String checkin2 = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[4]/td[2]")).getText().toUpperCase();
			boolean checkin2_status = checkin2.contains(checkin);
			status2 &= checkin2_status;
			
			String checkout2 = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[1]/tbody/tr[5]/td[2]")).getText().toUpperCase();
			boolean checkout2_status = checkout2.contains(checkout);
			status2 &= checkout2_status;
			
			String payment2 = driver.findElement(By.xpath("//*[@id=\"invoiceTable\"]/tbody/tr[4]/td/table/tbody/tr[2]/td/table[2]/tbody/tr/td[3]")).getText().toUpperCase();
			boolean payment2_status = payment2.contains(price0);
			status2 &= payment2_status;
			
			System.out.println("\n# DATA AT INVOICE #\n");
			System.out.println("*hotel: " + hotel2+ "\t" + hotel2_status);
			System.out.println("*(#) room ($/n): " + room2 + "\t" + room1_status);
			System.out.println("*chekin: " + checkin2 + "\t" + checkin2_status);
			System.out.println("*checkout: " + checkout2 + "\t" + checkout2_status);
			System.out.println("*payment: " + payment2 + "\t" + payment2_status);
			
			System.out.println("\nMATCHES:\t" + status2);

		}

	}
	
}