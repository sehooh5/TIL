package day13;

import java.util.HashSet;
import java.util.Iterator;

public class LottoMachine2 {

	public static void main(String[] args) {

		
		HashSet<Integer> lotto = new HashSet<>();
		
		while(lotto.size()!=10) {
		lotto.add((int)(Math.random()*21)+10);
		}
		
		Iterator<Integer> iterator = lotto.iterator();
		
		System.out.printf("오늘의 로또번호 : [%d",iterator.next());
		while(iterator.hasNext()) {
			int i = iterator.next();
			System.out.printf(", %d",i);
		}
		System.out.print("]");
	}

}
