package final_proyect;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

public abstract class User {
	String user;
	String pass;
	String url;
	String inputName;
	String pathToLogin;
	
	public void login(WebDriver driver) {
		driver.navigate().to(this.url);
		driver.manage().window().maximize();
		driver.findElement(By.name(this.inputName)).sendKeys(this.user);
		driver.findElement(By.name("password")).sendKeys(this.pass);
		driver.findElement(By.xpath(this.pathToLogin)).click();
	}
	
	public abstract void logout(WebDriver driver) throws InterruptedException;
}