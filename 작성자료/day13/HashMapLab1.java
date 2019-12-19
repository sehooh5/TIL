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

			System.out.println("나라 이름을 입력하세요");
			str = sc.next();
			if (hash.containsKey(str) == false) {
				System.out.println("인구 수를 입력하세요");
				in = sc.nextInt();
				System.out.println("저장되었습니다.");
				hash.put(str, in);

			} else
				System.out.printf("나라명 %s는 이미 저장되어 있습니다.", str);

		}

		System.out.println("5개가 모두 입력되었습니다.");
		System.out.print("입력된 데이터 : ");

		Iterator<String> iter = hash.keySet().iterator();
		while (iter.hasNext()) {
			String st = iter.next();
			System.out.print(iter.hasNext() ? st + "(" + hash.get(st) + ")," : "("+st + ")");

		}
		sc.close();

	}

}
