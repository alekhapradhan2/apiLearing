package ApiTesting;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String fullText = "PBKSfhehjsfhje 149/2 (12)";
    	String insideParentheses = fullText.replaceAll(".*\\((.*?)\\).*", "$1").split("\\.")[0];
    	int a=12;
    	System.out.println( insideParentheses.equals(""+a+""));
    	
    	String totalSc="PBKS 205/4 (17)  CRR: 12.06";
        String name = "40";
        String overs = "20";
        String maidens = "50";
        String runs = "10";
        String wickets = "5";
        String economy = "1";

        String bow=name + " - Over: " + overs + ", M: " + maidens + ", R: " + runs + ", W: " + wickets + ", ECO: " + economy;
        
        System.out.println("\n------ BATTING ------"+ "\n"+bow+"\n------ BOWLING ------"+ "\n" +bow);
    	
    	
//    	String bat[] = {"Abhishek Sharma - R: 48, B: 15, 4s: 6, 6s: 3, SR: 320.00","Glenn Maxwell - Over: 1, M: 0, R: 16, W: 0, ECO: 16.00"};
//    	String bt="";
//        for(String a:bat)
//        {
//        	bt=bt+ "\n" +a;
//        }
//    	
//    	System.out.println(bt);
    }
}
