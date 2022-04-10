package top.hzfq.git.controller.templates;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/4/10 9:04
 */
@Controller
public class PageController {

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
