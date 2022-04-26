
public class WithdrawController {
	Screen screen;
	Keypad keypad;
	MoneySlot moneyslot;
	BankDriver bankDriver;
    public WithdrawController(Screen screen,Keypad keypad,MoneySlot moneyslot,BankDriver bankDriver) {
    	this.keypad=keypad;
    	this.bankDriver=bankDriver;
    	this.screen=screen;
    	this.moneyslot=moneyslot;
    }

	public void withdrawChoice(int card) {
		// TODO Auto-generated method stub
		int balance;
		this.screen.displaym("Insert Ammount for Withdrawal");
		int ammount=this.keypad.getCommand();
		balance=bankDriver.getAvailableBalance(card);
		if((balance-ammount)>0) {
			this.moneyslot.activate(true);
			this.bankDriver.reduceAmmount(card, ammount);
			if(!this.moneyslot.dispenseMoney(ammount)) {
				this.screen.displaym("Not enough money for dispense");
			}
		}
		else {
			this.screen.displaym("Invalid Transaction");
		}
	}

}
