
public class DepositController {
	Screen screen;
	Keypad keypad;
	MoneySlot moneyslot;
	BankDriver bankDriver;
    public DepositController(Screen screen,Keypad keypad,MoneySlot moneyslot,BankDriver bankDriver) {
    	this.keypad=keypad;
    	this.bankDriver=bankDriver;
    	this.screen=screen;
    	this.moneyslot=moneyslot;
    }
	public void depositChoice(int card) {
		// TODO Auto-generated method stub
		this.screen.displaym("Insert Ammount for Deposit");
		this.keypad.activate(true);
		int ammount=this.keypad.getCommand();
		this.moneyslot.activate(true);
		this.bankDriver.deposit2Account(card, ammount);
	}

}
