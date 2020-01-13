# Storage API

- storageexam/stmemo/index.html 이 스토리지 API 활용한 기능

- 웹브라우저에 자료를 저장하기 위한 기능으로 로컬스토리지와 세션스토리지로 나뉜다.
- 쿠키와 비슷하지만 차이가 있다.
- 데이터 종류는 제한이 없고 문자열로 저장된다..또한 유일한 이름(키)을 같이 저장한다.



1. **로컬스토리지** : 영구보관
2. **세션스토리지** : 브라우저가 종료될 때까지 보관



#### window.localStorage 와 window.sessionStorage 의 주요 멤버

- length : 스토리지에 저장된 key/value 쌍의 개수를 추출하는 속성이다.

- key(index) : 숫자형 인덱스에 해당하는 key를 리턴한다.

- getItem(key) : 스토리지로 부터 key 에 해당하는 value 를 추출한다.

- setItem(key, value) : 스토리지에 key 에 해당하는 value 를 저장한다.

- removeItem(string key) : 스토리지에 key 에 해당하는 value 를 제거한다.

- clear() : 현재 스토리지의 모든 데이터를 제거한다.

- onstorage : 로컬 스토리지의 내용이 변경될 때릴다 발생되는 이벤트로 로컬 스토리지의 변경 사항을 모니터링 하는 것이 가능하다. StorageEvent 객체가 생성된다.
  [ StorageEvent 객체의 주요 속성 ]
  –key : 추가, 삭제, 변경된 키 이름
  –oldValue : 업데이트되기 전의 값으로 새로 추가된 값이면 null
  –newValue : 새로 업데이트된 값으로 기존 값을 삭제한 경우에는 null

  –url : 변경사항이 발생된 페이지의 URL



- 로컬 스토리지의 데이터 관리
  - 저장
    localStorage.mykey = "myvalue";
    localStorage["mykey"] = "myvalue";
    localStorage.setItem("mykey", "myvalue");
  - 읽기
    var mydata = localStorage.mykey;
    var mydata = localStorage["mykey"];
    var mydata = localStorage.getItem("mykey”);
  - 삭제
    delete localStorage.mykey;
    delete localStorage["mykey"];
    localStorage.removeItem("mykey");
- 세션 스토리지의 데이터 관리
  - 저장
    sessionStorage.mykey = "myvalue";
    sessionStorage["mykey"] = "myvalue";
    sessionStorage.setItem("mykey", "myvalue");
  - 인기
    var mydata = sessionStorage.mykey;
    var mydata = sessionStorage["mykey"];
    var mydata = sessionStorage.getItem("mykey”);
  - 삭제
    delete sessionStorage.mykey;
    delete sessionStorage["mykey"];
    sessionStorage.removeItem("mykey");