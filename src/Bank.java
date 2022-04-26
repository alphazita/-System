import java.util.Scanner;
import java.util.*; 
public class Bank {
	Scanner sc;
	String answer;
	int balance;
	public Bank(){
		sc = new Scanner(System.in);
	}
	    public boolean authentication(int num) {
	    	System.out.println("*******BANK********");
	    	answer=sc.nextLine();
	    	if(answer.equals("Yes") || answer.equals("yes")) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	   
	    public boolean authenticationPIN(int num,int pin) {
	    	System.out.println("*******BANK********");
	    	answer=sc.nextLine();
	    	if(answer.equals("Yes") || answer.equals("yes")) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	    
	    public int balance(int num) {
	    	System.out.println("*******BANK********");
	    	balance=sc.nextInt();
	    	return balance;
	    }
}
