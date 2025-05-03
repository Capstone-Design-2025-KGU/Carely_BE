# Outline

경기대학교 2025년 심화캡스톤디자인 팀 Carely 프로젝트 백엔드입니다.

# Quick start

## Prerequisite

![Docker](https://img.shields.io/badge/-Docker-2496ED?logo=docker&logoColor=white)

## Getting start

만약 호스트의 8080포트를 사용중이라면 프로젝트 경로에 .env 파일을 만들고
`BE_PORT=<AVAILABLE_PORT>`를 추가하면 됩니다. ex)`BE_PORT=8081`

```bash
git clone https://github.com/Capstone-Design-2025-KGU/Carely_BE.git carely_be
cd carely_be
docker-compose up --build -d
```

Terminate

```bash
docker-compose down -v
```

# Commit convention

| 머릿말 | 설명 |
| --- | --- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| design | CSS 등 사용자 UI 디자인 변경 |
| !BREAKING CHANGE | 커다란 API 변경의 경우 |
| !HOTFIX | 코드 포맷 변경, 세미 콜론 누락, 코드 수정이 없는 경우 |
| refactor | 프로덕션 코드 리팩토링업 |
| comment | 필요한 주석 추가 및 변경 |
| docs | 문서 수정 |
| test | 테스트 추가, 테스트 리팩토링(프로덕션 코드 변경 X) |
| setting | 패키지 설치, 개발 설정 |
| chore | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우(프로덕션 코드 변경 X) |
| rename | 파일 혹은 폴더명을 수정하거나 옮기는 작업만인 경우 |
| remove | 파일을 삭제하는 작업만 수행한 경우 |

# Tech Stack

### Web Server
![Nginx](https://img.shields.io/badge/-Nginx-009639?logo=nginx&logoColor=white)

### Application Server
![Tomcat](https://img.shields.io/badge/-Tomcat-F8DC75?logo=apache-tomcat&logoColor=black)

### Backend
![Spring Boot](https://img.shields.io/badge/-Spring_Boot-6DB33F?logo=spring-boot&logoColor=white)

### Database
![MySQL](https://img.shields.io/badge/-MySQL-4479A1?logo=mysql&logoColor=white)

### Version Control
![Git](https://img.shields.io/badge/-Git-F05032?logo=git&logoColor=white)

### CI/CD
![Jenkins](https://img.shields.io/badge/-Jenkins-D24939?logo=jenkins&logoColor=white)

### DevOps & Cloud
![Docker](https://img.shields.io/badge/-Docker-2496ED?logo=docker&logoColor=white)
![AWS](https://img.shields.io/badge/-AWS-232F3E?logo=amazon-aws&logoColor=white)

### Collaboration
![Notion](https://img.shields.io/badge/-Notion-000000?logo=notion&logoColor=white)
