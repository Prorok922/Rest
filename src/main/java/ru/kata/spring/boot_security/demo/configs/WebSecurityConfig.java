package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.service.UserService;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                //Доступ разрешен всем пользователей
                .antMatchers("/", "/resources/**").permitAll()
                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                //Настройка для входа в систему
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                //Перенарпавление на главную страницу после успешного входа
                .successHandler(new SuccessUserHandler())
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                //включение авторизации basic
                .and().httpBasic();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
// example json
//[{"id":1,"firstName":"USER","lastName":"USEROVICH","age":20,"email":"USER@MAIL.RU","password":"$2a$12$N20Ze5E0JC.gYgOcuMCOYOlvQQdubphk3hRTt.J82jc0ELeSTlV2u",
//        "roles":[{"id":2,"role":"ROLE_USER","authority":"ROLE_USER"}],
//        "enabled":true,
//        "authorities":[{"id":2,"role":"ROLE_USER","authority":"ROLE_USER"}],
//        "username":"USER@MAIL.RU",
//        "accountNonLocked":true,
//        "roleString":"USER",
//        "credentialsNonExpired":true,
//        "accountNonExpired":true},
//
//        {"id":2,"firstName":"ADMIN","lastName":"ADMINOVICH","age":20,"email":"ADMIN@MAIL.RU","password":"$2a$12$hRZaQPT0gA4GWwRdCSbIO.vauQzazDPKYLWkQAvtpMylcDze6eG1e",
//        "roles":[{"id":1,"role":"ROLE_ADMIN","authority":"ROLE_ADMIN"},{"id":2,"role":"ROLE_USER","authority":"ROLE_USER"}],
//        "enabled":true,
//        "authorities":[{"id":1,"role":"ROLE_ADMIN","authority":"ROLE_ADMIN"},{"id":2,"role":"ROLE_USER","authority":"ROLE_USER"}],
//        "username":"ADMIN@MAIL.RU",
//        "accountNonLocked":true,
//        "roleString":"ADMIN USER",
//        "credentialsNonExpired":true,
//        "accountNonExpired":true}]