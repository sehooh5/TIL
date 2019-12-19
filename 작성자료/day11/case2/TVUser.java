package day11.case2;

public class TVUser {
	public static void main(String[] args) {
		TV tv = new SamsungTV();	//Lg 혹은 Samsung 을 서로 바꿔도 에러가 나지 않는다
		tv.powerOn();
		tv.volumnUp();
		tv.volumnDown();
		tv.powerOff();
	}

}
