package final_proyect;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

public abstract class User {
	String user;
	String pass;
	String url;
	
	
	public void login(WebDriver driver) {
		driver.navigate().to(this.url);
		driver.findElement(By.name("email")).sendKeys(this.user);
		driver.findElement(By.name("password")).sendKeys(this.pass);
		driver.findElement(By.xpath("/html/body/div/form[1]/button")).click();
	}
	
	public abstract void logout(WebDriver driver);
}