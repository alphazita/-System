
public class BalanceInquiry {
	Screen screen;
	Keypad keypad;
	BankDriver bankDriver;
    public BalanceInquiry(Screen screen,Keypad keypad,BankDriver bankDriver) {
    	this.keypad=keypad;
    	this.bankDriver=bankDriver;
    	this.screen=screen;
    }
	public void balanceChoice(int card) {
		// TODO Auto-generated method stub
		String ammount=String.valueOf(this.bankDriver.getAvailableBalance(card));
		this.screen.displaym("Available Balance");	
		this.screen.displaym(ammount);
	}

}
