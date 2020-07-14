# [i.hub] Project

---



## 0. 주제  

공공 데이터 포털에서 사용되는 OPEN API 의 사용 가능 상태를 체크할 수 있고 해당 API의 사용 불가한 상황을 고려하여 자료를 json 파일로 백업하여 제공한다



## 1. 주제 선정 이유

공공 데이터를 사용하는 유저들의 공통된 불편한 점인 API  작동이 멈출 시 일어나는 문제점을 해소시켜주고 원하는 자료를 제공하기 위하여 주제를 선정하였다.



## 2. 사용 기술 

`



## 3. 업무 분담 

### 오세호

- DB 설계, 장고 로직 구현, 포털 사이트 크롤링, json 파일 변환 및 백업

### 김수이

- DB 설계 및 수정, 장고 로직 구현, API 작동 상태 구현

### 정해림

- 프론트 앤드 담당, BootStrap 활용 커스터마이징, 로고작업



## 4. 담당 상세 기능

- 데이터 포털 사이트에서 제공하는 조회수, 다운로드수 별 API의 랭킹을 크롤링하여 실시간으로 본 사이트에서 제공하도록 한다.
- 해당 사이트에 필요한 Data Base 의 구조를 Aquery Tool 을 사용하여 정의한다.
- XML, json형식으로 제공되는 API들의 반환값들을 json 형태로 변환 혹은 저장하여 Django의 Media 파일에 저장하여 사용자들이 접근 가능하도록 한다.



## 5. 사용 기술 및 개발계획

### 1) 사용기술

Python Django 프레임워크를 사용하고 JavaScript, BootStrap 을 활용해 개발

#### Backend

- Python : 3.7.6
- Django : 2.7.6

#### Frontend

- HTML
- JS, AJAX
- BootStrap



### 2) 개발 계획

- 진행 기간 : 2020.06.23 - 2020.07.07
- 목표 : 장고를 활용하여 OPEN API 상태를 확인시킬 수 있고 백업파일을 저장하여 제공하는 플랫폼을 빠르게 구현하자



## 6. DB 모델링

![image](https://user-images.githubusercontent.com/58541635/87240121-bb2e7300-c451-11ea-8e35-22bdfc5eed51.png)



## 7. 핵심 기능

### 1) OPEN API 사용 가능 상태 표시 및 검색

- 서울시 열린데이터 광장에서 제공하는 OPEN API 약 3600개에 대한 사용 가능 컨디션을 제공하는 보드를 제공
- 원하는 데이터를 검색할 수 있는 검색기능
- 세부사항에 실시간 컨디션 데이터를 받아와 언제부터 사용이 불가했는지 그래프로 표시

### 2) 조회수, 다운로드수 별 API 활용 랭킹 제공

- 현재 가장 활용도가 높은 API 랭킹을 순위별로 제공

### 3) 원하는 API의 백업파일 제공(json 형식)

- API 의 최근 데이터를 json 형식의 백업파일로 다운로드 가능하게함
- 다운로드하면 마이페이지에서 다운로드 한 자료 확인 가능
- API 별로 백업파일 다운로드 횟수 확인 가능



## 8. 시연 화면

---

### 1) 메인 화면

1. 상단 API 현황 및 중간 API 상세 작동 여부 리스트(검색 가능)

![image](https://user-images.githubusercontent.com/58541635/87283625-72012080-c530-11ea-9c6f-cec58578ade5.png)

2. 하단 조회수, 다운로드수 별 랭킹 시스템

![image](https://user-images.githubusercontent.com/58541635/87283840-b1c80800-c530-11ea-83da-f81b7441bcdc.png)



### 2) API 상세화면

![image](https://user-images.githubusercontent.com/58541635/87284022-e20fa680-c530-11ea-8f09-1f01786d7cd8.png)

- 모달창으로 구현
- API 이름, 수정날짜, 저작권자, 다운로드수 등 기본 정보 제공
- 15분 단위로 API 의 작동 여부를 체크한 그래프 제공
- json 백업 파일 다운로드 기능

### 3) 