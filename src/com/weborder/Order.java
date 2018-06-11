package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import net.bytebuddy.utility.RandomString;

public class Order {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\zafer\\OneDrive\\Belgeler\\selenium dependencies\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();

		// driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Process.aspx");
		driver.findElement(By.linkText("Order")).click();

		Random rd = new Random();
		int num = rd.nextInt(100 - 1) + 1;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys("" + num);

		int r = (int) (Math.random() * 4);
		String list = new String[] { "Bob", "Alisa", "Michelle", "Juan", "Bill" }[r];
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John " + list + " Smith");

		// Random r = new Random();
		// int Low = 64;
		// int High = 90;
		// String result = "" + (char) (r.nextInt(High-Low) + Low);
		// driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John
		// " + result + " Smith");

		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any Street");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Anytown");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Texas");

		int zip = rd.nextInt(100000 - 10000) + 10000;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys("" + zip);

		int cardType = rd.nextInt(3);
		if (cardType == 0) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
		} else if (cardType == 1) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
		} else {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();
		}

		String cardNumber;
		if (cardType == 0) {
			cardNumber = "4";
			for (int i = 1; i < 16; i++)
				cardNumber += rd.nextInt(3);
		} else if (cardType == 1) {
			cardNumber = "5";
			for (int i = 1; i < 16; i++)
				cardNumber += rd.nextInt(3);
		} else {
			cardNumber = "3";
			for (int i = 1; i < 15; i++)
				cardNumber += rd.nextInt(3);
		}
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);

		// int dt = rd.nextInt(11)+1;
		// int Low = 18;
		// int High = 23;
		// int res = rd.nextInt(High-Low)+Low;
		// driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("0"+dt+"/"+res);

		int mm = rd.nextInt(12 - 1) + 1;
		String m = "";
		if (mm < 10) {
			m = "0" + mm;
		} else {
			m = "" + mm;
		}
		int yy = rd.nextInt(88 - 18) + 18;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys(m + "/" + yy);

		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

		String expected = "New order has been successfully added.";
		String actual = driver.findElement(By.id("ctl00_MainContent_fmwOrder")).getText();

		if (actual.contains(expected)) {
			System.out.println("pass");
		} else {
			System.out.println("fail");
			System.out.println("Expected:\t" + expected);
			System.out.println("Actual:\t" + actual);
		}

		Thread.sleep(5000);
		driver.close();
	}

}
