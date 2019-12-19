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
		super(x,y);						//x,y �� ���� ������ ���� ȣ���Ͽ� ����ϰ� z�� ������ ���� ���
		//this.x = x;
		//this.y = y;
		this.z = z;
	}
	String getLocation() {	// �������̵�		//�޼��� �̸��� �ƱԸ�Ʈ�� ������ ������  ���ϰ��� ���ƾ��Ѵ�.
		return super.getLocation() + ", z :" + z;
//		return getLocation() + ", z :" + z;		//super �� ������ ��� �ڱ��ڽ� ȣ���ؼ� ������ ����.
	}	
}













