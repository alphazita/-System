//������������ �TM.���� ����� ��� ����� ��� ������� ��� ����������� ��� interface object
//��� ������� �� ������� ���� ��� ATM:CardReader,MoneySlot,ReceiptSlot,Keypad,Screen ��� BankDriver
//��� ��������������� ��� ��� ������� �� ��� �������.
//������� ��� ���� ��� ���������� ��� ������ � ������� � ������� ���������� �� ��� ��� ��� ������� 
//WithdrawController,DepositController,BalanceController.���� ����� ����� ����� �� ����������� ��� �������
//�� ATM ��� ���� ����������� � �������� ��� ����������� � ������� ���������� ���.
public class ATMController {
	private static boolean authentication;//��������� ��� ��� ��������� ��� ������
	private static  int numberCard;//��� ��� ������� ��� ������� �� ������ ��� ��� ������ ��� ������ ������ ��� �� ��� ������ ���� �������
	private static  Screen screen;
	private static  Keypad keypad;
	private static  MoneySlot moneySlot;
	private static  ReceiptSlot receiptSlot;
	private static CardReader cardReader;
	private static BankDriver bankDriver;//���� driver ��� ����������� �� ��� �������
	private static WithdrawController withdrawal;
	private static DepositController deposit;
	private static BalanceInquiry balance;
	
	//�� ��� constructor ���������� �� instances ��� ������� ���
	public ATMController(){
		numberCard=0;//���� ����� ��� ������ ��� ������ ����� ��� ��� ������ ������
		authentication=false;
		screen= new Screen();
		keypad=new Keypad();
		moneySlot=new MoneySlot();
		cardReader=new CardReader();
		bankDriver=new BankDriver();
		receiptSlot=new ReceiptSlot();
		//�� ���� �� controller object ��� ������� ��� �������� �� screen to moneySlot �� �eypad 
		//��� �� bankDriver
		withdrawal=new WithdrawController(screen,keypad,moneySlot,bankDriver);
		deposit=new DepositController(screen,keypad,moneySlot,bankDriver);
		balance=new BalanceInquiry(screen,keypad,bankDriver);
		
	}
	//������������ ��� Atm
	public static void run() {
		while(true) {
			while(!authentication) {
				screen.displaym("Welcome");
				//������������� ����� � �����
				boolean validCard=validateCard();
				if(validCard) {
					//������� ����� �� ������������ �� Pin
					pinVerification();
				}
				else{
					screen.displaym("Invalid Card for this Bank");
					//�������� ������
					cardReader.cardReject();
				}
			}
			//���������� ����������
			boolean exit=false;//�� ������ ��� ����� ��� �� �������
			//boolean success=false;//��������� ������� ��� ��������� ����������
			int choice=0;//��������� ��� ������� ��� ������� ��� ������
			while(!exit) {
				screen.displaym("Main Menu");
				screen.displaym("1:Withdrawl");
				screen.displaym("2:Deposit");
				screen.displaym("3:Balance Inquiry");
				screen.displaym("4:Exit System");
				keypad.activate(true);
				choice= keypad.getCommand();
				if(choice==1) {
					//� ������� ���������� ���� ���������� ���������
					withdrawal.withdrawChoice(numberCard);
					
				}
				else if(choice==2) {
					deposit.depositChoice(numberCard);	
				}
				else if(choice==3) {
					balance.balanceChoice(numberCard);	
				}
				else if (choice==4) {
					//������ ��� �� �������
					exit=true;
				}
				else {
					screen.displaym("Invalid Choice.Try again.");
				}
			}
				//if(success) {//������ �� ���������������� ���������
				
					screen.displaym("Do you want Receipt?");
					screen.displaym("1:Yes");
					screen.displaym("2:No");
					int choice2=keypad.getCommand();
					while(choice2!=1 && choice2!=2) {//��� ������ ������ ������� ������� ����� 1 � 2
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
						//�� ��� ������� ��������� ���� �� ���������� ��� �� � ������� ���� �������� �� ��� �� ��������
						//��� ��� ����� �������� ��� �� ��� ��������� �� �������� ���� �����
						balance.balanceChoice(numberCard);
					}
				//}
				
			
			//����������� ��� ��� ������� ������
			cardReader.cardReject();
			authentication=false;
			screen.displaym("Goodbye");
		}
	}
	private static boolean validateCard() {
		//���������� ������������
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
