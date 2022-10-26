# Nisum User API

Made in Sprint Boot

## Installation

Use [maven
](https://maven.apache.org/install.html) to run the project.

```bash
mvn spring-boot:run
```

## Usage

There's one endpoint to test `POST /register` the endpoint have the necessary validations.

#### Body example
```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "hunter2",
  "phones": [
    {
      "number": "1234567",
      "cityCode": "1",
      "countryCode": "57"
    }
  ]
}
```

#### Response example
```json
{
  "id": "bcae101a-561f-4f91-b843-6b23554c5fbc",
  "created": "2022-10-26T15:12:46.628+00:00",
  "modified": "2022-10-26T15:12:46.628+00:00",
  "lastLogin": "2022-10-26T15:12:46.628+00:00",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJleHAiOjE2NjY4MTUxNjYsImlhdCI6MTY2Njc5NzE2Nn0.WR3TXffocW67hGeU-NU5lsgrk21NSZyF-e2hk346wis5NCnv7nx1l1i6gGRJ6bG9g1Pg0opujZ-2Xh2lOAI6Kw",
  "active": true
}
```