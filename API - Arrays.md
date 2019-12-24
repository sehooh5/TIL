### API - Arrays

- java.util.Arrays
- 배열과 관련된 유용한 메서드들이 있는 클래스
- Static 형 변수사용



##### [Method]

1. toString : 배열 출력

   ```java
   System.out.printf("ary 배열 원소들 : %s\n", Arrays.toString(ary));
   ```

2. sort : 퀵 정렬

   ```java
   Arrays.sort(ary);
   System.out.printf("소트 후 ary 배열 원소들 : %s\n", Arrays.toString(ary));
   ```

3. binarySearch : 매개변수가 존재하는지 여부와 몇번째 위치하는지 index 찾아줌

   ​						존재하지 않는다면 만약 존재할 때 어디에 위치할지 알려줌

   ```java
   int[] ary = { 2, 4, 3, 7, 21, 9, 98, 76, 74 };
   
   int idx = Arrays.binarySearch(ary, 21); //10 넣으면 -6
   System.out.printf("21 이라는 값이 있는 원소의 인덱스 : %d\n\n", idx);
   ```

   ![img](http://cfile240.uf.daum.net/image/2738874151F9B64208FE40)

4. copyOf (배열, 숫자) : 숫자 만큼 복사하여 배열을 늘려줌, 디폴트값이 자동으로 들어감

   ```java
   int[] copyOfArray = Arrays.copyOf(ary, 11); //복사
   System.out.printf("copyOfArray 배열 크기: %d\n", copyOfArray.length);
   System.out.printf("copyOfArray 배열 원소들 : %s\n\n", Arrays.toString(copyOfArray));
   ```

5. copyOfRange(배열, 숫자, 숫자) : 숫자~숫자 전까지 부분 복사

   ```java
   int[] copyOfRangeArray = Arrays.copyOfRange(ary, 5, 8); //부분복사
   ```

6. fill(배열, 숫자); : 배열을 숫자로 채우기

   ```java
   int[] fillArray = new int[5]; //배열 생성
   System.out.printf("fillArray (before): %s\n", Arrays.toString(fillArray));
   Arrays.fill(fillArray, 1);	//5개 원소 갖은 배열에 1들을 입력
   System.out.printf("fillArray (after): %s\n\n", Arrays.toString(fillArray)); 
   
   ```

7. asList(리스트); : 배열로 리스트 만들기

   ```java
   Integer[] objAry = new Integer[ary.length]; //objAry 배열 ary 길이로 정의
   		for (int i = 0; i < ary.length; i++)
   			objAry[i] = ary[i];//ary 값을 objAry 에 입력
   		List<Integer> integerList = Arrays.asList(objAry); 
   											//배열로 리스트 만들기
   		System.out.printf("리스트 크기 : %d\n", integerList.size());
   		System.out.printf("리스트의 원소들 : ");
   		for (Integer i : integerList) {
   			System.out.printf("%d ", i);
   		}
   ```

   

