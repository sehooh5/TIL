# Collection-HashSet



```java
package day14.copy;
public class HashCodeTest {
	public static void main(String[] args) {
		String s1 = new String("듀크");
		String s2 = new String("듀크");
		String s3 = new String("듀크");
		String s4 = new String("듀크");
		String s5 = "듀크";
		System.out.println(s1.hashCode());
    //동일한 객체들은 똑같은 주소값을 갖는다
		System.out.println(s2.hashCode());
		System.out.println(s3.hashCode());
		System.out.println(s4.hashCode());
		System.out.println(s5.hashCode());//전부 같은 결과
		String s6 = new String("고길동");
		String s7 = new String("듀크1");
		System.out.println(s6.hashCode());
    //다른 객체들은 다른 주소값을 갖는다
		System.out.println(s7.hashCode());
	}
}
//***해쉬코드라는 메서드를 오버라이딩 하지 않은 클래스는 같은 참조값을 갖기 때문에 키가될 수 없다..
```

