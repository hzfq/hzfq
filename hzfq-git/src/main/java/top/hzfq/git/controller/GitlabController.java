package top.hzfq.git.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/4/9 13:16
 */
@RequestMapping("")
@RestController
public class GitlabController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    public static final String registrationId = "gitlab";

    @GetMapping("login/oauth2/code/{clientId}")
    public void callback(@PathVariable String clientId) {
        logger.info("clientId=" + clientId);
    }
}
