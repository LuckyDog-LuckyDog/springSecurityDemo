package xiaoliu.security_session_demo;

import cn.hutool.log.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description
 *
 * @author:xiaoLiu
 */
@RestController
public class HelloController {
    Log log = Log.get();

    @Value("${server.port}")
    Integer port;

    @GetMapping("/hello")
    public String hello() {
        return "这是接口";
    }

    @PostMapping("/doLogin2")
    public String doLogin() {
        log.info("/doLogin2");
        return "这是成功登录的回调地址doLogin2";
    }
//    @PostMapping("/logout")
//    public String loginOut() {
//        log.info("/loginOut");
//        return "这是成功退出的回调地址";
//    }

    @GetMapping("/set")
    public String set(HttpSession session) {
        session.setAttribute("user", "xiaoliu");
        return String.valueOf(port);
    }

    @GetMapping("/get")
    public String get(HttpSession session) {
        return session.getAttribute("user") + ":" + port;
    }
}
