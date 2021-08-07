package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
/**
 * 일반 Controller에서는 메서드에서반환하는게 String값이라면 view의 이름으로 반환된다.
 * 그런데 RestController는 view이름이 반환되는것이 아니라
 * Http 메시지 바디에 바로입력된다.
 * 따라서 logTest의 실행 결과로 ok메세지를 받아볼수 있다.
 */
public class LogTestController {
    /**
     * 로그를 찍을 클래스를 지정해준다.
     * private final Logger log = LoggerFactory.getLogger(getClass());
     * 위 코드를 작성해서 지정해줄 수도 있지만, Lombok 라이브러리를 사용해서
     * @Slf4j라는 어노테이션으로 대체할 수 있다.
     */

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        System.out.println("name = " + name);

        /**
         * log.trace("trace log ="+name);
         * 이렇게 써도 결과는 같다. 그런데 이렇게쓰면 안됀다.
         * 왜냐하면 자바 언어는 log.trace 메서드를 실행하기 전에 ()안에있는 연산이 먼저 일어난다.
         * 그런데 trace로그를 출력하지않도록 레벨을 사용해놓는다 해도
         * 연산이 발생해버린다.
         * 그래서 저렇게 작성하면 trace로그를 출력하지않더라도 연산이 일어나기때문에 메모리손해가 발생한다.
         *
         * 그러나 log.trace("trace log ={}",name); 얘는 trace메서드를 먼저 실행시켜주기때문에
         * 동일한 레벨 조건이면 연산이 발생하지 않는다. 따라서 이렇게 써야한다.
         */
        log.trace("trace log ={}",name);
        log.debug("debug log={}",name);
        log.info("info log ={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        /**
         * System.out.println과 logger의 결과 차이
         * Svout 결과 :
         * name = Spring
         *
         * 로거 결과 :
         * 2021-08-07 15:43:18.089  INFO 5432 --- [nio-7050-exec-2] hello.springmvc.basic.LogTestController  : info log =Spring
         *
         * 2021-08-07 15:45:38.414  INFO 14264 --- [nio-7050-exec-1] hello.springmvc.basic.LogTestController  : info log =Spring
         * 2021-08-07 15:45:38.415  WARN 14264 --- [nio-7050-exec-1] hello.springmvc.basic.LogTestController  : warn log=Spring
         * 2021-08-07 15:45:38.415 ERROR 14264 --- [nio-7050-exec-1] hello.springmvc.basic.LogTestController  : error log=Spring
         *
         * trace와 debug는 남지 않는다. 레벨별로 보이는 로그를 선택할 수 있다.
         *
         * hello.springmvc 패키지와 그 하위 로그 레벨을 설정해줄 수 있다.
         * logging.level.hello.springmvc=trace
         * trace부터 하위래밸 전부다 로그를찍는다.
         *
         * application.properties에 로그를 선택하지 않는다면 디폴트값으로 info 하위레벨부터 찍힌다.
         * info, WARN, ERROR
         *
         * logging.level.root=info
         * 이렇게 설정해서 전체 페키지를 대상으로 설정해줄 수 있다.
         */
        return "ok";
    }
}
