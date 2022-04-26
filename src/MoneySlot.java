
public class MoneySlot {
 boolean activate;
 int money;
 //Εφοδιάζω το μηχάνημα με ένα χρηματικό ποσό
 public MoneySlot(){
	 money=500;
 }
	public void activate(boolean a) {
		// TODO Auto-generated method stub
		this.activate=a;
	}
	public boolean dispenseMoney(int ammount) {
		if(ammount>money) {//αν δεν έχω το κατάλληλο χρηματικό ποσό για κάποιο λόγο δεν μπορεί να αναληφθεί το ποσό
			return false;
		}
		else {
			money=500;
			return true;
		}
		
	}
	public boolean ReceiveMoney() {
		return true;//methodos oti elava ta lefta
	}
}
