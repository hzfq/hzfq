package top.hzfq.git.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.AuthenticatedPrincipalOAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

import javax.annotation.Resource;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/4/9 13:22
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * OAuth2授权客户端配置方式
     * 1.oauth2Login
     * 2.oauth2Client
     * 官方文档 https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html
     * <p>
     * Filters
     * 1.OAuth2AuthorizationRequestRedirectFilter
     * 2.OAuth2LoginAuthenticationFilter
     * 3.OAuth2AuthorizationCodeGrantFilter
     * <p>
     * Providers
     * 1.OAuth2LoginAuthenticationProvider
     * 2.OAuth2AuthorizationCodeAuthenticationProvider
     */

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .requestCache().disable()
                .authorizeRequests()
                .mvcMatchers("/oauth2/**").permitAll()
                .mvcMatchers("/favicon.ico").permitAll()
                .mvcMatchers("/public/**").permitAll()
                .mvcMatchers("/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login(oAuth2LoginConfigurer());
//                .oauth2Login(Customizer.withDefaults());
    }

    /**
     * 1.用户访问受限资源
     * 2.授权请求重定向过滤器检测到用户未登录
     * 3.将用户重定向到oauth2授权服务器
     * 4.授权服务器响应用户操作。回调接口
     * 5.回调接口接收授权服务器请求信息
     * 6.判断用户授权状态并进一步处理
     */

    /**
     * 自定义oauth2登录配置
     * <p>
     * 扩展时可参考默认配置逻辑{@link OAuth2LoginConfigurer#init(HttpSecurityBuilder)}}
     */
    private Customizer<OAuth2LoginConfigurer<HttpSecurity>> oAuth2LoginConfigurer() {
        return configurer -> {
            configurer.successHandler((request, response, authentication) -> {
                logger.info("auth success. auth: " + objectMapper.writeValueAsString(authentication));
                //授权成功处理逻辑
//                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//                response.setStatus(HttpStatus.OK.value());
//                response.getWriter().println(objectMapper.writeValueAsString(authentication));

                RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
                redirectStrategy.sendRedirect(request, response, "/");
            });
            configurer.failureHandler((request, response, exception) -> {
                String localizedMessage = exception.getLocalizedMessage();
                logger.info("auth failure. exception: " + localizedMessage);
                //授权失败处理逻辑
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                response.setStatus(HttpStatus.OK.value());
                response.getWriter().println(objectMapper.writeValueAsString(localizedMessage));
            });
        };
    }

    //---------------------------- 自定义bean start ----------------------------
    // 自定义实现时在bean方法上添加注释 @Bean

    /**
     * 存储应用注册信息
     * <p>
     * 可自定义实现存储
     */
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository();
    }

    /**
     * 存储用户认证信息
     */
    public OAuth2AuthorizedClientRepository authorizedClientRepository() {
        //会话存储
//        return new HttpSessionOAuth2AuthorizedClientRepository();
        //认证存储
        return new AuthenticatedPrincipalOAuth2AuthorizedClientRepository(authorizedClientService());
    }

    /**
     * 已授权客户信息
     */
    public OAuth2AuthorizedClientService authorizedClientService() {
        //内存存储
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
        //持久化存储
        //此时需要引入相应持久化依赖 spring-boot-starter-jdbc,mysql
//        return new JdbcOAuth2AuthorizedClientService(jdbcTemplate, clientRegistrationRepository());
    }

    //---------------------------- 自定义bean end ----------------------------

}
