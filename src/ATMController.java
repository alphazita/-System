//Αναπαράσταση ΑTM.Αυτή είναι μια κλάση που ελέγχει την συμπεριφορά των interface object
//που αφορούν τα διάφορα μέλη του ATM:CardReader,MoneySlot,ReceiptSlot,Keypad,Screen και BankDriver
//που χρησιμοποιείται για την διεπαφή με την τράπεζα.
//Ανάλογα τον τύπο της συναλλαγής που ζητάει ο χρήστης ο έλεγχος μεταφέρετε σε μια από τις κλάσεις 
//WithdrawController,DepositController,BalanceController.Στην ουσία αυτές είναι οι λειτουργίες που εκτελεί
//το ATM και όταν ολοκληρωθεί η εκτέλεση των λειτουργιών ο έλεγχος επιστρέφει εδώ.
public class ATMController {
	private static boolean authentication;//μεταβλητή για την επικύρωση του χρήστη
	private static  int numberCard;//για τις ανάγκες της άσκησης θα παίρνω από τον χρήστη τον αριθμό κάρτας για να τον στέλνω στην τράπεζα
	private static  Screen screen;
	private static  Keypad keypad;
	private static  MoneySlot moneySlot;
	private static  ReceiptSlot receiptSlot;
	private static CardReader cardReader;
	private static BankDriver bankDriver;//ένας driver για επικοινωνία με την τράπεζα
	private static WithdrawController withdrawal;
	private static DepositController deposit;
	private static BalanceInquiry balance;
	
	//Με τον constructor αρχικοποιώ τα instances των κλάσεων μου
	public ATMController(){
		numberCard=0;//θέτω μηδέν τον αριθμό της κάρτας γιατί δεν έχω κάποιο πελάτη
		authentication=false;
		screen= new Screen();
		keypad=new Keypad();
		moneySlot=new MoneySlot();
		cardReader=new CardReader();
		bankDriver=new BankDriver();
		receiptSlot=new ReceiptSlot();
		//σε αυτά τα controller object μας περνάνε σαν ορίσματα το screen to moneySlot το Κeypad 
		//και το bankDriver
		withdrawal=new WithdrawController(screen,keypad,moneySlot,bankDriver);
		deposit=new DepositController(screen,keypad,moneySlot,bankDriver);
		balance=new BalanceInquiry(screen,keypad,bankDriver);
		
	}
	//Αρχικοποίηση του Atm
	public static void run() {
		while(true) {
			while(!authentication) {
				screen.displaym("Welcome");
				//Ταυτοποιείται πρώτα η κάρτα
				boolean validCard=validateCard();
				if(validCard) {
					//επόμενο είναι να ταυτοποιηθεί το Pin
					pinVerification();
				}
				else{
					screen.displaym("Invalid Card for this Bank");
					//απόρριψη κάρτας
					cardReader.cardReject();
				}
			}
			//διαδικασία συναλλαγής
			boolean exit=false;//θα ελέγχω την έξοδο από το σύστημα
			//boolean success=false;//μεταβλητή ελέγχου της επιτυχίας συναλλαγής
			int choice=0;//μεταβλητή που παίρνει την επιλογή του χρήστη
			while(!exit) {
				screen.displaym("Main Menu");
				screen.displaym("1:Withdrawl");
				screen.displaym("2:Deposit");
				screen.displaym("3:Balance Inquiry");
				screen.displaym("4:Exit System");
				keypad.activate(true);
				choice= keypad.getCommand();
				if(choice==1) {
					//ο έλεγχος μεταφέρετε στην αντίστοιχη συναλλαγή
					withdrawal.withdrawChoice(numberCard);
					
				}
				else if(choice==2) {
					deposit.depositChoice(numberCard);	
				}
				else if(choice==3) {
					balance.balanceChoice(numberCard);	
				}
				else if (choice==4) {
					//έξοδος από το σύστημα
					exit=true;
				}
				else {
					screen.displaym("Invalid Choice.Try again.");
				}
			}
				//if(success) {//δηλαδή αν πραγματοποιήθηκε συναλλαγή
				
					screen.displaym("Do you want Receipt?");
					screen.displaym("1:Yes");
					screen.displaym("2:No");
					int choice2=keypad.getCommand();
					while(choice2!=1 && choice2!=2) {//για έλεγχο σωστής εντολής εισόδου θέλει 1 ή 2
						screen.displaym("Invalid Choice");
						screen.displaym("Do you want Receipt?");
						screen.displaym("1:Yes");
						screen.displaym("2:No");
						choice2=keypad.getCommand();
					}
					if(choice2==1) {
						receiptSlot.activate(true);
						receiptSlot.despenseReceipt();
					}
					else if((choice2==2)&&(choice!=3)) {
						//με την δεύτερη μεταβλητή θέλω να εξασφαλίσω ότι αν ο χρήστης είχε διαλέξει να δεί το υπόλοιπο
						//και δεν θέλει απόδειξη δεν θα του ξαναδείξω το υπόλοιπο στην οθόνη
						balance.balanceChoice(numberCard);
					}
				//}
				
			
			//προετοιμάζω για τον επόμενο πελάτη
			cardReader.cardReject();
			authentication=false;
			screen.displaym("Goodbye");
		}
	}
	private static boolean validateCard() {
		//διαδικασία ταυτοποίησης
		cardReader.activate(true);
		screen.displaym("Insert Your Card");
		screen.displaym("Insert your NumberCard");
		keypad.activate(true);
		numberCard=keypad.getCommand();
		return bankDriver.authenticateCard(numberCard);
	}
	private static void pinVerification() {
		screen.displaym("Insert Your Pin");
		keypad.activate(true);
		int pin=keypad.getCommand();
		authentication=bankDriver.authenticateUser(numberCard,pin);
		if(!authentication) {
			screen.displaym("Invalid Pin");
		}
	}

}
