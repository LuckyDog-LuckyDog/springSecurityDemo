package xiaoliu.security_session_demo.configure;

import cn.hutool.log.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

/**
 * Description
 *
 * @author:xiaoLiu
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Log log = Log.get();

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

//    /**
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("lqh")
//                .password("lqh").roles("admin")
//                .and()
//                .withUser("lqh2")
//                .password("lqh2").roles("user");
//    }

    /**
     * form 表单的相关配置在 FormLoginConfigurer 中，
     * 该类继承自 AbstractAuthenticationFilterConfigurer ，
     * 所以当 FormLoginConfigurer 初始化的时候，AbstractAuthenticationFilterConfigurer 也会初始化，
     * 在 AbstractAuthenticationFilterConfigurer 的构造方法中，我们可以看到：
    * 	   protected AbstractAuthenticationFilterConfigurer() { setLoginPage("/login"); }
     *另一方面，FormLoginConfigurer 的初始化方法 init 方法中也调用了父类的 init 方法：updateAuthenticationDefaults，我们来看下这个方法：
     *     if (this.loginProcessingUrl == null) { loginProcessingUrl(this.loginPage) }
     *登录表单中的参数是 username 和 password，注意，默认情况下，这个不能变：
     *FormLoginConfigurer 类中，在它的构造方法中，我们可以看到有两个配置用户名密码的方法：
     *      public FormLoginConfigurer() {
     *      	super(new UsernamePasswordAuthenticationFilter(), null);
     *      	usernameParameter("username"); ----->UsernamePasswordAuthenticationFilter注入用户名
     *      	passwordParameter("password"); ----->UsernamePasswordAuthenticationFilter注入密码
     *      }
     * 在 UsernamePasswordAuthenticationFilter 该拦截器中 , 会进行如下判断
     *     protected String obtainPassword(HttpServletRequest request) {return request.getParameter(this.passwordParameter);}
     *     protected String obtainUsername(HttpServletRequest request) {return request.getParameter(this.usernameParameter);}
     *其中: username 和 password 这两个参数可以自己配置，方式如下：
     *   .usernameParameter("username")
     *   .passwordParameter("password")
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info(">>>>>>>>>>>>>>设置安全配置");
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
//                .loginProcessingUrl("/doLogin")
//                .usernameParameter("username")
//                .passwordParameter("password")
                .defaultSuccessUrl("/doLogin")
                .successForwardUrl("/doLogin")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .logoutSuccessUrl("/index")
                .deleteCookies()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf().disable();
    }

    /**
     * 用来忽略web的静态文件访问
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**","/images/**");
    }
}
