package day10;
import java.util.Scanner;
public class ScannerLab2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i=0;
		int i1 ;
		int i2 ;
		int i3=0;
		boolean flag = false;
		//���ڿ� �����ڸ� �Է¹޾� ó���ϴ� ����� 3�� �ݺ��ϵ��� �ڵ� �ۼ�
		for(i=0;i<3;i++) {
		System.out.println("ù ��° ���ڸ� �Է��ϼ��� : ");
		i1 = sc.nextInt();
		System.out.println("�� ��° ���ڸ� �Է��ϼ��� : ");
		i2 = sc.nextInt();
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
			flag =true;
		}
		if(flag == true) {
		System.out.println("+,-,*,/ �� �Է��ϼ�");
		}else
		System.out.printf("%d �� %d�� %s ���� ����� %d �Դϴ�.",i1,i2,st,i3);
		
	}sc.close();
	
}
}
