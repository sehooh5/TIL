# Linux

- VI 에디터를 사용 할줄 알아야 한다
- `root` 계정은 슈퍼유저로 모든 기능을 사용할 수 있다(추가, 삭제 등등)
- `centOS` 계정은 일반유저로 공부하기에 적합하다
- 우리는 `VM - m1` 에서 `프로그램-즐겨찾기-터미널` 에서 명령어를 공부한다
- `Unix` 의 발전된 버전
- `포팅` : 이미 만들어진 소스를 가져와서 내 환경에 맞게 고쳐 사용하는 방법
  - ex) 안드로이드 10 버전을 가져와서 삼성, LG에서 사용하는 행태들
- `centOS` 는 레드햇에서 만든 무료 배포용 리눅스



### 환경 설치

#### 1.VMwear 시스템 설치

- `VMware-player-12.5.8-7098237.exe` 설치
  -  한달간 무료로 사용 가능
- `c:/VM` 폴더 만들기
- New VM Wizard
  - `intall late - linux - centOS64bit` 선택 후 `VM name` 주기
  - `size 40GB` 설정 , `single file` 설정해주기(not multiple)
  - 설정 완료
- 메모리 조절, 필요없는 것들 제외시켜주기
  - `Edit virtual mahchine settings`
  - `Memory` : 4GB 로 늘려주기
  - `USB controller, Sound Card, Printer` 삭제
- cmd 에서 네트워크 확인
  -  VMware Network Adapter VMnet8: IP 주소 확인 후 변경해줘야 함

- `vmnetcfg.exe` 파일을 VMware Player 파일에 붙여넣기
  - 후에 관리자 권한으로 실행
  - VMnet8 의 IP 주소 설정 : 192.168.111.1 
- VMware player 의 Settings 에서 CD/DVD 설정 (CD 넣어서 설치하는 느낌)
  - `CentOS-7.0-1406-x86_64-DVD.iso` 를 설정해주기
- Start VM 실행시키기
- 환경설정 
  - 한국어
  - 키보드 - 영어 추가
  - 영어를 위로 올려서 기본 키보드로 설정
- 소프트웨어 선택
  - 개발 및 창조를 위한 워크 스테이션 설정
- 네트워크 및 호스트 이름
  - 연결됨 끔 -> 켬
  - 호스트 이름 : master 로 변경
- 설치 대상
  - 수동 설정 후 완료
  - `표준 파티션` 설정
  - `+` 새마운트 지점 추가
    - 마운트 지점 : `swap` , `4G`
    - 마운트 지점 : `/`
- ROOT 암호 설정
  - root / password
- 사용자 생성
  - 모두 다 centos 로
- 설치 후 라이센스 동의 후 `설정 완료`
- kdump 활성화 해제 후 `앞으로`
- 로그인 화면 뜸
- 로그인 후 계속 진행

- 디스플레이 설정 
- 프로그램 - 시스템 도구 - 소프트웨어 - 최신패키지만 설정 해제, 소프트웨어 공급원 하지않기
- **로그아웃 꼭 해주고 끄기!**



