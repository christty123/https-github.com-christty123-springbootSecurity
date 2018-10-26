package boss.portal.security;

import boss.portal.filter.*;
//import boss.portal.filter.MyFilterSecurityInterceptor;
import boss.portal.handler.Http401AuthenticationEntryPoint;
import boss.portal.handler.MyAuthenticationFailHander;
import boss.portal.handler.MyAuthenticationSuccessHandler;
import boss.portal.service.impl.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * SpringSecurity的配置
 * 通过SpringSecurity的配置，将JWTLoginFilter，JWTAuthenticationFilter组合在一起
 *
 * @author christ on 2017/9/13.
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Ingore URL
     */
    private static final String[] AUTH_WHITELIST = {
            "/users/signup",
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
    };

    @Autowired
    MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    MyAuthenticationFailHander myAuthenticationFailHander;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*@Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;*/


//    @Autowired
//    MyAccessDecisionManager myAccessDecisionManager;
//
//    @Autowired
//    MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;



    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/users/signup",
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
    @Autowired
    MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    // 设置 HTTP 验证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginProcessingUrl("/login").
                successHandler(myAuthenticationSuccessHandler).
                failureHandler(myAuthenticationFailHander).and().csrf().disable().anonymous().disable();
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilter(new JWTAuthenticationFilter(authenticationManager())) ;
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        System.out.printf("--------");

//        http.addFilterBefore(getInterceptor(), FilterSecurityInterceptor.class);


//                 .authorizeRequests()
//                .antMatchers(AUTH_WHITELIST).permitAll()
//                .anyRequest().authenticated() ;

//                .exceptionHandling()
//                    .authenticationEntryPoint(
//                            new Http401AuthenticationEntryPoint("Basic realm=\"MyApp\""))
//                    .and()
//                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler) // 自定义访问失败处理器
//                .and()
//                .addFilter(new JWTLoginFilter(authenticationManager()))
//                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
//                .logout() // 默认注销行为为logout，可以通过下面的方式来修改
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")// 设置注销成功后跳转页面，默认是跳转到登录页面;
////                .logoutSuccessHandler(customLogoutSuccessHandler)
//                .permitAll();
    }

    // 该方法是登录的时候会进入
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, bCryptPasswordEncoder));
    }

    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "GET"));
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailHander);
        return myUsernamePasswordAuthenticationFilter;
    }
//    @Bean
//    public MyFilterSecurityInterceptor getInterceptor(){
//        MyFilterSecurityInterceptor interceptor=new MyFilterSecurityInterceptor();
//        interceptor.setSecurityMetadataSource(myInvocationSecurityMetadataSourceService);
//        interceptor.setMyAccessDecisionManager(myAccessDecisionManager);
//        interceptor.setIngronUrl(AUTH_WHITELIST);
//        return interceptor;
//    }

    public static void main(String[] args) {
        String[] AUTH_WHITELIST = {
                "/users/signup",
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**"
        };
        PathMatcher matcher = new AntPathMatcher();

        System.out.print(matcher.match("/swagger-resources/**", "/swagger-resourcess/u/ss.css"));


    }
}
