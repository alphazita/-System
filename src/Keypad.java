import java.util.Scanner;
public class Keypad {
	Scanner input;
	public Keypad(){
		input = new Scanner(System.in);
	}
	boolean active = false;
	public void activate(boolean a) {
		this.active=a;
		// TODO Auto-generated method stub	
	}

	public int getCommand() {
		// TODO Auto-generated method stub
		return input.nextInt();
	}

}
