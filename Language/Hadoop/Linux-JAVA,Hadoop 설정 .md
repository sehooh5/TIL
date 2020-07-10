# Linux (4/28)

1. IP 주소를 고정해준다
2. JDK 1.8 을 설치한다
3. Eclipse 설치한다
4. Tomcat 설치한다



### 1. IP 주소 고정

1. 위치를 네트워크 설정으로 이동시킨다

   ```
   cd /etc/sysconfig/network-scripts/
   ```

2. `ifcfg-eno*****` 폴더를 vi 로 실행시켜준다

   ```
   vi ifcfg-eno16777728
   
   수정 : BOOTPROTO=none
   추가 : IPADDR=192.168.111.120
   추가 : NETMASK=255.255.255.0
   추가 : GATEWAY=192.168.111.2
   추가 : DNS1=192.168.111.2
   ```

3. 네트워크 재시작

   ```
   systemctl restart network (재시작)
   ifconfig (확인)
   ```



### 2. JDK 1.8 설치

1. `빅데이터플랫폼` 폴더에 `jdk-8u131-linux-x64.tar.gz` 을 리눅스로 이동

2. 파일을 복사해서 이동하고 싶은 폴더에 가서 붙여넣기

   `jdk, tomcat, eclipse, hadoop` 네개 전부 복사 붙여넣기

3. JDK 를 설치한다

   ```
   #cd
   #mkdir tools
   #cd tools
   #tar xvfz jdk-8u131-linux-x64.tar.gz
   #mv jdk1.8.0_131 /usr/local
   #cd /usr/local
   #ln -s jdk1.8._131 java (java-> jdk*** 참조시켜주는 기능)
   #ls -l
   #vi /etc/profile
   	행 추가
   	export JAVA_HOME=/usr/local/java
   	export PATH=$JAVA_HOME/bin:$PATH
   #source /etc/profile (적용)
   #java -version
   
   하면 jdk1.8버전 떠야한다
   ```



### 3. eclipse 설치

1. eclipse 설치

   ```
   #tar xvfz eclipse~~~~~~
   #cd eclipse
   #eclipse (실행 안됨)
   #./eclipse 이렇게 실행시켜줘야함
   ```

2. 다른작업 병행하고 싶으면 터미널 새로 띄워서 하면됨



### 4.tomcat 설치

1. 설치 명령어 실행

   ```
   # tar xvfz apache-tomcat-9.0.22.tar.gz 
   ```

2. 배포할때는 톰캣 폴더 안에 `webapps`폴더를 사용해주면 된다

3. eclipse 에 New-Others서버 찾기에서 Tomcat 9.0 설정해준다

4. `root - tools - apache~~~` 로 설정해준다

5. 포트번호 설정 방법(이미 등록 한 후에 하는 방법)

   - `Servers-Tomcat~~-server.xml` 에서 63행에 `port="8000"`으로 변경

6. `Dynamic Web Project`  생성..이름=`linuxedu`

   - `Target runtime = Apache Tomcat v9.0` 설정
   - `Generate web.xml deployment descriptor` 체크 후 `Finish`

7. 웹서비스 문서 작성 후 실행

8. 윈도우 크롬에선 페이지 실행이 안됨..리눅스가 외부에서 포트로 접근을 차단

   - 방화벽 종료 및 중단하고 실행해주면 사용 가능

     ```
     systemctl stop firewalld
     systemctl disable firewalld
     ```



## Hadoop 작업환경

### 5.Hadoop 설치

1. `tools` 폴더에서 다운로드 실행

   ```
   #wget https://archive.apache.org/dist/hadoop/common/hadoop-2.7.7/hadoop-2.7.7.tar.gz	다운로드
   #tar xvfz hadoop-2.7.7.tar.gz
   ```

2. `hadoop` 을 `root`로 옴겨줌

   ```
   #mv hadoop-2.7.7 .. (바로 위 디렉토리 즉 root)
   ```

3. 홈 디렉토리에 `.bashrc` 파일 마지막에 PATH 내용 추가 및 설정

   ```
   export HADOOP_HOME=/root/hadoop-2.7.7
   export PATH=$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH
   ```

   추가한 후에 로그인을 다시 하거나 **실행**을 해주어야 내용이 반영된다!

   ```
   #source .bashrc (실행)
   
   #echo $HADOOP_HOME (경로 잘 설정 되었나 확인)
   ```

4. 하둡 설정 변경을 위해 Hadoop 의 설정파일 디렉토리로 옮겨간다

   ```
   # cd $HADOOP_HOME/etc/hadoop
   ```

