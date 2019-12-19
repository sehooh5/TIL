package day7;	
//////////////////////////클래스 및 객체 생성/////////////////////
class Student{
	String name;												//1속성	 3개의 속성
	int age;													//2속성
	String subject;												//3속성	
	void printStudentInfo() {									//1기능	 2개의 기능(메서드)
		System.out.println(name+"학생은 나이가 "+age+" 입니다.");
	}
	void study() {												//2기눙	 
		System.out.println(name+"학생은 "+subject+" 과목을 학습합니다.");
	}
}
public class StudentTest {						//public calls는 꼭 한개!

	public static void main(String[] args) {
		Student st1 = new Student();		//객체 생성 식
		System.out.println(st1);			//day7.Student@15db9742
		System.out.println(st1.name);
		System.out.println(st1.age);
		System.out.println(st1.subject);
		st1.printStudentInfo();
		st1.study();
		st1.name="듀크";
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
		st2.name="턱시";
		st2.age=30;
		st2.subject="CSS3";
		st2.printStudentInfo();
		st2.study();
		System.out.println("st2=st1 수행");
		st2=st1;
		System.out.println(st1);
		System.out.println(st2);
		st1.printStudentInfo();
		st1.study();
		st2.printStudentInfo();
		st2.study();
		}

}
