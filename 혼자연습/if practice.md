# if practice

```java
package SEHO;

public class Practice2 {

	public static void main(String[] args) {
		int a=1;
		
		int b=2;
		
		if (a>b) {
			System.out.println(">");
		}else if(a<b){
			System.out.println("<");
		}else if(a==b) {
			System.out.println("==");
		}
	}

}
```



```java
package SEHO;

public class Practice3 {

	public static void main(String[] args) {
		int year = 2000;
		
		if(year%4==0&&year%100!=0||year%400==0) {
			System.out.println("1");
		}else
			System.out.println("0");
		
	}

}
```



```java
package SEHO;

public class Practice4 {

	public static void main(String[] args) {
		int h = (int)(Math.random()*23);
		int m = (int)(Math.random()*59);
		System.out.printf("%s %d%s %d%s\n","지금은 몇시 몇분?",h,"시",m,"분");
		if (m<45) {
			System.out.printf((h-1)+" "+(60+(m-45)));
		}else
			System.out.println(h+" "+(m-45));
		
		
		
	}

}
```



```java
package SEHO;

public class Practice5 {

	public static void main(String[] args) {
		int score = (int)(Math.random()*100);
		System.out.println("점수 : "+score);
		System.out.print("등급 : ");
		if (score>=90&&score<=100) {
			System.out.println("A");
		}else if(score>=80&&score<90) {
			System.out.println("B");
		}else if(score>=70&&score<80) {
			System.out.println("C");
		}else if(score>=60&&score<70) {
			System.out.println("D");
		}else
			System.out.println("F");
		
	}

}
```



```java
package SEHO;

public class Practice6 {

	public static void main(String[] args) {
		
		int a = (int)((Math.random()*100)+1);
		int b = (int)((Math.random()*100)+1);
		int c = (int)((Math.random()*100)+1);
		System.out.printf("%s%d %s%d %s%d\n","숫자 A : ",a,"숫자 B : ",b,"숫자 C : ",c);
		System.out.print("두번째로 큰 수는? : ");
		if((a>b&&b>c)||(c>b&&b>a)) {
			System.out.println("B");
		}else if((b>c&&c>a)||(a>c&&c>b)) {
			System.out.println("C");
		}else if((c>a&&a>b)||(b>a&&a>c)) {
			System.out.println("A");
		}
		
	}

}
```

