# Hadoop 을 jar 로 

## 실행방법

- 원하는 패키지 우클릭 후 `export`
- `JAVA` - `JAR file` 설정 후 원하는 파일만 선택

- `C:\TEMP\fruitswc.jar` 위치 및 파일명 설정 = jar 파일 생성
- jar 파일 복사
- 리눅스로 가서 홈 디렉토리에 `mkdir mrapp` 디렉토리 생성
- `mrapp` 디렉토리 안에 저장하기
- 리눅스 FireFox 에서 `master:50070, master:8088` 확인
- `master:8088` 실행 시켜 놓고 터미널로 이동
- `yarn jar fruitswc.jar mrexam.WordCount` 수행
- FireFox 다시 가보면 제출하고 있는 것을 볼 수 있다
- 

