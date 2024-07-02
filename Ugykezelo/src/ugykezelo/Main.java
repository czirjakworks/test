package ugykezelo;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main{
	
	public static void main (String[] args) throws InterruptedException {
		
		int n=0;					
		System.out.println("Válasszon az alábbi scenáriók közül: 1 Sikeres bejelentkezés (majd kilépés vagy további esetek), 2 Érvénytelen jelszó, 3 Kilépés");
		Scanner sc = new Scanner(System.in);
		n=sc.nextInt();
		switch(n){
			case 1:	
				//1 Sikeres bejelentkezés (majd kilépés vagy további esetek)
				System.out.println("Válasszon az alábbi scenáriók közül: 1 Sikeres bejelentkezés és böngésző bezárása, 2 Sikeres bejelentkezés és új ügy létrehozása");
				int p=0;
				Scanner sc2 = new Scanner(System.in);
				p=sc2.nextInt();
				switch(p) {
					case 1:
						//1 Sikeres bejelentkezés és böngésző bezárása
						//Bejelentkezési adatok megadása: felhasználó, jelszó
						Bejelentkezes bejelentkezes1=new Bejelentkezes("teszt.elek", "User1234");
						WebDriver session1 = new FirefoxDriver();
						bejelentkezes1.bekuld(bejelentkezes1.getFelhasznalo(), bejelentkezes1.getJelszo(), session1);
						session1.close();						
						break;
					case 2:
						//2 Sikeres bejelentkezés és új ügy létrehozása
						//Bejelentkezési adatok megadása: felhasználó, jelszó
						Bejelentkezes bejelentkezes2=new Bejelentkezes("teszt.elek", "User1234");
						WebDriver session2 = new FirefoxDriver();
						bejelentkezes2.bekuld(bejelentkezes2.getFelhasznalo(), bejelentkezes2.getJelszo(), session2);
						//Új ügy adatainak megadása: megnevezés, érvényes, személy, város, irányítószám, közterület, házszám
						UjUgyJovo ujugyjovo=new UjUgyJovo("Kutyakozmetika", "2024-07-02", "Teszt Költő", "Budapest", "1116", "Sztregova utca", "1.");
						ujugyjovo.ugyfelvetel(ujugyjovo.getMegnevezes(), ujugyjovo.getErvenyes(), ujugyjovo.getSzemely(), ujugyjovo.getVaros(), ujugyjovo.getIranyitoszam(), ujugyjovo.getKozterulet(), ujugyjovo.getHazszam(), session2);
						session2.close();				
						break;
				}
				break;
			case 2:
				//2 Érvénytelen jelszó
				//Bejelentkezési adatok megadása: felhasználó, jelszó
				Bejelentkezes bejelentkezes3=new Bejelentkezes("teszt.elek", "ervenytelenjelszo");
				WebDriver session3 = new FirefoxDriver();
				bejelentkezes3.bekuld(bejelentkezes3.getFelhasznalo(), bejelentkezes3.getJelszo(), session3);
				session3.close();
				break;
			case 3:
				System.out.println("Kilépés.");
				break;
			default: System.out.println("Hibás bevitel.");
			break;
			}		
		
	}


}