5. 설정 내용들

   ```
   7. hadoop-env.sh 파일 끝에 다음 내용을 추가한다.
   export JAVA_HOME=/usr/local/java
   export HADOOP_HOME=/root/hadoop-2.7.7
   export HADOOP_HEAPSIZE=500
   
   8. mapred-env.sh 파일 끝에 다음 내용을 추가한다.
   export JAVA_HOME=/usr/local/java
   export HADOOP_JOB_HISTORYSERVER_HEAPSIZE=500
   export HADOOP_HOME=/root/hadoop-2.7.7
   
   9. yarn-env.sh 파일 끝에 다음 내용을 추가한다.
   export JAVA_HOME=/usr/local/java
   export HADOOP_HOME=/root/hadoop-2.7.7
   export YARN_HEAPSIZE=500
   
   10. core-site.xml 에 다음 내용을 편집한다.
   <configuration>
      <property>
         <name>fs.defaultFS</name>
         <value>hdfs://master:9000/</value>
      </property>   
   </configuration>
   
   11. hdfs-site.xml 에 다음 내용을 편집한다.
   <configuration>
      <property>
         <name>dfs.replication</name>
         <value>3</value>
      </property>
      <property>
         <name>dfs.name.dir</name>
         <value>/root/hadoop-2.7.7/hdfs/name</value>
      </property>
      <property>
         <name>dfs.data.dir</name>
         <value>/root/hadoop-2.7.7/hdfs/data</value>
      </property>
      <property>
         <name>dfs.support.append</name>
         <value>true</value>
      </property>
      <property>
         <name>dfs.namenode.secondary.http-address</name>
         <value>slave1:50090</value>
      </property>
      <property>
         <name>dfs.namenode.secondary.https-address</name>
         <value>slave1:50091</value>
      </property> 
   </configuration>
   
   12. mapred-site.xml 에 다음 내용을 편집한다.
   <configuration>
      <property>
         <name>mapreduce.framework.name</name>
         <value>yarn</value>
      </property>
      <property>
         <name>yarn.resourcemanager.hostname</name>
         <value>master</value>
      </property>
   </configuration>
   
   13. yarn-site.xml 에 다음 내용을 편집한다. 
   <configuration>
   <!-- Site specific YARN configuration properties -->
      <property>
         <name>yarn.nodemanager.aux-services</name>
         <value>mapreduce_shuffle</value>
      </property>
   </configuration>
   ```

6. `cd $HADOOP_HOME/etc/hadoop` 로 들어가서 `slaves, masters`(생성,수정)

   - `vi slaves` : 

     ```
     slave1
     slave2
     slave3
     ```

   - `vi masters` :

     ```
     slave1 (SecondaryNameNode 설정할 컴퓨터)
     ```

     

7. 위 과정 후 로그아웃하고 VM 전부 종료시켜주기

8. 다시 VM 기동시키고 설치 CD 빼주기

   `m1` - `CD` - `Use phyical drive 체크`

9. `c:/VM/` 밑에 `m1, m2, m3, m4` 폴더 복사해주기

10. `m1.vmx` 파일을 메모장으로 열어서 `displayName` 을 각각 이름맞춰 지정(참고자료 14번 꼭해줘야함)

11. VM 에서 `Player-file-open` 해서 각각 인식시켜줌

12. 각 컴퓨터의 네트워크를 각각 변경해줘야한다

13. `Edit virtual machine settings - Network~ - Advanced - MAC Address(Generate)` 후 해당 주소 복사해놓기

    - m1 : 00:50:56:30:B5:B8
    - m2 : 00:50:56:28:F7:F3
    - m3 : 00:50:56:33:C4:11
    - m4 : 00:50:56:2A:00:77

14. 리눅스를 각각 기동시킨다

    - `I moved it` 선택해야지 `copied` 하면 `MAC address` 바뀜

15. 각각 이름 변경

    `master, slave1, slave2, slave3`

16. 각 머신의 고정 IP를 설정한다

    `cd  /etc/sysconfig/network-scripts/` - `vi ifcfg-eno~~~~`에서 수정

    1. HWADDR 변경
    2. IPDDR 아래로 변경

    - slave1 : 192.168.111.130
    - slave2 : 192.168.111.140
    - slave3 : 192.168.111.150

    3. 변경 후 네트워크 재시작 : `systemctl restart network` & `ifconfig`

17. 네트워크 잘 연결됐는지 확인 : `ping 아이피 주소`

    종료 : `Ctrl + c`  (interrupt)



### Hadoop 완전 분산모드 기동

1. 사용자 이름으로 찾아가게끔 설정해주는 방법

   - `vi /etc/hosts`
   - `192.168.111.120 master` 이런식으로 지정해주기

2. 다른 컴퓨터로 접속하기 (나가기 : `Ctrl+d`)

   - `ssh root@slave1` 실행 : 임시 키 발행하여 `slave1`에 보안 접속

     - `password`입력이 필요하다..하지만 우리는 `password`를 몰라도 접속이 가능하게 만들어줘야한다
     - **즉, 연결할 컴퓨터들 만의 `password`를 지정해줘야한다**

   - `ssh-keygen -t rsa` : ssh 키를 생성하여 다른 시스템과 공유

     ```
     <전부 그냥 엔터 눌러주면 비밀번호 없이 사용 가능>
     Enter file in which to save the key (/root/.ssh/id_rsa): 
     Enter passphrase (empty for no passphrase): 
     Enter same passphrase again:
     ```

   - `ssh-copy-id -i /root/.ssh/id_rsa.pub root@slave1` : 

     공개 키를 나머지 리눅스 시스템과 공유(패스워드 1회 입력해야함)

     우리는 `slave1 ,slave2, slave3, master` 모두 해주어야한다

   - 잘됐는데 확인하려면 `ssh root@slave1` 다시 실행

3. 이 과정을 통해 4개의 컴퓨터는 한 개의 **클러스터**로 묶였다

4. `hdfs namenode -format` : 하둡 파일시스템을 포맷 (`master`에서만 수행)
5. `start-dfs.sh` 을 마스터에서 실행
   - `jps` 실행하면 `NameNode` 확인할 수 있음 (`demon` 수행 확인)
   - `master` 에서만 실행했는데 다른 컴퓨터에서도 `SecondaryNameNode` 확인 가능
   - ` stop-dfs.sh` 종료