package day11;

import java.util.Random;

class DuplicateException extends Exception {
	DuplicateException() {
		super("중복된 로또 번호가 발생했습니다."); // string 형을 상속받으면 예외 메시지로 처리한다.
	}
}

class LottoMachine extends Exception {
	private int nums[];

	LottoMachine() { // 6개의 int 형 원소 갖는 배열 생성
		this.nums = new int[6];
	}

	public void createLottoNums() { // 배열에 원소 넣기
		Random ran = new Random();
		for (int i = 0; i < 6; i++)
			nums[i] = ran.nextInt(20) + 1;
	}

	public void checkLottoNums() throws DuplicateException { // 메서드 헤더에 throws 를 보면 예외 클래스가 어떤건지 알 수있다
		for (int i = 0; i < 5; i++) { //
			for (int j = i + 1; j < 5; j++) {
				if (nums[i] == nums[j])
					throw new DuplicateException(); // 예외를 발생시키기위해 throw를 발생시킴..throw 뒤에는 꼭 예외객체가 있어야한다.
				// DuplicateException e = new DuplicateException();
				// throw e;
			}

		}
	}

	public int[] getNums() {
		return nums;
	}
}

public class LottoGame {

	public static void main(String[] args) {
		LottoMachine lotto = new LottoMachine(); // 배열생성
		lotto.createLottoNums(); // 난수 넣기

		try { // 예외 확인하는 try-catch 구문
			lotto.checkLottoNums(); // 예회 확인하는 checkLottoNums 메서드에서 오류발생하면 catch
		} catch (DuplicateException e) {
			System.out.println(e.getMessage()); // e.getMessage는 오류의 메시지
			return; // return 주는 이유는? 오류나면 여기서 실행 종료
		}
		int nums[] = lotto.getNums();
		for (int i = 0; i < nums.length; i++) {
			System.out.print(i == nums.length - 1 ? nums[i] : nums[i] + ",");

//		lotto.getNums();
//		for (int i = 0; i < lotto.getNums().length; i++) {
//			System.out.print(i == lotto.getNums().length - 1 ? lotto.getNums()[i] : lotto.getNums()[i] + ",");
		}
	}

}
