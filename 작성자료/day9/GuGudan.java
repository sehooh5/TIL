package day9;
import day6.MethodLab3;
class Multiplication {
	private int dan;			//이 클래스에서만 사용 가능
	private int number;
	Multiplication() {}
	Multiplication(int dan) {
		this.dan = dan;
	}
	Multiplication(int dan, int number){
		this.dan = dan;
		this.number = number;
	}
	public void printPart() {
		if (number == 0) {	       
			for(int n=1; n <= 9; n++)
				System.out.print("\t"+dan + "*" + n+ "="+dan*n);
			System.out.println();
		} else {
			System.out.println(dan * number);
		}
	}
}
class GuGuDanExpr extends Multiplication{
	GuGuDanExpr(){}
	GuGuDanExpr(int dan){
		super(dan);
	}
	GuGuDanExpr(int dan, int number){
		super(dan,number);
	}
	static void printAll() {	//GuGuDanExpr을 호출하지 않아도 사용 가능한 static 메서드
		int dan;				//사용법  =  Class이름.메서드이름();
		int num;
		for(dan=1;dan<=9;dan++) {
			for(num=1;num <=9; num++)
				System.out.print(dan+"x"+num+"="+dan*num+"\t");
			System.out.println();
		}
	}
}


public class GuGudan {

	public static void main(String[] args) {
		int dan = (int)(MethodLab3.getRandom(20));
		int num = (int)(MethodLab3.getRandom(20));
		System.out.println(dan+", "+num);
		if(0<dan&&dan<=10) { 
			if(0<num&&num<10) {
				GuGuDanExpr p = new GuGuDanExpr(dan,num);
				System.out.print(dan+"*"+num+"=");
				p.printPart();
			}else if(10<=num) {
				GuGuDanExpr p = new GuGuDanExpr(dan);
				System.out.print(dan+"단 :");
				p.printPart();
			}
		}else 
			GuGuDanExpr.printAll();
			
		
		

	}

}
