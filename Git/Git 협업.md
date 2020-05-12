# Git 협업

### 1. PUSH/PULL

- 마스터가 주도하는 독재적, 수직적 형태
- 한명이 수정하고있으면 기다렸다가 수정해야하는 구조
- 협업 순서
  - `git pull origin master`
  - `git add READEME.md`
  - `git commit -m "message"`
  - `git push origin master`

### 2. Branching with Shared repository

#### Branch CLI

- `git branch [branch name]` : 브랜치 생성
- `git branch` : 현재 갖고있는 branch 이름들을 보여줌
- `git checkout [branch name]` : 브랜치 변경,이동
- `git checkout -b [branch name]` : 브랜치 생성하고 이동까지
- `git branch -D [branch name]` : 필요없는 브랜치 지우기
- `git merge [branch name]` : 합치는 주체 브랜치에서 합쳐지는 브랜치 이름 입력 
  - 내용이 복사되는 느낌이고 합쳐진 브랜치도 존재한다
- `git push --set-upstream origin 브랜치명` : 브랜치를 `github`에 올리기



#### Branch 를 통한 협업 원칙

- **master 브랜치는 절대!!! 건드리지 않는다**
- 각자는 각자의 브랜치에서 먼저 작업한다
  - 본인의 이름을 딴 브랜치를 생성 후 작업을 하고
  - 본인의 해당 브랜치를 push 한다
- 대장은 각자의 브랜치를 점검하고, merge 를 진행한다



#### conflict 를 두려워하지 말기

- merge 상황에서 발생함

1. **Fast Forward Merge**

   - commit 이 한쪽 브랜치에만 있는 경우

2. **Auto Merge**

   - 선결 조건 : 동일 파일의 특정 변경 사항이 중복되지 않을 경우(특정 파일의 내용을 두 사람이 함께 변경하지 않은 경우)

   - commit 이 여러 브랜드에 있는데 잘 merge 됨

3. **Merge Conflict**

<<<<<<< HEAD

\- master says : Conflict is not bad things for your slave

**=======**

\- I'm fking hate Conflict shit though

\>>>>>>> test



- DB의 관계
  - NO
  - 1:1
  - 1:N - 소속되는 경우(ex. 학급>학생) 외래키는 N에게



### 3. Fork&PR - pull request

#### (Open Source Contribute)

- **저장소의 작성 권한이 없을 경우 사용**

- 인턴 때 사용하게됨

- 다른사람의 리포지터리를 복사해서 내꺼로 만듬 .. 다른사람한테 영향 X

- 내가 고친 것을 원래 주인한테 바뀐내용을 제안할 수 있다

  - `New pull request` -> `create pull request`

- 주인은 바뀐 것중 컨펌해서 코드를 바꿀 수 있다

  - `merge request`

- ```
  
  git clone https://github.com/sehooh5/baekil.git
  ```
  





## Branching 협업 시나리오 

- /practice/collabo
- README.md , index.html 작성
- README.md : by boss
- index.html : by slave
- commit
- remote add
- push
- git hub 에서 setting -> 권한 주기
- 각자의 브랜치 만들고 작업후 푸시 **각자의 브랜치로 푸시**
- git hub 에서 각자 Pull Request해줌
- 그다음 PR을 점검하고 merge
- **merge 후에는 "master"를 기준으로 pull 을 통해 동기화한다** 
  - 이걸 안해서 conflict 발생경우가 많다
- 그리고 **기존 브랜치는 삭제**해준다 (로컬 컴퓨터)
- (대장/노예) Git Hub 브랜치도 삭제해준다





**이 시나리오를 매일매일 하게 된다**