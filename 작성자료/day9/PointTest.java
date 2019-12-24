package day9;
public class PointTest {
	public static void main(String args[]) {
		Point3D p3 = new Point3D(1,2,3);	
		System.out.println(p3.getLocation());
	}
}
class Point {
	int x;	
	int y;
	Point(int x, int y) {		
		this.x = x;
		this.y = y;
	}
	String getLocation() {
		return "x :" + x + ", y :"+ y;
	}
}
class Point3D extends Point {
	int z;
	Point3D(int x, int y, int z) {	
		super(x,y);						//x,y 의 값은 조상의 것을 호출하여 사용하고 z는 본인의 것을 사용
		//this.x = x;
		//this.y = y;
		this.z = z;
	}
	String getLocation() {	// 오버라이딩		//메서드 이름과 아규먼트값 유형이 같으면  리턴값도 같아야한다.
		return super.getLocation() + ", z :" + z;
//		return getLocation() + ", z :" + z;		//super 가 없으면 계속 자기자신 호출해서 에러가 난다.
	}	
}













