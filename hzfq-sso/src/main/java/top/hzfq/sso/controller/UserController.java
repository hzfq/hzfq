package top.hzfq.sso.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/5/12 20:41
 */
@RestController
public class UserController {

    @Lazy
    @Resource
    private ResourceServerTokenServices resourceServerTokenServices;

    private final AccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();

    @GetMapping(value = "/user/info")
    public Map<String, ?> checkToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String value) {

        OAuth2AccessToken token = resourceServerTokenServices.readAccessToken(value);
        if (token == null) {
            throw new InvalidTokenException("Token was not recognised");
        }

        if (token.isExpired()) {
            throw new InvalidTokenException("Token has expired");
        }

        OAuth2Authentication authentication = resourceServerTokenServices.loadAuthentication(token.getValue());

        Map<String, Object> response =
                (Map<String, Object>) accessTokenConverter.convertAccessToken(token, authentication);

        // gh-1070
        response.put("active", true);    // Always true if token exists and not expired
        response.put("username", "hzfq");

        return response;
    }
}
