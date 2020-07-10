package day8;
import day6.MethodLab3;
class SalaryExpr{
	int bonus;
	SalaryExpr(){
		bonus = 0;
	}
	SalaryExpr(int bonus){
		this.bonus = bonus;
	}
	int getSalary(int grade) {
		int i=0;
		if(grade==1) {
			 i=bonus+100;
		}else if(grade==2) {
			 i=bonus+90;
		}else if(grade==3) {
			 i=bonus+80;
		}else 
			 i=bonus+70;
		return i;
	}
}
public class SalaryExam {
	public static void main(String[] args) {
		int month = (int)(MethodLab3.getRandom(12));
		int grade = (int)(MethodLab3.getRandom(4));
		switch(month%2) {
		case 0 : 
			SalaryExpr p = new SalaryExpr(100);
			System.out.printf("%d월 %d등급의 월급은 %d 입니다.",month,grade,p.getSalary(grade));
			break;
		case 1 : 
			SalaryExpr i= new SalaryExpr();
			System.out.printf("%d월 %d등급의 월급은 %d 입니다.",month,grade,i.getSalary(grade));
			
		}
	}
}
