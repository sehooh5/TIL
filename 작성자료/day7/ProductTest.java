package day7;
class Product{
	String name;
	int balance;
	int price;
	Product(){
		this("µàÅ©ÀÎÇü",5,10000);
//		name = "µàÅ©ÀÎÇü";
//		balance = 5;
//		price = 10000;
	}
	Product(String name,int balance, int price){
		this.name = name;
		this.balance = balance;
		this.price = price;
	}
	String getName() {
//		String info = name;
//		return info;
		return name;
	}
	int getBalance() {
//		int info = balance;
//		return info;
		return balance;
	}
	int getPrice() {
//		int info = price;
//		return info;
		return price;
	}
}
public class ProductTest {

	public static void main(String[] args) {
		Product[] p = new Product[5];
		p[0]=new Product();
		p[1]=new Product("µàÅ©¿µÈ¥",1,20000);
		p[2]=new Product("µàÅ©³ª¹«",2,30000);
		p[3]=new Product("µàÅ©Áý¾È",2,25469);
		p[4]=new Product("µàÅ©ÀüºÎ",0,99999);
		for(int i=0;i<p.length;i++)
		System.out.printf("%s\t%d\t%,d¿ø\n",p[i].getName(),p[i].getBalance(),p[i].getPrice());
		System.out.println();
		for(Product data:p)
			System.out.printf("%s\t%d\t%,d¿ø\n",data.getName(),data.getBalance(),data.getPrice());
	}

}
