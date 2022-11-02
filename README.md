# 요구사항

## 기본

| 구현 기능           | 비고                                                        |
|-----------------|-----------------------------------------------------------|
| 메인 페이지          |                                                           |
| 회원 관리 페이지       | 회원 정보 등록 <br>회원정보 수정<br>회원정보 삭제<br>회원정보 검색                |
| 로그인 / 로그아웃 페이지  |                                                           |
| 실거래가 검색, 결과 페이지 | 전체 검색 화면 - 10<br>상세 검색 - 10<br>동별 화면 - 5<br>아파트별 검색 화면 -5 |

## 추가

- 비밀번호 찾기, 사이트맵, 메뉴 구성 화면
- 관심 지역 동네 업종 정보
- 관심 지역 대기 오염 정보
- 웹사이트 소개, 공지사항 관리 화면

---

# Where Is My Home

### Member

|이름 |
|--|
|이상원|
|정원철 |

## 기본 구현기능



### 1. 회원정보 관리

- 회원정보 확인

        로그인 상태로 현재 회원정보확인을 할수있는 "info" 탭을 누르게되면 현재 세션이 로그인되어있는 회원의 정보가 출력된다.

- 회원정보 수정

        위에서 띄운 각 회원정보란의 입력과 삭제를 통하여 회원의 정보를 수정할 수 있다. id는 readonly처리를 통하여 변경할수없도록 설정되었다.

- 회원정보 삭제

        회원탈퇴 버튼을 클릭하게되면 현재 세션에 로그인되어있는 id 값을 토대로 회원의 정보를 삭제하게된다. 현재 회원의 정보를 삭제한것이기에 회원정보 삭제뿐만 아니라 현재 로그인 되어있는 세션을 종료처리해주어 자동으로 로그아웃 하도록 설정되었다.

- 회원등록 기능

        register 버튼을 통하여 서브탭이 팝업되며 해당 회원가입팝업에서 성명, 포지션, ID, 비밀번호와 이메일 그리고 전화번호를 기준으로 회원가입을 진행한다. 이미 이전에 가입한 이력이 있는 ID(이미 DB에 존재하는 ID)는 중복검사를 통하여 확인한다. 중복되는 아이디일 경우에는 회원가입이 되지 않도록 설정을 했다.



### 2. 메인페이지 (실거래 페이지)

- 지도 api 연동

        deal info 탭에 접속하게되면 카카오맵 연동을 통해 현재 임의로 설정해놓은 위도와 경도로를 기반으로 첫 뷰가 보이게된다.

- 아파트 실거래가 검색

        deal info page에서 시/도, 구/군, 동 그리고 날짜선택을 통하여 해당 선택에 부합하는 아파트의 실거래가 매매정보가 각 정보 Colunm에 맞게 리스트로 출력이된다. 상위 지역에서 존재하는 하위지역명이 각각 다르기에 이를 따로 설정해주어 지역을 고를때 구역을 나눠서 설정했다.



### 3. 공지사항 (게시판 페이지)

- 글 목록 확인

        공지사항 페이지는 기본적으로 로그인상태에서 열람이 가능하다. 로그인이 되지 않은 상태에서는 Board탭이 활성화 되지 않고, 또한 주소로 강제로 이동을 한다고 하더라도 세션처리를 통하여 접속을 막는다. Board 탭에 접속하게되면 이전에 작성되었던 글들이 페이지 단위로 출력이된다. 각 페이지 넘버와 작성자 그리고 작성날짜가 출력되는데, 총 작성된 게시글의 수가 설정범위를 넘어가게되면 다음 페이지로 넘겨 출력하게된다.

- 글 작성

        글쓰기 버튼을 작성하게되면 제목과 내용 그리고 파일을 업로드할수있는 공란이 화면에 출력되게된다. 제목란과 글 내용란을 작성하고 파일업로드를 진행 후 확인을 누르게 되면 해당 글내용이 위 글 목록페이지에 표현된다.

### 4. 생각해봐야할 기능

- 공지사항 글 작성관련 권한 설정방법

일반적인 공지사항에서는 Admin(관리자)는 글 작성, 열람, 그리고 많으면 수정 권한까지 존재하고 일반 user는 오직 열람만을 할수있다. 그런 권한 설정을 어떻게 해야할지 생각해보았을때 가장 간단한 방법으로는 해당 사이트의 회원목록 DB에 관리자와 회원간의 차별을 둔 다른 컬럼을(0 OR 1) 설정한후 글 작성과 수정을할시 해당 컬럼의 확인을 통하여 권한이 존재하는지 체크하는 방법이 있을 것 같다고 생각을 했다. 

---

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

## BadSqlGrammarException

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

## NullPointerException

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
`MemberInterceptor`클래스에 HandlerInterceptor를 implements한다. 3가지의 메서드를 override할 수 있으며, controller의 메서드에 도달하기 전에 동작하는 `preHandler`를 사용한다. 
session이 존재하면 session에서 로그인 시 저장된 member 객체를 얻어온다. member 객체가 존재하는 경우 request의 속성에 member를 추가해 준다. Controller에서는 HttpServletRequest에서 member 속성을 받아 현재 로그인된 사용자의 정보를 사용할 수 있다. 
session이 존재하지 않거나 member 객체가 존재하지 않은 경우 index 페이지로 redirect 하게 된다. 

