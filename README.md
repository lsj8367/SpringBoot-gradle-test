# SpringBoot-gradle-test
스프링부트와 aws로 혼자 구현하는 웹서비스 실습
## **[바로가기](http://ec2-3-37-178-103.ap-northeast-2.compute.amazonaws.com:8080/)**

책에서 본 내용들을 토대로 게시판 구현

## 사용기술 
  - SpringBoot, JPA, MariaDB, Junit5, AWS(EC2, RDS, S3, CodeDeploy, IAM)
  - ThymeLeaf, H2, Oauth2, Spring Security

## API 명세
### 게시판
- 게시판 저장 - `POST /api/v1/posts` 
- 게시판 수정 - `PUT /api/v1/posts/{id}`
- 게시판 상세보기 - `GET /api/v1/posts/{id}`
- 게시판 삭제 - `DELETE /api/v1/posts/{id}`

## URL
### 페이지
- 조회 페이지 - `/`
- 등록 페이지 - `/posts/save`
- 수정 페이지 - `/posts/update/{id}` 

Spring Security 사용 Oauth 인증을 통한 Google, Naver 로그인

권한이 있는 유저만 글 작성 가능

## TRAVIS CI
S3, CodeDeploy, IAM 사용하여 깃허브 push로 자동 빌드 구현
### CI/CD 자동화 배포 기능 구현

