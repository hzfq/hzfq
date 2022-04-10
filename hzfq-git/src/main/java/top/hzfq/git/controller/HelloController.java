package top.hzfq.git.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/4/9 13:11
 */
@RestController
public class HelloController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private OAuth2AuthorizedClientRepository clientRepository;

    @GetMapping("hello")
    public String hello(String name) {
        return "Hello, " + name;
    }

    @GetMapping("user")
    public Authentication user(Authentication authentication, HttpSession session)
            throws JsonProcessingException {
        Enumeration<String> sessionAttributeNames = session.getAttributeNames();
        while (sessionAttributeNames.hasMoreElements()) {
            String name = sessionAttributeNames.nextElement();
            logger.info("[session attr] " + name + ": "
                        + objectMapper.writeValueAsString(session.getAttribute(name)));
        }
        return authentication;
    }

    @GetMapping("client")
    public OAuth2AuthorizedClient client(Authentication authentication, HttpServletRequest request)
            throws JsonProcessingException {
        OAuth2AuthorizedClient authorizedClient =
                clientRepository.loadAuthorizedClient("gitlab", authentication, request);
        logger.info("authorizedClient: " + objectMapper.writeValueAsString(authorizedClient));
        return authorizedClient;
    }
}
