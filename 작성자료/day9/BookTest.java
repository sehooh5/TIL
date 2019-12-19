package day9;
class Book{
	String title;
	String author;
	private int price;				////*** 외부에서 접근 못하게 private 설정
	Book(){

		this("자바의 정석","남궁 성",30000);
	}
	Book(String title,String author,int price){
		this.title = title;						
		this.author = author;
		setPrice(price);					//set Price 메서드를 사용하여 제대로된 price 생성
	}
	void setPrice(int price) {				//setter 설정 --> 무조건 void
		if (price < 0)						//setter 설정하여 잘못된 값 걸러내기
			this.price = -price;
		else
			this.price = price;
	}
	String getBookInfo() {
		return "책이름 : "+title+" "+author+" "+price;
	}
	void BookTest(){
		System.out.println(getBookInfo());
	}
}
public class BookTest {

	public static void main(String[] args) {
		Book info1 = new Book();
		info1.setPrice(-30000);
		
		System.out.println(info1.getBookInfo());

	}

}
