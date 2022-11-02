# Where Is My Home

## Contents

1. 회원 정보 관리
2. 생각해 봐야할 기능
3. AOP를 사용해 예외처리 관리하기
    - BadSqlGrammarException
    - NullPointerException
4. Interceptor를 사용한 login required 구현
    - Interceptor 정의하기
5. WebCam
6. 아파트 실거래가 조회
7. 게시판
8. Swagger API document
9. 사용자 정보 암호화 하기
10. SQL Injection
11. CSRF

### Member

|이름 |
|--|
|이상원|
|정원철 |

## 프로젝트 구조

#### Server

```shell
.
├── ServletInitializer.java
├── WhereIsMyHouseApplication.java
├── aop
│ └── GlobalExceptionHandler.java
├── board
│ ├── controller
│ ├── dto
│ ├── mapper
│ └── service
├── common
│ ├── controller
│ └── dto
├── config
│ ├── SwaggerConfiguration.java
│ └── WebConfig.java
├── error
│ └── ErrorResponse.java
├── house
│ ├── controller
│ ├── dto
│ ├── mapper
│ └── service
├── interceptor
│ └── MemberInterceptor.java
├── member
│ ├── controller
│ ├── dto
│ ├── mapper
│ └── service
├── security
│ ├── dto
│ └── mapper
└── util
    └── CipherUtil.java
```

### Client

```shell
.
├── board
│ ├── detail.jsp
│ ├── list.jsp
│ ├── result.jsp
│ └── upload.jsp
├── common
│ ├── head.jsp
│ ├── header.jsp
│ └── script.jsp
├── house
│ └── deal.jsp
├── index.jsp
└── user
    ├── info.jsp
    ├── login.jsp
    └── register.jsp
```

## 데이터베이스 구조

