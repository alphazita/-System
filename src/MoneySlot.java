
public class MoneySlot {
 boolean activate;
 int money;
 //�������� �� �������� �� ��� ��������� ����
 public MoneySlot(){
	 money=500;
 }
	public void activate(boolean a) {
		// TODO Auto-generated method stub
		this.activate=a;
	}
	public boolean dispenseMoney(int ammount) {
		if(ammount>money) {//�� ��� ��� �� ��������� ��������� ���� ��� ������ ���� ��� ������ �� ��������� �� ����
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
