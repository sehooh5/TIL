package day9;


	class Person{
		private String name;		//자손, 클래스 모두 접근 불가- Person 클래스 내에서만 사용
		Person(String name){
			this.name = name;
		}
		public String getInfo() {
			return name;
		}
}
		class Friend extends Person{
			private String phoneNum ;
			private String email;
			Friend(String name, String num, String add){
				super(name);
				this.phoneNum = num;
				this.email = add;
			}
			public String getInfo() {
				return super.getInfo()+"  "+phoneNum+"  "+email;
		}
	}
public class FriendTest {
	public static void main(String[] args) {
		
		Friend[] p = new Friend[5];
		p[0] = new Friend ("오세호","12345678","seho@seho.com");
		p[1] = new Friend ("오세호","12345678","seho@seho.com");
		p[2] = new Friend ("오세호","12345678","seho@seho.com");
		p[3] = new Friend ("오세호","12345678","seho@seho.com");
		p[4] = new Friend ("오세호","12345678","seho@seho.com");
		for(int i = 0; i<p.length ; i++)
			System.out.println(p[i].getInfo());
	}

}

//class Friend extends Person{
//	/*
//	private String phoneNum;
//	private String email;
//	Friend(String phoneNum, String email, String name){
//		super(name); // private인데 왜 접근 된 이유는?
//		this.phoneNum = phoneNum;
//		this.email = email;
//	}
//	*/
//	private String phoneNum;
//	private String email;
//	Friend(String x, String y, String z){
//		super(x); // private인데 왜 접근 된 이유는?
//		this.phoneNum = y;
//		this.email = z;
//	}
//	public String getInfo() {
//		return super.getInfo()+"\t"+phoneNum+"\t"+email;
//	}
//}
//public class FriendTest {
//	public static void main(String[] args) {
//		Friend[] f = new Friend[5]; // 변수 5개 설정
//		f[0] = new Friend("이영기","0000","ee249@naver.com");
//		f[1] = new Friend("영기","0000","ee24@naver.com");
//		f[2] = new Friend("영기","0000","ee249@navr.com");
//		f[3] = new Friend("이기","0000","ee249@navercom");
//		f[4] = new Friend("이","0000","ee249@nav.com");
//		System.out.println("이름      전화번호         메일주소");
//		System.out.println("-----------------------");
//		for(int i=0;i<5;i++) {
//			System.out.println(f[i].getInfo());
//		}
//	}
//}
