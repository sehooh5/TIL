package day7;	
//////////////////////持失切 五辞球 神獄稽漁しししししししし/////////////////////
class StudentNew2{
	String name;
	int age;
	String subject;
	StudentNew2(){								// 神獄稽漁しししししししししししししししししししししししししししし

	}
	StudentNew2(String p1,int p2){			
		name = p1;
		age = p2;
		subject = "切郊";
	}
	StudentNew2(String p1,int p2, String p3){			
		name = p1;
		age = p2;
		subject = p3;
	}
	void printStudentInfo() {									
		System.out.println(name+"俳持精 蟹戚亜 "+age+" 脊艦陥.");
	}
	void study() {												
		System.out.println(name+"俳持精 "+subject+" 引鯉聖 俳柔杯艦陥.");
	}
	void study(int hour) {												
		System.out.println(name+"俳持精 "+subject+" 引鯉聖 "+hour+"獣娃 疑照 俳柔杯艦陥.");
	}
}
public class StudentTest3 {						

	public static void main(String[] args) {
		StudentNew2 st1 = new StudentNew2("朽滴",24);	
//		System.out.println(st1);
		st1.study(2);
		StudentNew2 st2 = new StudentNew2("却軒",100,"硲責");	
//		System.out.println(st2);
		st2.study(2);
		StudentNew2 st3 = new StudentNew2();	
//		System.out.println(st3);
		st3.study(2);
		
		}
}
