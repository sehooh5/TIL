package day2;

public class OperTest1 {

	public static void main(String[] args) {

		// 증감연산자 : 증가연산자(++), 감소연산자(--)
		
		int su1, su2, su3, su4;
		su1 = 10;
		su2 = 10;
		su3 = 10;
		su4 = 10;
		
		System.out.println(su1);
		System.out.println(++su1);  // 증가 시키고 su1값 증가
		System.out.println(su1++);
		System.out.println(su1++);
		System.out.println(++su1);
		System.out.println(--su1);
		
		System.out.println(su2);
		System.out.println(su2++);  // su2 값 전달하고 나중에 증가
		System.out.println(su2++);  // su2 값 전달하고 나중에 증가
		System.out.println(su2++);  // su2 값 전달하고 나중에 증가
		System.out.println(su2--);  // su2 값 전달하고 나중에 감소
		System.out.println(su2);	// 12
		
		System.out.println(su3);	// 10
		System.out.println(su3++);  // 10
		System.out.println(++su3);  // 12
		System.out.println(su3++);  // 12
		System.out.println(su3--);  // 13
		System.out.println(--su3);	// 11
		
		System.out.println(su4);	// 10
		++su4;
		System.out.println(su4);  // 11
		su4++;
		System.out.println(su4);  // 12
		// ++10;  can't use
		
		
	}

}
