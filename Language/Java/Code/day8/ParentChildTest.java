package day8;
class Parent{			//java.lang.Object 클래스가 부모가 된다. 그의 멤버들을 상속받는다.	
	//부모 클래스의 메서드를 오버라이딩해서 사용 가능 (public 꼭 붙이고, 이름 똑같이, 매개변수도 똑같이!!
	int x =1, y = 2;
	public String toString() {
		return "Parent 클래스의 객체 입니당";
	}
}
class Child extends Parent {			//부모 지정하는 방법 class 헤더에 + extends + 부모class이름
	int x = 10;
	void printInfo() {
		int x = 100;
		System.out.println(x);					//100
		System.out.println(this.x);				//10
		System.out.println(super.x);			//1
		System.out.println(y);					//2	없으면 부모 위 클래스 가서 찾음
		System.out.println(this.y);				//2
		System.out.println(super.y);			//2
//		System.out.println(z);
	}
	public String toString() {
		return "Class 클래스의 객체 입니당";
	}
}
public class ParentChildTest {

	public static void main(String[] args) {
		Parent p = new Parent();
		System.out.println(p.toString());		//toString 은 객체를 문자로 바꿔주는 메서드
		System.out.println(p);
		System.out.println("출력1"+p);
		
		Card c = new Card();
		System.out.println(c.toString());
		System.out.println("출력2"+c);
		
		java.util.Date d = new java.util.Date();
		System.out.println(d.toString());
		System.out.println("출력3"+d);
		
		Child ch = new Child();
		System.out.println("출력4"+ch);
		
		ch.printInfo();
		System.out.println(ch.x);
		System.out.println(ch.y);
	}

}
