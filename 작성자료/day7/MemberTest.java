package day7;

public class MemberTest {

	public static void main(String[] args) {
		Member d1 = new Member();
		d1.name = "울릉도";
		d1.account = "동남쪽1";
		d1.passwd = "secret";
		d1.birthyear = 910208;
		System.out.printf("회원 1 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);
		d1 = new Member();
		d1.name = "뱃길따라";
		d1.account = "이백리1";
		d1.passwd = "topsecret";
		d1.birthyear = 910101;
		System.out.printf("회원 2 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);
		d1 = new Member();
		d1.name = "외로운";
		d1.account = "섬하나1";
		d1.passwd = "toptopsecret";
		d1.birthyear = 010101;
		System.out.printf("회원 3 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);

	}

}
