# java.lang

**java.lang** - Object, Math, Integer, String, StringBuffer, Character...

- **String** : 문자열 처리와 관련된 메서드들을 가지고 있다.

  ​			String 객체가 생성된 이후 초기화 된 문자열 내용은 변경 불가하다.

  ​			읽기 용도로만 사용 가능하다.				

  ​			ex) "abc" + "가나다" = "abc가나다"  ----> 세 객체 모두 새로운 객체, 변할 수 없다.

  ​					"a" + "b" + "c" = "abc"		----->		문자열 리터럴은 모두 스트링 객체

  ​					"ab" "abc" "abcd" 총 세개의 객체를 더 만든다..나머지는 버림

- **StringBuffer** : 문자열 편집하는 용도

  ```java
  StringBuffer sb = new StringBuffer();
  sb.append("a");
  sb.append("b");
  sb.append("c");
  sb.append("d");
  ```

- **equals()** : Object 클래스에 있는 equals는 등가연산자 `==` 과 동일하다

  ```
  Book b1 = new Book("짱구","울라숑",10000);
  Book b1 = new Book("짱국","울라쇼",10000);
  Book b3 = b2;
  b1 == b2 ? false
  b3 == b2 ? true
  b1.equals(b2) ? false
  b3.equals(b2) ?	true
  ```

  

- **API** : Application Programming Interface

  ​		자주 사용되는 기능을 미리 만들어 놓은 프로그램

  ​		자바 : 클래스, abstract 클래스, 인터페이스,,

  ​		C : 함수

