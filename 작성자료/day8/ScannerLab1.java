package day8;
import java.util.Scanner;
public class ScannerLab1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
	
		int i3=0;
		System.out.println("ù ��° ���ڸ� �Է��ϼ��� : ");
		int i1 = sc.nextInt();
		System.out.println("�� ��° ���ڸ� �Է��ϼ��� : ");
		int i2 = sc.nextInt();
		System.out.println("������(+,-,*,/)�� �Է��ϼ��� : ");
		String st = sc.next();
		switch(st) {
		case "+" : i3 = i1+i2;
		break;
		case "-" : i3 = i1-i2;
		break;
		case "/" : i3 = i1/i2;
		break;
		case "*" : i3 = i1*i2;
		break;
		default : 
		System.out.println("+,-,*,/ �� �Է��ϼ�");
		sc.close();
		return;							///�߿�! ������ �ؿ��� �����! main method �̱⶧������� ����
		}
		System.out.printf("%d �� %d�� %s ���� ����� %d �Դϴ�.",i1,i2,st,i3);
		sc.close();
		
	}

}
