### API-StringBuffer

```java
package day12;

public class StringBufferTest {

	public static void main(String[] args) {		
		StringBuffer buffer = new StringBuffer();	
		String str = "자바프로그래밍";					
     	buffer.append(str);

        System.out.printf("%s\n", buffer);
        buffer.reverse();			//반전
        System.out.printf("%s\n", buffer);
        System.out.printf("길이 : %d\n", buffer.length());    
        
        StringBuffer buffer2 = new StringBuffer();
        buffer2.append('자');
        buffer2.append('바');
        buffer2.append('프');
        buffer2.append('로');
        buffer2.append('그');
        buffer2.append('래');
        buffer2.append('밍');
        
        System.out.println(buffer == buffer2);
        System.out.println(buffer.equals(buffer2));
        System.out.println(buffer.toString().equals(buffer2.toString()));
	}			//StringBuffer 비교하려면 둘다 String 형으로 변환하여 비교해야한다
}
```

- StringBuffer : 초기 문자열이 없고 16개의 문자를 저장할 수 있는 버퍼를 가진 객체를 생성

- StringBuffer(int length) : 초기 문자열이 없고 length개의 문자를 저장할 수 있는 버퍼객체

- StringBuffer(boolean, char, double, float, String....)

    현재 문자열 끝에 각 데이터를 추가한다.