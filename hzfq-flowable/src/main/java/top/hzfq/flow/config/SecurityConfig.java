package top.hzfq.flow.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author huzhifengqing@qq.com
 * @since 2022/6/7 22:56
 */
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {

//    @Configuration(proxyBeanMethods = false)
//    @Order(Integer.MIN_VALUE)
//    public static class HzfqWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests().anyRequest().permitAll();
//        }
//    }
}
