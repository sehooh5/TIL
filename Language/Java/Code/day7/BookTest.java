package day7;
class Book{
	String title;
	String author;
	int price;
	Book(){
//		title = "자바의 정석";
//		author = "남궁 성";
//		price = 30000;
		this("자바의 정석","남궁 성",30000);
	}
	Book(String title,String author,int price){
		this.title = title;						//////////this 는 자기 자신의 객체 멤버의 변수
		this.author = author;
		this.price = price;
	}
	String getBookInfo() {
//		String info = title+"  "+author+"  "+price;
//		return info;
		return "책이름 : "+title+" "+author+" "+price;
	}
	void BookTest(){
		System.out.println(getBookInfo());
	}
}
public class BookTest {

	public static void main(String[] args) {
		Book info1 = new Book();
		Book info2 = new Book("자바의 정식","남궁 식",40000);
		Book info3 = new Book("자바의 정성","남궁 정성",50000);
		Book info4 = new Book("자바의 정수","남궁 수",60000);
		Book info5 = new Book("자바의 정신","남궁 신",70000);
		info1.BookTest();
		info2.BookTest();
		info3.BookTest();
		info4.BookTest();
		info5.BookTest();

	}

}
