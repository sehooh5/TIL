package day4;

public class ArrayLab1 {

	public static void main(String[] args) {
		int ary[] = new int[10];
		for(int i=0;i<ary.length;i++) {
		System.out.print(ary[i]+" ");
		}System.out.println();
		for(int i=0;i<ary.length;i++) {
		ary[i] = i*10+10;
		System.out.print(ary[i]+" ");
		}System.out.println();
		for(int i=ary.length-1;i>=0;i--) {          // ��� ary.length ����� �� �ִ���?
		ary[i] = i*10+10;
		System.out.print(ary[i]+" ");
		}System.out.println();
		for(int i=1;i<ary.length;i+=2) {
		ary[i] = i*10+10;
		System.out.print(ary[i]+" ");
		}
		
	}

}
