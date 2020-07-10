package day11.case2;

public class SamsungTV implements TV{//implements를 사용하여 인터페이스에 가서 오버라이딩 할 수 있다. 
	public void powerOn(){
		System.out.println("SamsungTV --- 전원을 켠다.");
	}
	public void powerOff(){
		System.out.println("SamsungTV --- 전원을 끈다.");
	}
	public void volumnUp(){
		System.out.println("SamsungTV --- 소리를 높인다.");
	}
	public void volumnDown(){
		System.out.println("SamsungTV --- 소리를 낮춘다.");
	}

}
