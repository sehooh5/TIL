package day6;

public class MethodLab2 {

	public static void main(String[] args) {
		int rand1;
		int rand2;
		for(int j=0;j<5;j++) {
			rand1 = (int)(Math.random()*30)+1;
			rand2 = (int)(Math.random()*30)+1;
		differN(rand1,rand2);
		System.out.println(rand1+" 와 "+rand2+" 의 차는 "+differN(rand1,rand2)+" 입니다.");
		}
	}
	static int differN(int num1, int num2) {	
		int differ = num1-num2;
		while(true) {
			if(differ>0) {
				break;
			}else if(differ<0) {
				differ=differ*(-1);
			}else differ = 0;
		}
		return differ;
	}

}
