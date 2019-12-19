package day11;

import java.util.Random;

class DuplicateException extends Exception {
	DuplicateException() {
		super("�ߺ��� �ζ� ��ȣ�� �߻��߽��ϴ�."); // string ���� ��ӹ����� ���� �޽����� ó���Ѵ�.
	}
}

class LottoMachine extends Exception {
	private int nums[];

	LottoMachine() { // 6���� int �� ���� ���� �迭 ����
		this.nums = new int[6];
	}

	public void createLottoNums() { // �迭�� ���� �ֱ�
		Random ran = new Random();
		for (int i = 0; i < 6; i++)
			nums[i] = ran.nextInt(20) + 1;
	}

	public void checkLottoNums() throws DuplicateException { // �޼��� ����� throws �� ���� ���� Ŭ������ ����� �� ���ִ�
		for (int i = 0; i < 5; i++) { //
			for (int j = i + 1; j < 5; j++) {
				if (nums[i] == nums[j])
					throw new DuplicateException(); // ���ܸ� �߻���Ű������ throw�� �߻���Ŵ..throw �ڿ��� �� ���ܰ�ü�� �־���Ѵ�.
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
		LottoMachine lotto = new LottoMachine(); // �迭����
		lotto.createLottoNums(); // ���� �ֱ�

		try { // ���� Ȯ���ϴ� try-catch ����
			lotto.checkLottoNums(); // ��ȸ Ȯ���ϴ� checkLottoNums �޼��忡�� �����߻��ϸ� catch
		} catch (DuplicateException e) {
			System.out.println(e.getMessage()); // e.getMessage�� ������ �޽���
			return; // return �ִ� ������? �������� ���⼭ ���� ����
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
