## 기술 스택
- `Java 17`
- `Gradle`
- `Spring Boot 3.2.0`
- `Spring Data JPA`
- `Spring Security`
- `jsonwebtoken`
- `H2 Database`

## 프로젝트 구조
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.seungilahn
│   │   │       └── todolist
│   │   │           ├── adapter
│   │   │           │   ├── in
│   │   │           │   └── out
│   │   │           ├── application
│   │   │           │   ├── port
│   │   │           │   │   ├── in
│   │   │           │   │   └── out
│   │   │           │   └── service
│   │   │           ├── auth
│   │   │           ├── common
│   │   │           ├── configuration
│   │   │           ├── domain
│   │   │           └── user
```

## API
### 회원가입
- URL: `/api/v1/auth/signup`
- Method: `POST`
- Request Body
```json
{
  "email": "test@email.com",
  "nickname": "test nickname",
  "password": "test1234",
  "role": "USER"
}
```
- Response
```json
{
  "access_token": "..",
  "refresh_token": ".."
}
```

<br>

### 로그인
- URL: `/api/v1/auth/signin`
- Method: `POST`
- Request Body
```json
{
  "email": "test@email.com",
  "password": "test1234"
}
```
- Response
```json
{
  "access_token": "..",
  "refresh_token": ".."
}
```

<br>

### 로그아웃
- URL: `/api/v1/auth/signout`
- Method: `POST`
- Request Body
```json
{
  "refresh_token": ".."
}
```

<br>

### Refresh Token
- URL: `/api/v1/auth/refresh-token`
- Method: `POST`
- Request Body
```json
{
  "refresh_token": ".."
}
```
- Response
```json
{
  "access_token": "..",
  "refresh_token": ".."
}
```

<br>

### 회원 탈퇴
- URL: `/api/v1/users`
- Method: `DELETE`
- Header
```
Authorization: Bearer {access_token}
```

### ToDo 생성
- URL: `/api/v1/todos`
- Method: `POST`
- Header
```
Authorization: Bearer {access_token}
```
- Request Body
```json
{
  "title": "test title",
  "content": "test content"
}
```
- Response
```json
{
  "id": 1,
  "title": "test title",
  "content": "test content",
  "created_at": "2022-01-01T00:00:00",
  "updated_at": "2022-01-01T00:00:00"
}
```

<br>

### ToDo List 조회
- URL: `/api/v1/todos`
- Method: `GET`
- Header
```
Authorization: Bearer {access_token}
```
- Request Param
```
cursor: 0 (default 0)
limit: 10 (default Integer.MAX_VALUE)
sort: DESC | ASC (default DESC)
```
- Response
```json
{
  "total_count": 1,
  "data": [
    {
      "id": 1,
      "title": "test title",
      "content": "test content",
      "status": "TODO",
      "created_at": "2022-01-01T00:00:00",
      "updated_at": "2022-01-01T00:00:00"
    }
  ]
}
```

<br>

### ToDo 상태 변경
- URL: `/api/v1/todos/{id}`
- Method: `PUT`
- Header
```
Authorization: Bearer {access_token}
```
- Request Body
```json
{
  "status": "DONE(TODO | ONGOING | STANDBY | DONE)"
}
```
- Response
```json
{
  "id": 1,
  "title": "test title",
  "content": "test content",
  "status": "DONE",
  "created_at": "2022-01-01T00:00:00",
  "updated_at": "2022-01-01T00:00:00"
}
```