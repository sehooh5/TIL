package day7;
class Book{
	String title;
	String author;
	int price;
	Book(){
//		title = "�ڹ��� ����";
//		author = "���� ��";
//		price = 30000;
		this("�ڹ��� ����","���� ��",30000);
	}
	Book(String title,String author,int price){
		this.title = title;						//////////this �� �ڱ� �ڽ��� ��ü ����� ����
		this.author = author;
		this.price = price;
	}
	String getBookInfo() {
//		String info = title+"  "+author+"  "+price;
//		return info;
		return "å�̸� : "+title+" "+author+" "+price;
	}
	void BookTest(){
		System.out.println(getBookInfo());
	}
}
public class BookTest {

	public static void main(String[] args) {
		Book info1 = new Book();
		Book info2 = new Book("�ڹ��� ����","���� ��",40000);
		Book info3 = new Book("�ڹ��� ����","���� ����",50000);
		Book info4 = new Book("�ڹ��� ����","���� ��",60000);
		Book info5 = new Book("�ڹ��� ����","���� ��",70000);
		info1.BookTest();
		info2.BookTest();
		info3.BookTest();
		info4.BookTest();
		info5.BookTest();

	}

}
