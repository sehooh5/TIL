package day7;	
//////////////////////////Ŭ���� �� ��ü ����/////////////////////
class Student{
	String name;												//1�Ӽ�	 3���� �Ӽ�
	int age;													//2�Ӽ�
	String subject;												//3�Ӽ�	
	void printStudentInfo() {									//1���	 2���� ���(�޼���)
		System.out.println(name+"�л��� ���̰� "+age+" �Դϴ�.");
	}
	void study() {												//2�ⴱ	 
		System.out.println(name+"�л��� "+subject+" ������ �н��մϴ�.");
	}
}
public class StudentTest {						//public calls�� �� �Ѱ�!

	public static void main(String[] args) {
		Student st1 = new Student();		//��ü ���� ��
		System.out.println(st1);			//day7.Student@15db9742
		System.out.println(st1.name);
		System.out.println(st1.age);
		System.out.println(st1.subject);
		st1.printStudentInfo();
		st1.study();
		st1.name="��ũ";
		st1.age=24;
		st1.subject="HTML5";
		st1.printStudentInfo();
		st1.study();
		
		Student st2 = new Student();		
		System.out.println(st2);			
		System.out.println(st2.name);
		System.out.println(st2.age);
		System.out.println(st2.subject);
		st2.printStudentInfo();
		st2.study();
		st2.name="�ν�";
		st2.age=30;
		st2.subject="CSS3";
		st2.printStudentInfo();
		st2.study();
		System.out.println("st2=st1 ����");
		st2=st1;
		System.out.println(st1);
		System.out.println(st2);
		st1.printStudentInfo();
		st1.study();
		st2.printStudentInfo();
		st2.study();
		}

}
