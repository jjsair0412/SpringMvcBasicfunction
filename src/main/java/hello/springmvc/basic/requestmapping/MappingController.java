package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class MappingController {
    //private Logger log = LoggerFactory.getLogger(getClass());

    /**
     * RequestMapping에서는 배열형식으로 여러개가 들어가도 무관하다.
     * Mapping된 uri주소가 오면 해당 메서드가 실행되는 코드이다.
     */
    @RequestMapping(value = {"/hello-basic","/hello-go"},method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value="/mapping-get-v1",method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * @PathVariable()을 사용하면
     * uri경로에 값을 넣어서 전달했을 경우
     * @PathVariable("요기")에 있는 값과 이름이 같다면
     * 그대로 받아올 수 있다.
     * -> 쿼리파라미터는 아님.
     *
     * 해당 예에선 userId가 uri로 전달되면 @PathVariable("userId")로 받아져서
     * String data안에 userId가 들어간다.
     *
     * @PathVariable 의 이름과 파라미터 이름이 같으면 생략할 수 있다 -> 변수명이 같으면 @PathVariable 이것만 써도 된다.
     * 여기선 변수명이 data였으니까 생략할 수 없는데
     * 이 변수명을 userId로 바꿔버리면 생략할 수 있다.
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}",data);
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     *
     * 여러개를 사용할 수 있다.
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }
    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     *
     * 특정 파라미터가 있거나 없는 조건을 추가할 수 있다. 잘 사용하지는 않는다.
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     *
     * 특정 헤더값의 이름이 mode고 그 헤더의 value가 debug여야 실행되는 메서드
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     *
     * 특정 content-Type에 처리하도록 할 수 있다.
     * 해당 예는 application/json일때만 얘가 실행된다. consumes을 사용해야 한다.
     *
     * consumes는 내가 받아들일때
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     *
     * produces는 내가 소비할때
     */
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }

}
