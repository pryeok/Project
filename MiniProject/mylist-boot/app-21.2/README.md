### 21.2 로그 출력 : 스프링부트의 기본 로그 사용하기

- 스프링부트에 기본으로 설정된 로그를 사용하여 출력하기

## 백엔드 개발 실습

### 1단계 - Log4j2 라이브러리 설정을 제거한다.

- build.gradle 변경

```
// 다음 설정을 제거한다.
configurations {
    all {
        exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
    }
}
```

```
// 다음 라이브러리를 제거한다.
implementation 'org.springframework.boot:spring-boot-starter-log4j2'
```

### 2단계 - 로깅 레벨을 설정한다.

- /src/main/resources/application.properties 변경

다음 설정을 추가한다.
```
logging.level.root=info
logging.level.com.eomcs.mylist=debug
```

### 3단계 - 로그 객체를 사용하여 로그를 출력한다.

- 로그를 출력할 클래스에 적용


## 프론트엔드 개발 실습


#