![](https://user-images.githubusercontent.com/55802893/199618360-ee544528-0dda-4e89-9b8b-272ecacaf733.png)

## 회원정보 관리

- 회원정보 확인

        로그인 상태로 현재 회원정보확인을 할수있는 "info" 탭을 누르게되면 현재 세션이 로그인되어있는 회원의 정보가 출력된다.

  ![회원정보확인-1](https://user-images.githubusercontent.com/74225835/199532797-16169ab8-d747-436b-b314-4633908ba633.PNG)

- 회원정보 수정

        위에서 띄운 각 회원정보란의 입력과 삭제를 통하여 회원의 정보를 수정할 수 있다. id는 readonly처리를 통하여 변경할수없도록 설정되었다.

  ![회원정보수정 -2](https://user-images.githubusercontent.com/74225835/199533066-2f8555b3-8d70-413d-8e76-edd550a94617.PNG)
  ![회원수정완료- 3](https://user-images.githubusercontent.com/74225835/199533079-fca18a5a-41d0-4e8e-b2e6-a49855d28a3b.PNG)


- 회원정보 삭제

        회원탈퇴 버튼을 클릭하게되면 현재 세션에 로그인되어있는 id 값을 토대로 회원의 정보를 삭제하게된다. 현재 회원의 정보를 삭제한것이기에 회원정보 삭제뿐만 아니라 현재 로그인 되어있는 세션을 종료처리해주어 자동으로 로그아웃 하도록 설정되었다.

  ![디비 전 -4](https://user-images.githubusercontent.com/74225835/199533533-2ed5ec4c-1a57-40c8-8e9e-6b05a899f19b.PNG)
  ![탈퇴 후 -5](https://user-images.githubusercontent.com/74225835/199533555-06abe774-0cd8-4c73-a4e1-aaf046177cb2.PNG)
  ![탈퇴후 디비 -6](https://user-images.githubusercontent.com/74225835/199533567-5f981bdf-0567-47fb-9941-a22b58656c2e.PNG)

- 회원등록 기능

        register 버튼을 통하여 서브탭이 팝업되며 해당 회원가입팝업에서 성명, 포지션, ID, 비밀번호와 이메일 그리고 전화번호를 기준으로 회원가입을 진행한다. 이미 이전에 가입한 이력이 있는 ID(이미 DB에 존재하는 ID)는 중복검사를 통하여 확인한다. 중복되는 아이디일 경우에는 회원가입이 되지 않도록 설정을 했다.

  ![회원등록 입력 -7](https://user-images.githubusercontent.com/74225835/199534296-79ae732d-584c-4b11-97c3-de721a6dd49f.PNG)
  ![회원등록 완료 -8](https://user-images.githubusercontent.com/74225835/199534310-a9535ee6-73fc-44fb-85a5-e404df5df4f6.PNG)
  ![등록히 디비 -9](https://user-images.githubusercontent.com/74225835/199534321-c2c0a23a-8aa9-44b6-bafe-5421837da173.PNG)

## 생각해봐야할 기능

- 공지사항 글 작성관련 권한 설정방법

일반적인 공지사항에서는 Admin(관리자)는 글 작성, 열람, 그리고 많으면 수정 권한까지 존재하고 일반 user는 오직 열람만을 할수있다. 그런 권한 설정을 어떻게 해야할지 생각해보았을때 가장 간단한 방법으로는
해당 사이트의 회원목록 DB에 관리자와 회원간의 차별을 둔 다른 컬럼을(0 OR 1) 설정한후 글 작성과 수정을할시 해당 컬럼의 확인을 통하여 권한이 존재하는지 체크하는 방법이 있을 것 같다고 생각을 했다.

## AOP를 사용해 예외처리 관리하기

AOP는 관점지향 프로그래밍으로 로직을 기준으로 핵심 관점과 부과적인 관점으로 나눠서 보고 그 관점을 기준으로 각각을 모듈화 하는 것이다.

핵심 기능은 비즈니스 로직을 구현하는 과정에서 비즈니스 로직이 처리하려는 목적 기능을 말한다. 클라이언트로부터 상품 정보 등록 요청을 받아 DB에 저장하고, 상품 정보를 조회하는 비즈니스 로직을 구현할 경우, 정보를
저장하는 것과 정보 데이터를 보여주는 부분이 핵심이다.

클래스들은 Aspect를 재활용하여 사용할 수 있다. Service 비즈니스 로직에서 트랜잭션이라는 부가 기능 관심사를 분리할 수 있다.

```java

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponse> NullPointerException(NullPointerException n) {
        n.printStackTrace();
        ErrorResponse response = new ErrorResponse(404, n.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLSyntaxErrorException.class)
    public ResponseEntity<ErrorResponse> SqlSyntaxErrorException(SQLSyntaxErrorException s) {
        System.out.println("sql syntax error");
        ErrorResponse response = new ErrorResponse(404, s.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseEntity<ErrorResponse> BadSqlGrammarException(BadSqlGrammarException b) {
        b.printStackTrace();
        ErrorResponse response = new ErrorResponse(404, b.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public String Exception(Exception e) {
        System.out.println("Exception");
        return "sql grammar error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeException(RuntimeException r) {
        System.out.println("run time exception");
        return "run time exception";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> NotFoundException(NotFoundException n) {
        ErrorResponse response = new ErrorResponse(404, n.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
```

`GlobalExceptionHandler.java`이다. 발생할 수 있는 예외들을 정의해 놓은 파일이다. ControllerAdvice 어노테이션으로 모든 controller에 대한 예외를 처리해 준다. 각
메서드에는 ExceptionHandler 어노테이션이 붙어 있다. ExceptionHandler는 어떤 예외 클래스에 대한 처리를 할지 정의해 주는 것이다. ExceptionHandler로 예외 클래스를 지정해 주기
때문에 각 메서드들의 이름은 큰 의미가 없다. 웹 서비스에서 일부러 예외를 발생시켜 확인해 보자.

```java
// ErrorResponse.java

@Getter
public class ErrorResponse {
    private String message;
    private int status;

    public ErrorResponse(int i, String message) {
        this.status = i;
        this.message = message;
    }
}
```

위 코드는 `GlobalExceptionHandler.java`에서 사용하는 `ErrorResponse` 객체이다.

### BadSqlGrammarException

```xml

<select id="login" resultType="Member" parameterType="map">
    select *
    from member_
    where id = #{id}
    and pw = #{pw};
</select>
```

위 코드는 mybatis(mybatis 설명은 생략한다)로 정의한 사용자 로그인 쿼리이다. `from member`가 올바른 쿼리이며, `member_`로 쿼리에서 오류를 발생시켜보았다. 로그인 요청을 하게되면
BadSqlGrammarException이 발생하게 된다.

![스크린샷 2022-10-26 오후 8.46.10.png](/readme-image/pic1.png)

```java
@ExceptionHandler(BadSqlGrammarException.class)
public ResponseEntity<ErrorResponse> BadSqlGrammarException(BadSqlGrammarException b){
        System.out.println("bad grammar");
        ErrorResponse response=new ErrorResponse(404,b.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
```

ExceptionHandler에 예외 클래스를 정의해 준다. ErrorResponse 객체로 오류 발생 시 반환할 객체를 생성하고 ResponseEntity로 반환해 준다.
![](/readme-image/pic2.png)

postman으로 로그인 요청을 보낸 후 받은 응답 body이다. 요청에 대한 응답은 ErrorResponse 객체를 받은 것을 확인할 수 있다. errors와 code는 정의하지 않아 null로 반환되었다. 코드
위쪽의 이미지에서 BadSqlGrammarException이 콘솔에 찍혀 있는것을 확인할 수 있다.

### NullPointerException

다시 사용자 로그인을 사용해 NullPointerException을 발생시켜 본다. 위에서 로그인 쿼리는 다시 되돌려 놓는다. 이번에는 데이터베이스에 존재하지 않는 정보로 로그인을 시도한다.

![](/readme-image/pic3.png)

id가 p이고 pw가 1234인 사용자는 데이터에 존재하지 않는다. 이 데이터를 가지고 로그인 요청을 하게되면 NullPointerException이 발생한다.

![](/readme-image/pic4.png)

```java
@ExceptionHandler(NullPointerException.class)
public ResponseEntity<ErrorResponse> NullPointerException(NullPointerException n){
        n.printStackTrace();
        ErrorResponse response=new ErrorResponse(404,n.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
```

ExceptionHandler에 NullPointerException으로 예외 클래스를 정의해 준다. 데이터베이스에 존재하지 않는 사용자이므로 NullPointerException가 발생하는 것을 볼 수 있다.
ErrorResponse로 응답 객체를 만들고 ResponseEntity로 json 형태로 반환하게 되면 다음과 같은 응답을 얻을 수 있다.

![](/readme-image/pic5.png)

에러의 메세지와 지정해준 상태 코드가 담겨 응답으로 반환된다. errors와 code 데이터는 추가하지 않아 null을 반환하게 된다.

## Interceptor를 사용한 login required 구현

웹 서비스의 사용자가 로그인이 되어 있는지 확인하기 위한 interceptor를 구현해 본다. client에서 요청된 url로 넘어가는 과정에서 조건을 충족하는지 확인하는 미들웨어 역할을 한다.

### Interceptor 정의하기

```java

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final MemberInterceptor memberInterceptor;

    private static final List<String> interceptorUrlPatterns = Arrays.asList("/user/*", "/board/*");
    private static final List<String> excludeInterceptorUrlPatterns = Arrays.asList("/user/login", "/user/register", "/house/*");

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(memberInterceptor)
                .addPathPatterns(interceptorUrlPatterns)
                .excludePathPatterns(excludeInterceptorUrlPatterns);
    }
}
```

`WebMvcConfigurer`를 implements하여 addInterceptors를 override한다. Interceptor를 적용할 url 패턴과 제외할 패턴을 지정할 수 있다.

```java

@Component
public class MemberInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 컨트롤러 메서드에 매핑된 uri가 호출 되면 실행 되는 메서드 controller 진입 직전 수행
        HttpSession session = request.getSession(false);
        if (session != null) {
            Member member = (Member) session.getAttribute("member");
            if (member == null) {
                response.sendRedirect(request.getContextPath() + "/");
                return false;
            }
            request.setAttribute("member", member);
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
```

`MemberInterceptor`클래스에 HandlerInterceptor를 implements한다. 3가지의 메서드를 override할 수 있으며, controller의 메서드에 도달하기 전에
동작하는 `preHandler`를 사용한다.
session이 존재하면 session에서 로그인 시 저장된 member 객체를 얻어온다. member 객체가 존재하는 경우 request의 속성에 member를 추가해 준다. Controller에서는
HttpServletRequest에서 member 속성을 받아 현재 로그인된 사용자의 정보를 사용할 수 있다.
session이 존재하지 않거나 member 객체가 존재하지 않은 경우 index 페이지로 redirect 하게 된다.

## WebCam

![webcam](https://user-images.githubusercontent.com/55802893/199490364-1ae102a2-4123-4929-be1f-f4fe33ee86ea.png)

- header 부분에 `#Selfie`를 클릭하게 되면 위와 같은 웹캡을 사용할 수 있는 모달창이 뜬다.

## 아파트 실거래가 조회

### 메인페이지 (실거래 페이지)

- 지도 api 연동

        deal info 탭에 접속하게되면 카카오맵 연동을 통해 현재 임의로 설정해놓은 위도와 경도로를 기반으로 첫 뷰가 보이게된다.

- 아파트 실거래가 검색

        deal info page에서 시/도, 구/군, 동 그리고 날짜선택을 통하여 해당 선택에 부합하는 아파트의 실거래가 매매정보가 각 정보 Colunm에 맞게 리스트로 출력이된다. 상위 지역에서 존재하는 하위지역명이 각각 다르기에 이를 따로 설정해주어 지역을 고를때 구역을 나눠서 설정했다.

![](https://user-images.githubusercontent.com/55802893/199491055-b6898682-99cb-46b9-b4e1-41f4d543a991.png)

- 시, 군구, 동, 년도, 월을 선택한 후 `매매정보가져오기`를 클릭하게 되면 아파트 실거래 정보를 얻을 수 있다.
- 아파트 이름을 클릭하게 되면 해당 아파트의 좌표로 지도가 이동한다.

## 게시판

### 공지사항 (게시판 페이지)

- 글 목록 확인

        공지사항 페이지는 기본적으로 로그인상태에서 열람이 가능하다. 로그인이 되지 않은 상태에서는 Board탭이 활성화 되지 않고, 또한 주소로 강제로 이동을 한다고 하더라도 세션처리를 통하여 접속을 막는다. Board 탭에 접속하게되면 이전에 작성되었던 글들이 페이지 단위로 출력이된다. 각 페이지 넘버와 작성자 그리고 작성날짜가 출력되는데, 총 작성된 게시글의 수가 설정범위를 넘어가게되면 다음 페이지로 넘겨 출력하게된다.

- 글 작성

        글쓰기 버튼을 작성하게되면 제목과 내용 그리고 파일을 업로드할수있는 공란이 화면에 출력되게된다. 제목란과 글 내용란을 작성하고 파일업로드를 진행 후 확인을 누르게 되면 해당 글내용이 위 글 목록페이지에 표현된다.

### 게시판 목록 조회

![](https://user-images.githubusercontent.com/55802893/199528095-162180ac-76ab-4b7b-8bdd-c5c9ec1549e1.png)

- 사용자들이 작성한 게시판이다. 글을 조회할 때마다 글 번호인 index가 갱신되며 제목, 작성자, 작성 날짜가 보인다.

### 게시글 작성하기

![](https://user-images.githubusercontent.com/55802893/199530508-c8eb62d1-ab49-4182-a4f1-d33bfb980e3f.png)
![스크린샷 2022-11-03 오전 12 29 57](https://user-images.githubusercontent.com/55802893/199531521-46fb6130-945e-47c0-a8bc-e007eb48ef47.png)

- 게시글의 제목과 내용, 파일을 선택하고 제출을 누르면 게시글이 작성되고 파일은 `resources/static/files`에 저장된다.

### 게시판 상세 보기

![](https://user-images.githubusercontent.com/55802893/199528882-043a1e37-9c02-4f4c-87e0-858e69632633.png)

```java
@GetMapping("detail/{code}")
public String detail(@PathVariable String code,HttpServletRequest req){
        BoardDto boardDto=boardService.select(code);
        req.setAttribute("detail",boardDto);
        return"board/detail";
        }
```

- 게시판 상세보기 페이지이다. 목록에서는 GET요청으로 글의 id를 query string으로 담아 요청한다.
- @PathVariable 어노테이션으로 query string 값을 받아 올 수 있다.
- 상세보기 페이지에서 내용을 수정한 뒤 `제출`을 누르게 되면 게시글의 정보가 수정된다.

## Swagger API document

swagger 3.0을 이용해서 API문서를 사용할 수 있다.

### 의존성 추가

```xml

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
        <!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
<groupId>io.springfox</groupId>
<artifactId>springfox-swagger-ui</artifactId>
<version>3.0.0</version>
</dependency>
```

- springfox-boot-starter와 springfox-swagger-ui 3버전을 추가한다.

### Swagger Configuration

```java

@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
```

- Swagger 사용을 정의해 준다. @Configuration 어노테이션을 사용해 설정 파일임을 명시해 준다.
- API 문서의 설명을 정의할 수 있다.

### Controller에서 swagger 정의

```java

@Api(tags = {"users"})
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class MemberController {
    @PostMapping("/login")
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")
    })
    public ResponseEntity<ResponseDTO> login(
            @ApiParam(value = "member")
            @RequestBody LoginDTO member,
            HttpServletRequest req) {
        ResponseDTO res = new ResponseDTO();
        try {
            // 로그인 프로세스 추가
            Member user = memberService.login(member);
            HttpSession session = req.getSession();
            session.setAttribute("member", user);

            res.setStatus("success");
            res.setMsg("login success");
            res.setBody(user);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            res.setStatus("fail");
            res.setErrMsg("로그인 정보가 잘못되었습니다. 다시 로그인해 주세요");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }
}
```

- Controller class에 `@Api(tags = {"users"})`로 Controller의 도메인을 지정해 준다.
- Controller의 각 메서드에 @ApiResponses 어노테이션으로 응답에 대한 정보를 정의해 줄 수 있다.
- 메서드의 파라미터에 @ApiParam()으로 파라미터 값의 정보를 명시해 줄 수 있다. 파라미터 타입이나 required 여부 등을 정의할 수 있다.

### Swagger api 문서 보기

![스크린샷 2022-11-03 오전 1 03 54](https://user-images.githubusercontent.com/55802893/199540419-b8c69d32-1c47-4037-8555-a692f80d14d0.png)

- 위 login 메서드의 요청 부분이다.
- 파라미터로 LoginDTO를 받게 되고 LoginDTO에는 id와 pw가 String으로 들어오게 된다.
- 응답은 ResponseDTO 객체를 반환해 주는데 응답 예시를 알 수 있다.
- 파라미터 정보와 응답 정보를 통해 어떤 데이터 형식으로 요청을하고 반환되는지 API 문서만 보고 이해할 수 있다.

## 사용자 정보 암호화 하기

암호화는 크게 양방향 암호화, 단방향 암호화가 있다. 양방향은 키를 이용한 복호화가 가능하다는 차이점이 있다.

### 양방향 암호화

양방향 암호화에는 대칭키 방식과 비대칭키 방식으로 나눌 수 있다. 대칭 키 방식은 암호화와 복호화에 사용하는 키가 같은 것이고, 비대칭키 방식은 암호화 하는 키와 복호화 하는 키가 다른 것이다.

대칭키 방식에는 AES, DES, SEED 등이 있다. 이번 포스팅에서는 AES128 방식을 사용할 것이다.

비대칭키 방식에는 DAS, RSA가 있다. 대칭키에 비해 느리지만, 키 생성 시 private key와 public key가 생성된다.

### 단방향 암호화

단방향 암호화는 hash를 이용해서 암호화 하는 방법이다. 평문을 암호화 할 수 있지만, 복호화는 불가능하다.

단방향 암호화는 사용자 인증, 인가에 사용되는 JWT(json web token)에 사용되는 방식이다.

### 사용자 정보 암호화 하기

이번 프로젝트에서는 `AES128` 방식으로 평문을 암호화 해볼 것이다. 암호화가 사용되는 시점은 사용자 회원가입 시 발생하게 된다.

비밀번호는 단방향 해시 알고리즘인 `SHA256`을 사용해 암호화 할 것이다. 단방향 암호화 알고리즘을 사용해 복호화가 불가능하게 한다.

`java.security`와 `javax.crypto` 클래스에서 import 하여 사용한다.

### 회원가입 시 사용자 정보 암호화

![](https://user-images.githubusercontent.com/55802893/199544887-6a937986-b60a-4e3e-bc76-43fe8a877444.png)

#### 1) Generate key

AES 128 방식으로 secret key를 발급한다.

```java
import javax.crypto.KeyGenerator;

public class Encrypt {
...

    public static byte[] generateKey(String algorithm, int keySize) throws
            NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }
...
}
```

`generateKey` 메서드에 `algorithm`에는 암호화의 방식, `keySize`는 키의 크기가 들어간다. `algorithm`은 AES, `keySize`는 128로 한다.

javax.crypto의 KeyGenerator를 사용해 알고리즘 종류와 키의 크기로 초기화 해준다. KeyGenerator의 generateKey()를 이용해 키를 생성한 후 getEncoded()로 키를
반환한다.

```java
public static String byteArrayToHex(byte[]ba){
        if(ba==null||ba.length==0){
        return null;
        }
        StringBuffer sb=new StringBuffer(ba.length*2);
        String hexNumber;
        for(int x=0;x<ba.length;x++){
        hexNumber="0"+Integer.toHexString(0xff&ba[x]);
        sb.append(hexNumber.substring(hexNumber.length()-2));
        }
        return sb.toString();
        }
```

`generateKey()`메서드에서 얻어진 byte 배열을 16진수의 문자열로 변환하는 `byteArrayToHex` 메서드이다.

```java
byte[]key=generateKey("AES",128);
        System.out.println("key length: "+key.length);
        String hexKey=byteArrayToHex(key);
        System.out.println(hexKey);

/*
key length: 16
9880ac1ecc316c9929b700a95b38b372
*/
```

`byteArrayToHex`를 사용하여 secret key를 출력해 보았다. 키의 길이는 16, 아래는 키를 16진수로 변환한 것이다.

이제 암호화에 사용할 secret key는 발급되었다. security VO 모델에 사용자 id와 salt에 저장될 UUID, 발급된 AES 128 16진수 키를 저장한다.

![](https://user-images.githubusercontent.com/55802893/199545322-8c683b7e-0712-4642-8ba6-17b104d3f747.png)

#### 2) 사용자 이름 암호화

```java
public static String aesEncrypt(String msg,byte[]key)throws Exception{
        SecretKeySpec skeySpec=new SecretKeySpec(key,"AES");
        Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv="AAAAAAAAAAAAAAAA";
        cipher.init(Cipher.ENCRYPT_MODE,
        skeySpec,
        new IvParameterSpec(iv.getBytes()));
        byte[]encrypted=cipher.doFinal(msg.getBytes());
        return byteArrayToHex(encrypted);
        }
/*
평문: lee
encrypt: 91f0742622d1defa32ca78f399cd62e7
*/
```

데이터베이스에 저장되는 사용자의 정보는 마스킹한 후 저장되어야 한다. 키를 사용해 암호화와 복호화가 가능한 Cipher 클래스를 사용해 인스턴스를 생성하고, Cipher mode, secret key, 복호화에
사용할 키를 넣어 초기화해 준다. 초기화한 Cipher에 평문을 byte로 넣어 암호화 한 후 16진수로 변환해 반환한다. 반환된 16진수 문자열은 데이터베이스에 저장된다.

#### 3) 사용자 비밀번호 암호화

```java
public static byte[]getSHA256(String source,String salt){
        byte byteData[]=null;
        try{
        MessageDigest md=MessageDigest.getInstance("SHA-256");
        md.update(source.getBytes());
        md.update(salt.getBytes());
        byteData=md.digest();
        System.out.println("원문: "+source+" SHA-256: "+
        byteData.length+","+byteArrayToHex(byteData));
        }catch(NoSuchAlgorithmException e){
        e.printStackTrace();
        }
        return byteData;
        }
/*
원문: 1234
SHA-256: 4b3bed8af7b7612e8c1e25f63ba24496f5b16b2df44efb2db7ce3cb24b7e96f7
pw encrypt: K;���a.�%�;�D���k-�N�-��<�K~��
*/
```

비밀번호는 단방향 암호화 알고리즘인 SHA256 방식으로 암호화 되어 저장된다. 복호화 가능한 키가 없는 해시 알고리즘이다. `getSHA256`메서드의 두 번째 인자인 `salt`는 위에서 security VO
모델에 저장한 salt를 사용하게 된다.

MessageDigest 클래스를 사용하여 인스턴스를 생성해 준다. 평문과 salt를 byte로 변환해 추가한다. 데이터베이스에 암호화된 byte 데이터가 저장된다.

#### 4) 로그인 시 암호화된 비밀번호와 사용자 입력 비밀번호 확인

```java
public Member login(String id,String pw){
        // password 암호화 후 데이터베이스와 비교
        String databasePw=securityService.selectByUserId(id); // 사용자 salt 받아오기 
        String hashedPassword=new String(CipherUtil.getSHA256(pw,databasePw)); // 사용자 입력 비밀번호 평문 -> 암호화 
        return memberDao.login(id,hashedPassword);
        }
```

데이터베이스에 사용자 비밀번호는 해시 함수를 사용하여 암호화 되어 있다. 사용자가 정보를 변경하거나 어떤 동작에서 비밀번호를 입력 하는 행위가 필요한 경우 사용자가 입력한 비밀번호를 동일한 방식으로 해시화 해서
데이터베이스에 저장된 값과 비교한다.

## SQL Injection

- SQL Injection은 악의적인 사용자가 보안상의 취약점을 이용해 임의의 sql 쿼리를 주입하여 실행하게 한다. 데이터베이스가 비 정상적으로 동작하도록 하는 것이 목적이다.

### SQL Injection 대응 방안

#### 입력 데이터 검증

- 사용자가 입력한 값에 대해 검증
- 서버에서 화이트리스트 기반으로 검증

#### Prepared Statement 구문 사용

- 사용자의 입력 값이 데이터베이스의 파라미터로 들어가기전에 DBMS가 미리 컴파일 하여 실행하지 않고 대기한다.
- 이후 사용자의 입력 값을 문자열로 인식하여 들어가기 때문에 공격 쿼리가 포함되어 있어도 단순 문자열 형태로 공격을 할 수 없다
- mybatis 를 사용하게되면 Prepared Statement를 사용하므로 대응 가능하다.

#### Error Message 숨기기

- 데이터베이스 요청 시 발생한 에러를 그대로 반환하게 되면, 테이블명, 컬럼명 등 공격자에게 그대로 노출이 되기 때문에 에러 발생 시 사용자에게 에러 페이지나 에러 메세지를 따로 보여준다.

#### 웹 방화벽 사용

- 소프트웨어와 하드웨어, 프록시로 세 가지 종류로 나눌 수 있다.
- 소프트웨어 형은 서버 내에 직접 설치하는 방법이다.
- 하드웨어 형은 네트워크 상에서 서버 앞 단에 직접 하드웨어 장비로 구성한다.
- 프록시 형은 DNS 서버 주소를 웹 방화벽으로 바꾸고 서버로 가는 트래픽이 웹 방화벽을 먼저 거치도록 한다.

## CSRF(Cross site request forgery)

사이트 간 요청 위조는 공격자가 의도한 행위를 특정 웹 사이트에 사용자가 요청하게 하는 공격이다. 특정 웹 사이트가 사용자의 웹 브라우저를 신뢰하는 상태를 노린 공격이다.
사용자가 웹 사이트에 로그인한 성태에서 사이트간 요청 위조 공격 코드가 삽입된 페이지를 열면, 공격 대상이 되는 웹 사이트는 위조된 공격 명령이 믿을 수 있는 사용자로부터 발송된 것으로 판단하여 공격에 노출된다.

### CSRF 취약점 대응 방안

#### Referer 검증

- 백엔드에서 request의 referer를 확인해 도메인이 일치하는지 검증한다.

#### Security Token(CSRF Token)

- 사용자의 세션에 임의의 난수 값을 저장하고, 사용자의 요청마다 해당 난수값을 포함시켜 전송한다.
- 백엔드에서 요청을 받을 때마다 세션에 저장된 토큰 값과 요청 파라미터에서 전달되는 토큰값이 같은지 검증한다.

#### Double Submit Cookie 검증

- 웹 브라우저의 same origin 정책으로 인해 자바스크립트에서 타 도메인의 쿠키값을 확인한다.
- 스크립트에서 요청시 난수를 생성하여 쿠키에 저장되고, 동일한 난수 값을 요청 파라미터에 저장하여 서버에 전송한다.
- 서버단에서 쿠키의 토큰 값과 파라미터의 토큰 값이 일치하는지 검증한다. 
