package ApiTesting;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Ppas {
	public static void main(String[] args) throws InterruptedException, AWTException {
	
			
			String  farmerDetail="",tokenfile="",ppcCode = "";
			String userName="",password="",pacdID="";
			Scanner sc=new Scanner(System.in);
			System.out.println("Please give the Society Id");
			Thread.sleep(2000);
			System.out.print("For Badapalanka Press B and for Gopinathpur press G ");
			ppcCode=sc.next();
			
			switch (ppcCode.toUpperCase()) {
			case "G":
				userName="S1110717";
				password="Kunjit@2024";

				break;
			case "B":
				userName="S1110703";
				password="BIPRA@321";

				break;

			default:
				break;
			}
			
			System.setProperty("webdriver", "chromedriver.exe");
			WebDriver driver=new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);	 
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get("https://ppas.pdsodisha.gov.in");
			driver.findElement(By.id("txtuserID")).sendKeys(userName);
			driver.findElement(By.id("txtPassword")).sendKeys(password);
			Thread.sleep(7000);
			driver.findElement(By.id("LoginButton")).click();
			driver.findElement(By.className("icon-menu")).click();
			driver.findElement(By.xpath("//a//span[text()='Purchase']")).click();
			driver.findElement(By.xpath("//a[text()='Paddy Purchase']")).click();
			driver.findElement(By.xpath("//a[text()='View']")).click();
			Select ppcid=new Select(driver.findElement(By.id("intPpcId")));
			ppcid.selectByIndex(1);
			driver.findElement(By.xpath("//button[@id='searchBtnId']")).click();
			
			
			for(int j=1;j<=20;j++)
			{
			List<WebElement> table=driver.findElements(By.xpath("//table[@id='viewPacsTableId']//tbody//tr"));
			for(int i=0;i<table.size();i++)
			{
				List<WebElement>col=table.get(i).findElements(By.tagName("td"));
				String vrNo=col.get(6).getText().trim();
				col.get(12).findElement(By.tagName("a")).click();
				
				Set<String> winIds=driver.getWindowHandles();
				String firstWindow=driver.getWindowHandle();
				
				for(String swit:winIds)
				{
					if(!swit.equals(firstWindow))
					{
						driver.switchTo().window(swit);
					}
				}
				Robot robot = new Robot();
				Thread.sleep(1000);
			    // Bring Chrome Print Dialog into focus (CTRL + P)
		        robot.keyPress(KeyEvent.VK_CONTROL);
		        robot.keyPress(KeyEvent.VK_P);
		        robot.keyRelease(KeyEvent.VK_P);
		        robot.keyRelease(KeyEvent.VK_CONTROL);

		        // Wait for Print Dialog
		        Thread.sleep(1000);

		        // Press Enter to confirm printing
		        robot.keyPress(KeyEvent.VK_ENTER);
		        robot.keyRelease(KeyEvent.VK_ENTER);

		        System.out.println("Print confirmed.");
		        // Wait for Save As dialog
		        Thread.sleep(1000); 
				
				   for (char c : vrNo.toCharArray()) {
		                robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(c));
		                robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(c));
		            }

		            // Press Enter to save the file
		            robot.keyPress(KeyEvent.VK_ENTER);
		            robot.keyRelease(KeyEvent.VK_ENTER);

		            System.out.println("File saved successfully!");
		            driver.close();
		            
		            driver.switchTo().window(firstWindow);
		            // Scroll down from the element


			}
			 WebElement element = driver.findElement(By.xpath("//a[text()='Next']")); // Change to your element's locator
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            Thread.sleep(2000);
            
           
			
			}
			
			
			
	}

}
