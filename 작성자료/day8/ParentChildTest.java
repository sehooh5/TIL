package day8;
class Parent{			//java.lang.Object Ŭ������ �θ� �ȴ�. ���� ������� ��ӹ޴´�.	
	//�θ� Ŭ������ �޼��带 �������̵��ؼ� ��� ���� (public �� ���̰�, �̸� �Ȱ���, �Ű������� �Ȱ���!!
	int x =1, y = 2;
	public String toString() {
		return "Parent Ŭ������ ��ü �Դϴ�";
	}
}
class Child extends Parent {			//�θ� �����ϴ� ��� class ����� + extends + �θ�class�̸�
	int x = 10;
	void printInfo() {
		int x = 100;
		System.out.println(x);					//100
		System.out.println(this.x);				//10
		System.out.println(super.x);			//1
		System.out.println(y);					//2	������ �θ� �� Ŭ���� ���� ã��
		System.out.println(this.y);				//2
		System.out.println(super.y);			//2
//		System.out.println(z);
	}
	public String toString() {
		return "Class Ŭ������ ��ü �Դϴ�";
	}
}
public class ParentChildTest {

	public static void main(String[] args) {
		Parent p = new Parent();
		System.out.println(p.toString());		//toString �� ��ü�� ���ڷ� �ٲ��ִ� �޼���
		System.out.println(p);
		System.out.println("���1"+p);
		
		Card c = new Card();
		System.out.println(c.toString());
		System.out.println("���2"+c);
		
		java.util.Date d = new java.util.Date();
		System.out.println(d.toString());
		System.out.println("���3"+d);
		
		Child ch = new Child();
		System.out.println("���4"+ch);
		
		ch.printInfo();
		System.out.println(ch.x);
		System.out.println(ch.y);
	}

}
