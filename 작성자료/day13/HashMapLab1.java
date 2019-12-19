package day13;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class HashMapLab1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> hash = new HashMap<String, Integer>(5);
		String str = null;
		int in = 0;
		while(hash.size() !=5){

			System.out.println("���� �̸��� �Է��ϼ���");
			str = sc.next();
			if (hash.containsKey(str) == false) {
				System.out.println("�α� ���� �Է��ϼ���");
				in = sc.nextInt();
				System.out.println("����Ǿ����ϴ�.");
				hash.put(str, in);

			} else
				System.out.printf("����� %s�� �̹� ����Ǿ� �ֽ��ϴ�.", str);

		}

		System.out.println("5���� ��� �ԷµǾ����ϴ�.");
		System.out.print("�Էµ� ������ : ");

		Iterator<String> iter = hash.keySet().iterator();
		while (iter.hasNext()) {
			String st = iter.next();
			System.out.print(iter.hasNext() ? st + "(" + hash.get(st) + ")," : "("+st + ")");

		}
		sc.close();

	}

}
