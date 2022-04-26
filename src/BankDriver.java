
public class BankDriver {
	Bank bank;
	public BankDriver() {
		bank=new Bank();
	}
	public boolean authenticateCard(int numberCard) {
		// TODO Auto-generated method stub
		
		//return bank.authentication();
		
		//return bank.authentication(numberCard);
		
		return bank.authentication(numberCard);
	}

	public boolean authenticateUser(int numberCard,int pin) {
		// TODO Auto-generated method stub
		return bank.authenticationPIN(numberCard,pin);
	}

	public boolean deposit2Account(int numberCard,int ammount) {//επιστρέφει true γιατί πραγματοποίηση την ενεργεια
		return true;
	}
	//πραγματοποίησε την ενέργεια και επιστρέφει True
	public boolean reduceAmmount(int numberCard,int ammount) {
		return true;
	}

	public int getAvailableBalance(int numberCard) {
		return bank.balance(numberCard);
	}
}
