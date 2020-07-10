

## [Linux]



editor -> VI 



-  VMware-player 설치
  - 빅데이터 플랫폼 폴더 -> VMware-player-12.5.8-7098237.exe  설치 (-> Custom Setup에서 체크박스 체크하면 키보드가 조금 업그레이드) -> User Experience Settings에 체크되어 있는거 다 해제 -> next -> install
  - c:/VM 폴더 생성 -> VMware Workstation 12 Player실행 -> email입력 (non commercial 버전으로 설치됨) 

- VM 생성
  - create new virtual machine ->  나중에 설치할게! 라는 의미의 3번째 radio 버튼 선택 -> next ->  Linux 선택 & Version은 CentOS 64-bit 선택 next->  Location : "C:\VM\m1"으로 두고, Virtual machine name : m1 으로 설정 -> Specify Disk Capacity 에서 Maximum disk size 40.0으로 설정 & Store virtual disk as a single file 선택 next -> Network Adapter  : NAT (master(host)의 램카드를 빌려서 사용하겠다는 의미)    ->finish
  - edit virtual machine settings -> memory 4GB로 변경 -> USB Controller, Sound Card, Printer remove  -> ok
-  network 환경 설정하기
  - vmnetcfg.exe파일을 복사해서 C:\Program Files (x86)\VMware\VMware Player에 붙여넣기 -> 우측 버튼 관리자 권한으로 실행-> VMnet8으르 선택하고 Subnet IP를 192.168.111.0으로 변경
  - cmd 창에서 ipconfig 로 확인 가능



- virtual machine setting 변경
  - CD/DVD(IDE) -> Use ISO image file -> Browse에서 빅데이터 플랫폼 폴더 -> CentOS-7.0-1406-x86_64-DVD.iso 선택 
  - <mark> VMware Workstation에서 마우스 없어지면 Ctrl + Alt 누르면 생김 </mark>



- Centos 설치 및 환경설정은 책 3장 따라하면 됨!
  - 네트워크 및 호스트 이름 설정 시 호스트 이름  master로 변경



<hr>

- Linux는 다중 사용자 운영 체제 -> window랑 다름

- 로그인의 과정 필요, 시스템을 사용할 수 있는 권한 -> id를 부여받아야 함
- root라는 계정 = 시스템 super 유저 = system 관리자 계정
- root 계정의 password는 까먹으면 안됨! -> 다른 유저는 잊어버리더라도 root계정에서 변경 요청 가능
- 프로그램 -> 즐겨찾기 -> 터미널 -> 명령을 가지고 작업하는 것이 일반적
- 처음부터 끝까지 개발하는 것이 아닌 내가 원하는 환경에 맞게 있던 소스를 바꿔서 완성하는 과정 -> 포팅(porting)



- ``` 
  [centos@master ~]$ 
  마스터에서 centos로 
  ~ : Home directory - 로그인을 성공적으로 하면 자동적으로 가게되는 directory
  ```

- ``` 
  pwd : present working directory - 현재 디렉토리 보여줘
  ls : list - 현재 디렉토리의 리스트 출력. 파일 명만 보고 싶을 때
  ls -l : ls 라는 명령을 수행하는데, l(long)에 맞춰서 함 == ll 파일 명 + 상세 
  
  [centos@master ~]$ ls -l
  합계 0
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 공개
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 다운로드
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 문서
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 바탕화면
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 비디오
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 사진
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 서식
  drwxr-xr-x. 2 centos centos 6  4월 27 09:32 음악
  사용자 허가모드. 
  
  ls -a : ls all // 명칭이 .으로 시작하면 hidden 파일
  [centos@master ~]$ ls -a
  .              .bash_profile  .esd_auth  다운로드  사진
  ..             .bashrc        .local     문서      서식
  .ICEauthority  .cache         .mozilla   바탕화면  음악
  .bash_logout   .config        공개       비디오
  
  ls -al : all 과 long 모두를 적용하여 ls를 수행
  [centos@master ~]$ ls -al
  합계 32
  drwx------. 14 centos centos 4096  4월 27 09:32 .
  drwxr-xr-x.  3 root   root     19  4월 24 20:55 ..
  -rw-------.  1 centos centos  310  4월 27 09:32 .ICEauthority
  -rw-r--r--.  1 centos centos   18  6월 10  2014 .bash_logout
  -rw-r--r--.  1 centos centos  193  6월 10  2014 .bash_profile
  -rw-r--r--.  1 centos centos  231  6월 10  2014 .bashrc
  drwx------.  9 centos centos 4096  4월 27 09:35 .cache
  drwxr-xr-x. 15 centos centos 4096  4월 27 09:34 .config
  -rw-------.  1 centos centos   16  4월 27 09:32 .esd_auth
  drwx------.  3 centos centos   18  4월 27 09:32 .local
  drwxr-xr-x.  5 centos centos   51  4월 27 09:35 .mozilla
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 공개
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 다운로드
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 문서
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 바탕화면
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 비디오
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 사진
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 서식
  drwxr-xr-x.  2 centos centos    6  4월 27 09:32 음악
  
  cd : 어떤 디렉토리에 있던 home으로 갈거다!
  
  ```

