# Hadoop memo

## Yarn

- Yet Another Resource Nagotiator
- 각 어플리케이션(MapReduce 등등)에 필요한 리소스(CPU, 메모리, 디스크)를 할당하고 모니터링 하는 업무
- MapReduce 의 단점을 극복하기 위해 시작된 프로젝트





## Yarn의 구성요소

### ResourceManager

- 클러스터에 1개 존재
- 클러스터의 전반적인 자원 관리와 스케쥴러, `Application Manager`를 조정하는 역할
- 클라이언트로부터 어플리케이션 실행 요청을 받으면, 그 실행을 책임질 `Application Manager`을 실행한다
- 클러스터 내의 `Node Manager`들과 통신하여 할당된 자원과 사용중인 자원의 상황을 알 수 있다.

### Scheduler

- `Node Manager`들의 자원 상태를 관리하며 부족한 리소스를 할당한다
- 자원 상태에 따라 테스크들의 실행 여부를 허가해주는 역할인 스케쥴링 작업만 담당한다
- `Node Manager`들의 자원 상태를 `Resource Manager`에게 통지

### ApplicationManager

- `Node Manager` 에서 특정 작업을 위해 Application Manager를 실행하고, Application의 실행 상태를 관리하여 그 상태를 Resource Manager에게 통지한다.