package ugykezelo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class UjUgyJovo  {
	
	private String megnevezes;
	//private String allapot;
	private String ervenyes;
	private String szemely;
	private String varos;
	private String iranyitoszam;
	private String kozterulet;
	private String hazszam;
	//private String felelos;
		
		
	public UjUgyJovo (String megnevezes, String ervenyes, String szemely, String varos, String iranyitoszam, String kozterulet, String hazszam) {	
		
		this.megnevezes=megnevezes;
		this.ervenyes=ervenyes;
		this.szemely=szemely;
		this.varos=varos;
		this.iranyitoszam=iranyitoszam;
		this.kozterulet=kozterulet;
		this.hazszam=hazszam;
	}	
	
	
	
	public String getMegnevezes() {
		
		return megnevezes;
	}
	
	
	public String getErvenyes() {
		
		return ervenyes;
	}
	
	
	public String getSzemely() {
		
		return szemely;
	}
	
	
	public String getVaros() {
		
		return varos;
	}
	
	
	public String getIranyitoszam() {
		
		return iranyitoszam;
	}
	
	
	public String getKozterulet() {
		
		return kozterulet;
	}
	
	
	public String getHazszam() {
		
		return hazszam;
	}
	
	

	@SuppressWarnings("deprecation")	
	public void ugyfelvetel (String megnevezes, String ervenyes, String szemely, String varos, String iranyitoszam, String kozterulet, String hazszam, WebDriver session) throws InterruptedException {
		
		session.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
						
		WebElement newcase=session.findElement(By.xpath("/html/body/div/router-view/div/div[2]/div/a"));
		
		newcase.click();
		System.out.println("Új ügy rögzítési felület megnyílt.");
		
		Thread.sleep(5000);
		
		//Dátum beállítása a holnapi napra
		Calendar calendar = Calendar.getInstance();				
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow=calendar.getTime();		
		SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-dd");
		ervenyes = DateFormat.format(tomorrow);
		
			
		WebElement name=session.findElement(By.xpath("//*[@id=\"name\"]"));
		//WebElement status=session.findElement(By.xpath("//*[@id="status"]"));
		WebElement validity=session.findElement(By.xpath("//*[@id=\"validity\"]"));
		WebElement person=session.findElement(By.xpath("//*[@id=\"person\"]"));
		WebElement city=session.findElement(By.xpath("//*[@id=\"city\"]"));
		WebElement zipCode=session.findElement(By.xpath("//*[@id=\"zipCode\"]"));
		WebElement street=session.findElement(By.xpath("//*[@id=\"street\"]"));
		WebElement number=session.findElement(By.xpath("//*[@id=\"number\"]"));
		//WebElement responsible=driver.findElement(By.xpath("//*[@id=\"responsible\"]"));
		WebElement save=session.findElement(By.xpath("/html/body/div/router-view/div/div[2]/div/a[1]"));
		
		
		name.clear();
		name.sendKeys(megnevezes);
		validity.sendKeys(ervenyes);
		person.sendKeys(szemely);
		city.sendKeys(varos);
		zipCode.sendKeys(iranyitoszam);
		street.sendKeys(kozterulet);
		number.sendKeys(hazszam);
		
		System.out.println("Összes adat kitöltésre került.");
		
		Thread.sleep(5000);
				
		//Sikeres-e a Mentés gombra kattintás
		try{
			   save.click();
			}catch(NoSuchElementException e){
			   System.out.println("Nem sikerült menteni az ügyet.");
			}
		System.out.println("Sikeres klikkelés a Mentés gombra.");
		
		
		Thread.sleep(5000);
		
		
		//Új ügyek táblázatban az eredmény ellenőrzése
		ugytablazatcheck(session);				
		
    }
	
	
	
	public void ugytablazatcheck (WebDriver session) throws InterruptedException {
		
		//Új ügyek táblázat
		WebElement tbl = session.findElement(By.xpath("/html/body/div/router-view/div/div[2]/table"));

		//Összes sor csekkolása, 'tr' tag
		List<WebElement> rows = tbl.findElements(By.tagName("tr"));
		List<String> result = new ArrayList<>();
		String eredmeny="";

		//Sorok
		for(int i=0; i<rows.size(); i++) {
		    //Egy sor oszlopai, 'td' tag
		    List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));

		    //Oszlopok
		    for(int j=0; j<cols.size(); j++) {
		    	String cell = cols.get(j).getText();		    	
		    	result.add(cell);		   
		    }		
	   }	
		
		eredmeny=result.toString();	
		
		//Szerepel-e az eredményben a létrehozott ügy személy adata
		if (result.contains(szemely)) {
			System.out.println("Az új ügy szerepel a táblázatban." + eredmeny);		
		}else {
			System.out.println("Az új ügy nem szerepel a táblázatban.");
		}
		
	}
	
		
	
}
