package final_proyect;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class User {
	String user;
	String pass;
	String url;
	
	User(String user, String pass, String url){
		this.user = user;
		this.pass = pass;
		this.url  = url;
	}
	
	public void Login (WebDriver driver) throws InterruptedException {
		driver.navigate().to(this.url);
		driver.findElement(By.name("email")).sendKeys(this.user);
		driver.findElement(By.name("password")).sendKeys(this.pass);
		driver.findElement(By.xpath("/html/body/div/form[1]/button")).click();
	}
	
}