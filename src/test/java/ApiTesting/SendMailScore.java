package ApiTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class SendMailScore {

	
	String url="https://cricbuzz.com/live-cricket-scores/118907/pbks-vs-rcb-qualifier-1-indian-premier-league-2025";
	@Test
	public void getTheScore() throws InterruptedException {
		
		
		System.setProperty("webdriver", "chromedriver.exe");
		
        ChromeOptions options = new ChromeOptions();
//      options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
	      driver.manage().window().setSize(new org.openqa.selenium.Dimension(1920, 1080));
	      driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);	 
	 	   driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 	   driver.manage().window().maximize();
	 	   driver.get(url); 
		
		String score1=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']//h2")).getText();
		System.out.println(score1);
		String insideParentheses1= score1.replaceAll(".*\\((.*?)\\).*", "$1").split("\\.")[0];
    	Pattern pattern = Pattern.compile("/(\\d+)\\s*\\(");
    	Matcher matcher = pattern.matcher(score1);
    	String wicketInString="";
    	if (matcher.find()) {
    	    wicketInString = matcher.group(1);
    	    
    	}

		Integer over=Integer.parseInt(insideParentheses1);
		Integer wicket=Integer.parseInt(wicketInString);
		over++;
		
		while(over<=20 && wicket<=10)
		{
			
//			score1=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']//h2")).getText();
			String scor2=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']//h2")).getText();
			if(!score1.equals(scor2))
			{
				System.out.println(scor2);
				score1=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']//h2")).getText();
			}
		
			insideParentheses1= scor2.replaceAll(".*\\((.*?)\\).*", "$1").split("\\.")[0];
	    	if (matcher.find()) {
	    	    wicketInString = matcher.group(1);
	    	    
	    	}
	    	
	    	if(!wicketInString.equals(""+wicket+""))
	    	{
				score1=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']//h2")).getText();
				String score3=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']")).getText();
				String totalSc=score3;
				// -------- BATTING DATA EXTRACTION --------
				List<WebElement> battingRows = driver.findElements(By.xpath("//div[@ng-repeat='batsmen in match.miniscore.batsman']"));
				List<String> bat = new ArrayList<>();
				List<String> batToPrint = new ArrayList<>();

				for (WebElement row : battingRows) {
				    List<WebElement> cols = row.findElements(By.xpath(".//div")); // Use relative XPath here
				    if (cols.size() >= 6) {
				        String name = cols.get(0).getText().trim();
				        String runs = cols.get(1).getText().trim();
				        String balls = cols.get(2).getText().trim();
				        String fours = cols.get(3).getText().trim();
				        String sixes = cols.get(4).getText().trim();
				        String strikeRate = cols.get(5).getText().trim();

				        String bt = "<tr><td>" + name + "</td><td>" + runs + "</td><td>" + balls + "</td><td>" + fours + "</td><td>" + sixes + "</td><td>" + strikeRate + "</td></tr>";
				        String batPrint=name + " - R: " + runs + ", B: " + balls + ", 4s: " + fours + ", 6s: " + sixes + ", SR: " + strikeRate;
				        bat.add(bt);
				        batToPrint.add(batPrint);
				    }
				}


				// -------- BOWLING DATA EXTRACTION --------
				List<WebElement> bowlingRows = driver.findElements(By.xpath("//div[@ng-repeat='bowler in match.miniscore.bowler']"));
				List<String> ball = new ArrayList<>();
				List<String> ballToPrint = new ArrayList<>();

				for (WebElement row : bowlingRows) {
				    List<WebElement> cols = row.findElements(By.xpath(".//div"));
				    if (cols.size() >= 6) {
				        String name = cols.get(0).getText().trim();
				        String overs = cols.get(1).getText().trim();
				        String maidens = cols.get(2).getText().trim();
				        String runs = cols.get(3).getText().trim();
				        String wickets = cols.get(4).getText().trim();
				        String economy = cols.get(5).getText().trim();

				        String bow = "<tr><td>" + name + "</td><td>" + overs + "</td><td>" + maidens + "</td><td>" + runs + "</td><td>" + wickets + "</td><td>" + economy + "</td></tr>";
		                String ballPrint=name + " - Over: " + overs + ", M: " + maidens + ", R: " + runs + ", W: " + wickets + ", ECO: " + economy;
				        ball.add(bow);
				        ballToPrint.add(ballPrint);
				    }
				}
				
		        String batPrint="";
		        String ballPrint="";
		        for(String a:batToPrint)
		        {
		        	batPrint=batPrint+ "\n" +a;
		        	
		        }
		        
		        for(String a:ballToPrint)
		        {

		        	ballPrint=ballPrint+ "\n" +a;
		        	
		        }
		        
		        System.out.println(totalSc+ "\n" +"\n------ BATTING ------"+ "\n"+batPrint+"\n------ BOWLING ------"+ "\n" +ballPrint);
		       
		        
		        StringBuilder htmlBody = new StringBuilder();
		    

		        htmlBody.append("<h2 style='color:#2E86C1;'>Match Score: ").append(totalSc).append("</h2>");

		        // Batting Summary Table
		        htmlBody.append("<h3 style='color:#117A65;'>Batting Summary</h3>");
		        htmlBody.append("<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; font-family: Arial;'>");
		        htmlBody.append("<tr style='background-color: #D6EAF8;'><th>Player</th><th>Runs</th><th>Balls</th><th>4s</th><th>6s</th><th>SR</th></tr>");

		        for (WebElement row : battingRows) {
		            List<WebElement> cols = row.findElements(By.cssSelector("div"));
		            if (cols.size() >= 6) {
		                htmlBody.append("<tr>");
		                for (int j = 0; j < 6; j++) {
		                    htmlBody.append("<td>").append(cols.get(j).getText()).append("</td>");
		                }
		                htmlBody.append("</tr>");
		            }
		        }
		        htmlBody.append("</table>");

		        // Bowling Summary Table
		        htmlBody.append("<h3 style='color:#C0392B;'>Bowling Summary</h3>");
		        htmlBody.append("<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; font-family: Arial;'>");
		        htmlBody.append("<tr style='background-color: #FADBD8;'><th>Player</th><th>Overs</th><th>Maidens</th><th>Runs</th><th>Wickets</th><th>Economy</th></tr>");

		        for (WebElement row : bowlingRows) {
		            List<WebElement> cols = row.findElements(By.cssSelector("div"));
		            if (cols.size() >= 6) {
		                htmlBody.append("<tr>");
		                for (int j = 0; j < 6; j++) {
		                    htmlBody.append("<td>").append(cols.get(j).getText()).append("</td>");
		                }
		                htmlBody.append("</tr>");
		            }
		        }
		        htmlBody.append("</table>");

		        // Send email
		        SendMail(htmlBody.toString(), totalSc);
		        wicket++;
		        }
	    	
			if(insideParentheses1.equals(""+over+""))
			{
				score1=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']//h2")).getText();
				String score3=driver.findElement(By.xpath("//div[@ng-show='isMiniscoreRendered']//div[contains(@class,'cb-col-scores')]//div[@class='cb-min-bat-rw']")).getText();
				String totalSc=score3;
				// -------- BATTING DATA EXTRACTION --------
				List<WebElement> battingRows = driver.findElements(By.xpath("//div[@ng-repeat='batsmen in match.miniscore.batsman']"));
				List<String> bat = new ArrayList<>();
				List<String> batToPrint = new ArrayList<>();

				for (WebElement row : battingRows) {
				    List<WebElement> cols = row.findElements(By.xpath(".//div")); // Use relative XPath here
				    if (cols.size() >= 6) {
				        String name = cols.get(0).getText().trim();
				        String runs = cols.get(1).getText().trim();
				        String balls = cols.get(2).getText().trim();
				        String fours = cols.get(3).getText().trim();
				        String sixes = cols.get(4).getText().trim();
				        String strikeRate = cols.get(5).getText().trim();

				        String bt = "<tr><td>" + name + "</td><td>" + runs + "</td><td>" + balls + "</td><td>" + fours + "</td><td>" + sixes + "</td><td>" + strikeRate + "</td></tr>";
				        String batPrint=name + " - R: " + runs + ", B: " + balls + ", 4s: " + fours + ", 6s: " + sixes + ", SR: " + strikeRate;
				        bat.add(bt);
				        batToPrint.add(batPrint);
				    }
				}


				// -------- BOWLING DATA EXTRACTION --------
				List<WebElement> bowlingRows = driver.findElements(By.xpath("//div[@ng-repeat='bowler in match.miniscore.bowler']"));
				List<String> ball = new ArrayList<>();
				List<String> ballToPrint = new ArrayList<>();

				for (WebElement row : bowlingRows) {
				    List<WebElement> cols = row.findElements(By.xpath(".//div"));
				    if (cols.size() >= 6) {
				        String name = cols.get(0).getText().trim();
				        String overs = cols.get(1).getText().trim();
				        String maidens = cols.get(2).getText().trim();
				        String runs = cols.get(3).getText().trim();
				        String wickets = cols.get(4).getText().trim();
				        String economy = cols.get(5).getText().trim();

				        String bow = "<tr><td>" + name + "</td><td>" + overs + "</td><td>" + maidens + "</td><td>" + runs + "</td><td>" + wickets + "</td><td>" + economy + "</td></tr>";
		                String ballPrint=name + " - Over: " + overs + ", M: " + maidens + ", R: " + runs + ", W: " + wickets + ", ECO: " + economy;
				        ball.add(bow);
				        ballToPrint.add(ballPrint);
				    }
				}
				
		        String batPrint="";
		        String ballPrint="";
		        for(String a:batToPrint)
		        {
		        	batPrint=batPrint+ "\n" +a;
		        	
		        }
		        
		        for(String a:ballToPrint)
		        {

		        	ballPrint=ballPrint+ "\n" +a;
		        	
		        }
		        
		        System.out.println(totalSc+ "\n" +"\n------ BATTING ------"+ "\n"+batPrint+"\n------ BOWLING ------"+ "\n" +ballPrint);
		       
		        
		        StringBuilder htmlBody = new StringBuilder();
		    

		        htmlBody.append("<h2 style='color:#2E86C1;'>Match Score: ").append(totalSc).append("</h2>");

		        // Batting Summary Table
		        htmlBody.append("<h3 style='color:#117A65;'>Batting Summary</h3>");
		        htmlBody.append("<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; font-family: Arial;'>");
		        htmlBody.append("<tr style='background-color: #D6EAF8;'><th>Player</th><th>Runs</th><th>Balls</th><th>4s</th><th>6s</th><th>SR</th></tr>");

		        for (WebElement row : battingRows) {
		            List<WebElement> cols = row.findElements(By.cssSelector("div"));
		            if (cols.size() >= 6) {
		                htmlBody.append("<tr>");
		                for (int j = 0; j < 6; j++) {
		                    htmlBody.append("<td>").append(cols.get(j).getText()).append("</td>");
		                }
		                htmlBody.append("</tr>");
		            }
		        }
		        htmlBody.append("</table>");

		        // Bowling Summary Table
		        htmlBody.append("<h3 style='color:#C0392B;'>Bowling Summary</h3>");
		        htmlBody.append("<table border='1' cellpadding='8' cellspacing='0' style='border-collapse: collapse; font-family: Arial;'>");
		        htmlBody.append("<tr style='background-color: #FADBD8;'><th>Player</th><th>Overs</th><th>Maidens</th><th>Runs</th><th>Wickets</th><th>Economy</th></tr>");

		        for (WebElement row : bowlingRows) {
		            List<WebElement> cols = row.findElements(By.cssSelector("div"));
		            if (cols.size() >= 6) {
		                htmlBody.append("<tr>");
		                for (int j = 0; j < 6; j++) {
		                    htmlBody.append("<td>").append(cols.get(j).getText()).append("</td>");
		                }
		                htmlBody.append("</tr>");
		            }
		        }
		        htmlBody.append("</table>");

		        // Send email
		        SendMail(htmlBody.toString(), totalSc);
		        over++;
		        }
				
			}
			Thread.sleep(5000);
		

		}

	
		

		

	
	public static void SendMail(String content,String sub)
	{
	    final String username = "alekhpradhan18@gmail.com";     
	    final String password = "fzqp anhv bwht xzyc";          

	    Properties props = new Properties();
	  
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("alekhpradhan18@gmail.com"));
	        String[] id = {"manupradhan7887@gmail.com"};
	        InternetAddress[] recipientAddresses = new InternetAddress[id.length];

	        for (int i = 0; i < id.length; i++) {
	            recipientAddresses[i] = new InternetAddress(id[i]);
	        }

	        message.setRecipients(Message.RecipientType.TO, recipientAddresses);

	        message.setSubject(sub);
	        message.setContent(content, "text/html");

	        Transport.send(message);

	        System.out.println("Email sent successfully!");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
	


}
