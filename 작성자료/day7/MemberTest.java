package day7;

public class MemberTest {

	public static void main(String[] args) {
		Member d1 = new Member();
		d1.name = "�︪��";
		d1.account = "������1";
		d1.passwd = "secret";
		d1.birthyear = 910208;
		System.out.printf("ȸ�� 1 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);
		d1 = new Member();
		d1.name = "������";
		d1.account = "�̹鸮1";
		d1.passwd = "topsecret";
		d1.birthyear = 910101;
		System.out.printf("ȸ�� 2 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);
		d1 = new Member();
		d1.name = "�ܷο�";
		d1.account = "���ϳ�1";
		d1.passwd = "toptopsecret";
		d1.birthyear = 010101;
		System.out.printf("ȸ�� 3 : %s(%s,%s,%d)\n", d1.name, d1.account, d1.passwd, d1.birthyear);

	}

}
