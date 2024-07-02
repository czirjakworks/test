package ugykezelo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;


public class Bejelentkezes {
	
	private String user;
	private String password;
    	
	
	public Bejelentkezes (String user, String password) {
		
		this.user=user;
		this.password=password;
				
	}
	
	
	public String getFelhasznalo() {
		
		return user;
	}
	
	
	public String getJelszo() {
		
		return password;
	}	
	

	@SuppressWarnings("deprecation")
	public void bekuld (String user, String password, WebDriver session) throws InterruptedException {
					
		session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		session.navigate().to("https://ibello.hu/tasks/cases/#/login");
				
		session.manage().window().maximize();
		System.out.println("Bejelentkezési felület megnyílt.");
		
		WebElement felhasznalo=session.findElement(By.xpath("//*[@id=\"username\"]"));
		WebElement jelszo=session.findElement(By.xpath("//*[@id=\"password\"]"));
		WebElement bejelentkezes=session.findElement(By.xpath("/html/body/div/router-view/div/div[2]/div/button"));
				
		felhasznalo.sendKeys(user);
		System.out.println("Felhasználó megadva.");
		jelszo.sendKeys(password);
		System.out.println("Jelszó megadva.");
				
		Thread.sleep(5000);
		
		bejelentkezes.click();
		
		Thread.sleep(5000);
		
		session.switchTo().defaultContent();
		
		//Sikeres-e a belépés
		try{			
			WebElement panelcim=session.findElement(By.xpath("//h3[starts-with(@class, 'panel-title')][text()='Ügyek']"));
			boolean sikeresbelepes=panelcim.isDisplayed();
			if (sikeresbelepes==true) {
				System.out.println("A bejelentkezés sikeres. Panelcím látszik: " + panelcim.getText());
				}
			}catch(NoSuchElementException e){
			   System.out.println("A bejelentkezés sikertelen. Panelcím nem látszik.");
			   WebElement erroruzenet=session.findElement(By.xpath("//div[starts-with(@class, 'error')][text()='A felhasználói név és/vagy jelszó nem megfelelő.']"));
			   System.out.println("Error üzenet látszik: " + erroruzenet.getText());
		}
						
	}	


}
