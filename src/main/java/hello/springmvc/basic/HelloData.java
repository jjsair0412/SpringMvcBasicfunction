package hello.springmvc.basic;

import lombok.Data;

/**
 * lombok의 @Data를 써주면 get,set,toString등 여러가지를 다 자동으로 만들어준다.
 */
@Data
public class HelloData {
    private String username;
    private int age;

}
