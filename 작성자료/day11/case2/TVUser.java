package day11.case2;

public class TVUser {
	public static void main(String[] args) {
		TV tv = new SamsungTV();	//Lg Ȥ�� Samsung �� ���� �ٲ㵵 ������ ���� �ʴ´�
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
	}

}