- 사용자 허가 모드 

  - 소유자에 대한 허가
  - 파일의 그룹에 대한 사용자 허가
  -  그 외의 모든 사용자

- .. : 부모 directory (현재에서 상위 디렉토리)
- . : 현재 directory

- 권한

  파일 타입을 제외한 9개의 rwx에 대해 3개씩 끊으면 순서대로 소유자, 소유 그룹, 제 3자(기타)에 대한 권한을 나타냅니다.

  rwx의 의미는 다음과 같습니다.

  r    : 읽기 권한

  w   : 쓰기 권한

  x    : 접근 권한

  rwx 간에도 규칙이 있습니다.

  접근을 할 수 있어야 파일 및 디렉터리를 읽을 수 있고, 읽을 수 있어야 쓰기가 가능하다는 것입니다.

  x -> r -> w

  이제 권한을 읽는 방법에 대해 알아보겠습니다.

  

  예를들어 파일의 권한이 r w - r - - r - - 일 경우, 파일의 소유자는 읽고, 쓸 수 있는 권한이 있고,

  파일의 소유 그룹에 해당하는 유저들은 읽기만 가능하며, 그 외 제3자도 읽기만 가능합니다.

  

  rw-r--r--은 간단히 644와 같이 Numeric으로 표현합니다.

  r w -   : 6 ( = 4 + 2 + 0 )
  r - -    : 4 ( = 4 + 0 + 0 )
  r - -    : 4 ( = 4 + 0 + 0 )

  한 주체에 대해 r w x의 각 자리는 2의 거듭제곱으로 표현합니다.

- ``` 
  vi example1.txt
  
  i(or a) + enter : 입력/추가 모드 변경 후 입력가능
  esc로 명령모드 전환 후
  x : 한 글자 삭제
  dd : 한 행 삭제
  yy : 한 행 복사 / 3yy : 3행 복사 (원하는 행의 수만큼 복사 가능)
  p : 붙여넣기
  :1 : 1행으로 이동
  :10 : 10행으로 이동
  shift + g : 무조건 마지막 행으로 감
  
  터미널 명령어
  more, head, tail : 내용 보여줌
```
  
- ```java
  vi Example.java
      
  import java.util.Date;
  public class Example{
      public static void main(String args[]){
          System.out.println("현재 시간 : "+ new Date());
      }
  }
  ```

- ```
  [centos@master imsi]$ java -version
  java version "1.7.0_51"
  OpenJDK Runtime Environment (rhel-2.4.5.5.el7-x86_64 u51-b31)
  OpenJDK 64-Bit Server VM (build 24.51-b03, mixed mode)
  
  [centos@master imsi]$ javac Example.java 
  bash: javac: 명령을 찾을 수 없습니다...
  유사한 명령: 'java'
  // 자바 실행환경은 있지만, 개발환경은 구축되어 있지 않음 -> jdk부터 다시 깔아야 함
  ```

- 단축키 정리

  | 키            | 설명                                   | 키      | 설명                                  |
  | ------------- | -------------------------------------- | ------- | ------------------------------------- |
  | i             | 현재 커서의 위치부터 입력              | I       | 현재 커서 줄의 맨 앞에서 부터 입력    |
  | a             | 현재 커서의 위치 다음 칸부터 입력      | A       | 현재 커서 줄의 맨 마지막부터 입력     |
  | o             | 현재 커서의 다음 줄에 입력             | O       | 현재 커서의 이전 줄에 입력            |
  | s             | 현재 커서 위치의 한 글자를 지우고 입력 | S       | 현재 커서의 한 줄을 지우고 입력       |
  | h             | 커서를 왼쪽으로 한 칸 이동             | j       | 커서를 아래로 한 칸 이동              |
  | k             | 커서를 위로 한 칸 이동                 | l       | 커서를 오른쪽으로 한 칸 이동          |
  | Ctrl+F        | 다음 화면으로 이동                     | Ctrl+B  | 이전 화면으로 이동                    |
  | ^             | 현재 행의 처음으로 이동                | $       | 현재 행의 마지막으로 이동             |
  | gg            | 제일 첫 행으로 이동                    | G       | 제일 끝 행으로 이동                   |
  | 숫자 G        | 해당 숫자의 행으로 이동                | :숫자   | 해당 숫자의 행으로 이동               |
