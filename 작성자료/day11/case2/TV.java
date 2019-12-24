package day11.case2;

public interface TV {				//interface 사용
	public void powerOn();			//abstract 를 안써줘도 자동으로 abstract 메서드가 된다.
	public void powerOff();			//작용하는 메서드를 규격화 하는 것이다
	public void volumnUp();
	public void volumnDown();
}
