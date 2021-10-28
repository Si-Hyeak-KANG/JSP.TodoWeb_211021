# JSP.TodoWeb_211021
Todo List web service _ second project _ 211021 ~ 1103 

# 프로젝트 기획
### * 마감기한
11월 3일(수)

### * 개발 배경
실습을 통한 포트폴리오 확보 및 실무에 대한 대비
CRUD의 이해

### * 목적
DB와 연동한 TODO LIST 웹서비스 개발, 최대한 나의 생각대로 만들면서 CRUD에 대해 확실한 이해를 할 것이다.

### * WBS(작업시간) 예상

![wbs_todoWeb](https://user-images.githubusercontent.com/79829085/138304661-55626cb6-6488-4472-8eff-1e0de8829b1c.JPG)

### * 서비스 개요
클라이언트가 웹 브라우저를 통해 받는 일반적인 서비스

### * 기능 상세
1) Create(POST)
: input창에 입력 후 등록 버튼을 클릭 시 새 글 작성
2) ReadAll(GET)
: 등록된 글 전체 조회
3) ReadOne(GET)
: 등록된 단일 글 선택시 상세 조회
4) Update
: 선택 글 수정
5) DeleteAll
: 전체글 삭제
6) DeleteOne
: 선택 글 삭제

### * DB 모델링
 설계 후 업로드 예정

### * Flow chart
![todo_flow](https://user-images.githubusercontent.com/79829085/138698488-9251d310-6ec5-47cf-837a-d7e7234764d7.jpg)



# UI 디자인 (Figma)

1. main_home

![todoWebUI(home)_2](https://user-images.githubusercontent.com/79829085/138928705-7426164f-3179-4a5c-85de-e5624c9ad8c7.JPG)



2. (파란 플러스 버튼 클릭 시) 새 글 입력창 생성

![todoWebUI(insert)_2](https://user-images.githubusercontent.com/79829085/138928710-7646acaa-1e86-4957-8c90-b8153fcb0149.JPG)

# E.R.D(todo_table)

![todo_table_erd](https://user-images.githubusercontent.com/79829085/139273825-4a041958-9c82-4e0c-9ee5-ac741505af74.JPG)

# 추가 정보

server : tomcat 9.0

JRE : Java SE-10

DB : oracle