| x             | 현재 커서가 위치한 글자 삭제           | X       | 현재 커서가 위치한 앞 글자 삭제       |
  | dd            | 현재 커서의 행 삭제                    | 숫자 dd | 현재 커서부터 숫자만큼의 행 삭제      |
  | yy            | 현재 커서가 있는 행을 복사             | 숫자 yy | 현재 커서부터 숫자만큼의 행을 복사    |
  | p             | 복사한 내용을 현재 행 이후에 붙여 넣기 | P       | 복사한 내용을 현재 행 이전에 붙여넣기 |
  | /문자열 Enter | 해당 문자열을 찾음(현재 커서 이후로)   | n       | 찾은 문자 중에서 다음 문자로 이동     |
  
  

- 명령어 정리

  | 명령어                         | 설명                                                         |
  | ------------------------------ | ------------------------------------------------------------ |
  | 명령>파일명                    | 해당 명령의 결과를 파일에 저장 (덮어쓰기)                    |
  | 명령 >>파일명                  | 덧 붙이기(append)                                            |
  | 명령1\|명령2                   | 명령 1을 가지고 명령 2를 보여줌                              |
  | Ctrl+c                         | 중단                                                         |
  | clear                          | 현재 사용 중인 터미널 화면 깨끗하게 지워줌                   |
  | file                           | 해당 파일이 어떤 종류의 파일인지 표시                        |
  | more                           | 텍스트 형식으로 작성된 파일을 페이지 단위로 화면에 출력<br />ex)  more +100 ananconda-ks.cfg  :  100행부터 출력 |
  | less                           | 'more' 명령어와 용도 비슷                                    |
  | q                              | quit라는 의미로 나가기                                       |
  | man 명령어                     | manual로 명령어의 document가 나옴                            |
  | ps                             | process status의 약어. <br />process : 현재 수행중인 프로그램을 보여줌 - 해당 터미널에서만 수행중인 것만 보여줌<br />ps -e : 모든 터미널에서 수행중인 process 보여줌<br /> PID      TTY          TIME    CMD<br/> 56506 pts/0    00:00:00 bash<br/> 58356 pts/0    00:00:00 ps<br />의 형태로 나옴 |
  | sleep 초                       | 초 만큼 멈춤 -> java의 sleep 메소드와 비슷<br />이대로 수행하면 해당 명령어가 다 수행될 때 까지 다른 명령어를 수행하지 못함 |
  | sleep 초&                      | &의 의미는 해당 명령어를 background로 실행하겠다는 의미<br />즉, 터미널이 한 개 일 때 사용하기 유용하며, 다른 명령어 수행 가능 |
  | wc -l                          | word count - 행단위<br />행단위로 명령들의 갯수를 나타내줌 (line count) |
  | wc                             | [centos@master imsi]$ wc Example.java <br/>  6  18 145 Example.java<br />행 단어 문자수 |
  | ls                             | list 의 약자. windows의 'dir'역할                            |
  | cd                             | change directory<br />ex) <br />cd ~centos : ~해당 user의 홈 디렉토리 이동<br />cd /home/centos/my : 절대 경로<br />cd ../my : 상대 경로 |
  | rm                             | remove의 약자, root사용자는 모든 권한이 있음<br />ex)<br />rm -f a.txt : 삭제시 확인하지 않고 바로 삭제<br />rm -r mt : 해당 directory 삭제<br />rm -rf abc : 해당 directory를 확인하지 않고 바로 삭제. <mark> 주의</mark> |
  | rmdir                          | remove directory <mark>해당 디렉토리는 비어있어야 함</mark><br />파일이 들어있는 디렉토리 삭제시 rm -r 실행 |
  | cp                             | Copy의 약자<br />ex)<br />cp -r sample.txt edit.txt :sample.txt를 edit.txt로 복사 |
  | touch                          | 크기가 0인 새 파일 생성 혹은, 최종 수정 시간 변경            |
  | cat                            | conCATenate의 약자. 파일의 내용 보여줌.<br />여러개의 파일 나열 시 파일 연결해서 보여줌 |
  | cat 파일명1 파일명2 > 파일명 3 | 파일1과 파일2를 합해서 파일3을 새로 만듬                     |