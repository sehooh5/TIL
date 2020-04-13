# CLI

**Command Line Interface : 명령어 인터페이스**



## 명령어

- `ls` : 현재 디렉토리 내부의 파일 & 디렉토리를 보여줌 (**l**i**s**t)
- `pwd` : 현재 위치를 출력함 (**p**rint **w**orking **d**irectory) 
  - 절대(최상단 디렉토리부터)경로
- `mkdir [디렉토리명]` : 디렉토리(폴더) 생성 (**m**a**k**e **dir**ectory)
- `cd [경로]` : 디렉토리를 변경/이동 (**c**hange **d**irectory)
- `cd ..` : 상위 디렉토리로 이동(..)
- `cd .` : 현재 디렉토리로 이동(.)
- ~~`cd /` : 루트(최상단) 디렉토리로 이동(/) - linux 상의 최상위..사용 잘 안함~~
- `cd ~` : 최상단 디렉토리로 이동(~)
- `git init` : git 관리 시작
- `ls -al` : 보이지 않는 폴더까지 다 보여줌
- `rm [파일명]` : 파일을 삭제함 (**r**e**m**ove)
- `rm -r .git` : git 설정된 폴더 해제하는 방법
- `git add [파일명]` : 스테이지에 올리기
- `git rm --cached [파일명](-r [폴더명])` : 스테이지에서 다시 내려오게끔
  - = `git restore --staged [파일명]`
- `git commit -m "message"` : snapshot=현재상태 저장하기
- `git log` : commit history
  - `git log --oneline`
  - `git log --oneline -[갯수]`
- `git status` : commit, add 상태 알려줌
- `git diff` : 변경 내용 알려줌
- `git remote add [저장소 이름] [저장소 주소]` : 원격 저장소 설정
  - `git remote rename [원래이름] [바꿀이름]`
  - `git remote remove [저장소 이름]` : 원격저장소 지우기
  - `git remote -v` : 확인
- `git push [저장소 이름] [브랜치 이름]` : 원격 저장소에 푸시
- `git clone [저장소 주소]` : 
- `touch [file name]`: make file
- `rm -rf  [directory name]` : git 해제
- HEAD -> master : HEAD 는 마지막 커밋을 가르키는 포인터
- `git branch [branch name]` : 브랜치 생성
- `git branch` : 현재 갖고있는 branch 이름들을 보여줌
- `git checkout [branch name]` : 브랜치 변경,이동
- `git branch -D [branch name]` : 필요없는 브랜치 지우기
- `git merge [branch name]` : 합치는 주체 브랜치에서 합쳐지는 브랜치 이름 입력 
  - 내용이 복사되는 느낌이고 합쳐진 브랜치도 존재한다
- **Push**
  - `git pull origin master`
  - `git add READEME.md`
  - `git commit -m "message"`
  - `git push origin master`
- 













```java
[재귀 함수]
public static void hello(){
    hello()
}
```

