package day11;
import java.util.Random;
interface Drawable {
	 void draw();		//오버라이딩 시 실수 많이 하는 것 "접근제어자"
}						//접근제어자를 (default)를 자손에서도 똑같이 주면 안됨..
						//(default) 같이 보이지만 컴파일시 자동 public 붙음..따라서 public 줘야함
class Rect implements Drawable {
	public void draw() {	//여기서 인터페이스의 메서드를 오버라이딩 하지 않으면 클래스 성립 안됨 
		System.out.println("사각형을 그립니다.");
	}
}
class Circle implements Drawable {
	public void draw() {
		System.out.println("원을 그립니다.");
	}
}
class Diamond implements Drawable  {
	public void draw() {
		System.out.println("마름모를 그립니다.");
	}
}
public class DrawableTest {
	public static void main(String[] args) {
		Random rand = new Random();
		int num = rand.nextInt(3);
		Drawable d = null;
		if(num == 0)
			d = new Rect();
		else if(num == 1)
			d = new Circle();		
		else if(num == 2)
			d = new Diamond();		
		output(d);
	}
	public static void output(Drawable d){
		System.out.println("전달된 객체의 클래스명 : "+
	                        d.getClass().getName());
		d.draw();
	}
}



