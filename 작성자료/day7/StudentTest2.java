package day7;	
//////////////////////생성자 메서드/////////////////////
class StudentNew{
	String name;
	int age;
	String subject;
	StudentNew(String p1,int p2, String p3){			//생성자 메서드 정의 (리턴값 없다)
		name = p1;
		age = p2;
		subject = p3;
	}
	void printStudentInfo() {									
		System.out.println(name+"학생은 나이가 "+age+" 입니다.");
	}
	void study() {												
		System.out.println(name+"학생은 "+subject+" 과목을 학습합니다.");
	}
	void study(int hour) {												
		System.out.println(name+"학생은 "+subject+" 과목을 "+hour+" 동안 학습합니다.");
	}
}
public class StudentTest2 {						

	public static void main(String[] args) {
		StudentNew st1 = new StudentNew("듀크",24,"자바");	
		System.out.println(st1);
		st1.study(2);
		StudentNew st2 = new StudentNew("둘리",100,"자바");	
		System.out.println(st2);
		st2.study();
		
		}
}
