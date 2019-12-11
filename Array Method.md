#  Array Method

### Array Method

* 배열을 이용한 메서드를 생성하여 사용할 수 있다.

```java
package day7;

public class MethodTest10 {
public static void main(String[] args) {
	int p1[] = {20,10,14,30};			//int 배열 p1의 값 입력
	System.out.println("p1 변수의 값 : "+p1);//여기서 p1 은 참조값으로 출력
	printArray(p1);						//p1 원소를 넣어서 배열을 프린트
	int[] result = updateArray1(p1);	//updateArray1에 p1의 원소들이 들어가 
    									//새로운 result 배열 작성
	printArray(result);					//result의 참조값 (=위 p1의 참조값)
	printArray(p1);						//updateArray 이후 메서드 입력값 받아 2배 출력
	System.out.println("----------------");	//(구분선)
	updateArray1(p1);					//*중요! return 값 받지 않고 시작
    									//updateArray1 에 들어갔다 나와도 숫자는 
	printArray(p1);						//변하지 않는다
	System.out.println("----------------");
	updateArray2(p1);					//updateArray2 에 들어갔다 나오면 숫자가 
    									//변한다. **리턴값이 없기 때문
	printArray(p1);
}
static void printArray(int[]p2) {			//[배열을 프린트해주는 메서드]
	for(int data:p2)						//int 타입 data를 int[] p2 배열에 입력해
		System.out.printf("%d ",data);		//(%d=int 타입)data 값을 출력해준다 
	System.out.printf("\n");				//(\n = 띄어쓰기)
}
static int[] updateArray1(int [] p2) {		//[배열의 원소에 2씩 곱해주는 메서드]
	System.out.println("p2 변수의 값 : "+p2);
	int [] result = new int[p2.length];		//p2배열 길이만큼의 result 라는 배열 생성
	for(int i=0;i<p2.length;i++)			//p2 길이보다 작은만큼 반복시켜라 
		result[i] = p2[i]*2;				//뭐를? 배열에 2씩 곱한 값=result배열의 값
	return result;							//int[] 형식으로 return
}
static void updateArray2(int [] p3) {		//[위와 같지만 리턴시키지 않는 메서드]
	System.out.println("p3 변수의 값 : "+p3);
	for(int i=0;i<p3.length;i++)
		p3[i] = p3[i]*2;
}
}
```
###### *읽는 순서는 밑에 메서드 부터 !