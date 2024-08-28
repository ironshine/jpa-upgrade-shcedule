### API 명세서
| 기능           | Method     | URL | Request| Response |
|----------------|----------|-------------|------|------|
| 댓글 작성 | `POST` | /comments | 요청 body, param("schedule") | 댓글 정보(댓글 고유 식별자, 댓글 유저명, 댓글 내용, 작성일, 수정일) |
| 댓글 전체조회 | `GET` | /comments | - | 댓글전체 정보 |
| 선택한 댓글 조회 | `GET` | /comments/{id} | 요청 query | 선택한 댓글 정보 |
| 댓글 수정 | `PUT` | /comments/update/{id} | 요청 body, query | 댓글 수정 정보 |
| 댓글 삭제 | `DELETE` | /comments/delete/{id} | 요청 query | "삭제완료" |
| 일정 작성 | `POST` | /schedules | 요청 body, param("user") | 일정 정보(일정 고유 식별자, 작성 유저명, 할일 제목, 할일 내용, 작성일, 수정일) |
| 일정 전체조회 | `GET` | /schedules | 요청 param("page", "size") | 일정전체 정보 페이지네이션 |
| 선택한 일정 조회 | `GET` | /schedules/{id} | 요청 query | 선택한 일정 정보 |
| 일정 수정 | `PUT` | /schedules/update/{id} | 요청 body, query | 일정 수정 정보 |
| 일정 삭제 | `DELETE` | /schedules/delete/{id} | 요청 query | "삭제완료" |
| 유저 작성(회원가입) | `POST` | /users/signup | 요청 body | 유저 정보(유저 고유 식별자, 유저명, 이메일, 작성일, 수정일), JWT(1시간) |
| 유저 전체조회 | `GET` | /users | - | 유저전체 정보 |
| 선택한 유저 조회 | `GET` | /users/{id} | 요청 query | 선택한 유저 정보 |
| 유저 수정 | `PUT` | /users/update/{id} | 요청 body, query | 유저 수정 정보 |
| 유저 삭제 | `DELETE` | /users/delete/{id} | 요청 query | "삭제완료" |
| 유저 로그인 | `POST` | /users/login | 요청 body | "로그인 완료", JWT(1시간) |

------

### ERD
  
![image](https://github.com/user-attachments/assets/d486205b-2a1c-4780-aeb5-3b620c1b5ab7)
