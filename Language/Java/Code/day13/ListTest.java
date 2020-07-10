package day13;

import java.util.ArrayList;
import java.util.Iterator;

class CreateList {
	public ArrayList<Integer> convertList(int array[]){
		
		ArrayList<Integer> ary = new ArrayList<>();
		for(int i=10;i>=0;i--)
			ary.add(array[i]);
		return ary;
	}
}
public class ListTest {

	public static void main(String[] args) {
		
		CreateList array = new CreateList();
		int[] arr = {3,4,2,5,2,3,6,7,5,7,9};
		array.convertList(arr);
		
		Iterator<Integer> iter = array.convertList(arr).iterator();
		while ( iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
