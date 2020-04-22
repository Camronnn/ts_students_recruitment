package com.amirdigiev.tsaritsynostudentportfolio.config;

import com.amirdigiev.tsaritsynostudentportfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.amirdigiev.tsaritsynostudentportfolio")
@SuppressWarnings("Duplicates")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Configuration
//    @Order(1)
//    public static class StudentSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        private final StudentService studentService;
//        private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//        @Autowired
//        public StudentSecurityConfig(@Qualifier("studentService") StudentService studentService,
//                                     BCryptPasswordEncoder bCryptPasswordEncoder) {
//            this.studentService = studentService;
//            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.requestMatchers()
//                        .antMatchers("/login/student", "/registration/student")
//                    .and()
//                        .authorizeRequests()
//                        .anyRequest().authenticated()
//
//                    .and()
//                        .formLogin().permitAll()
//                            .loginPage("/login/student")
//                            .usernameParameter("username")
//                            .passwordParameter("password")
//                            .defaultSuccessUrl("/home", true)
//
//                    .and()
//                        .logout()
//                            .logoutUrl("/logout")
//                            .logoutSuccessUrl("/login/student")
//                            .invalidateHttpSession(true)
//                            .deleteCookies("JSESSIONID");
//
//            http.headers()
//                        .cacheControl().disable()
//                    .and()
//                        .csrf().disable();
//        }
//
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            web.ignoring().antMatchers("/static/**", "/images/**");
//        }
//
//        @Override
//        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(studentService).passwordEncoder(bCryptPasswordEncoder);
//        }
//
//        @Bean
//        public AuthenticationManager authenticationManager() throws Exception {
//            return super.authenticationManager();
//        }
//    }
//
//    @Configuration
//    public static class DirectorSecurityConfig extends WebSecurityConfigurerAdapter {
//
//        private final DirectorService directorService;
//        private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//        @Autowired
//        public DirectorSecurityConfig(@Qualifier("directorService") DirectorService directorService,
//                                      BCryptPasswordEncoder bCryptPasswordEncoder) {
//            this.directorService = directorService;
//            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http.requestMatchers()
//                        .antMatchers("/login/director", "/registration/director")
//                    .and()
//                        .authorizeRequests()
//                        .anyRequest().authenticated()
//
//                    .and()
//                        .formLogin().permitAll()
//                            .loginPage("/login/director_login")
//                            .usernameParameter("username")
//                            .passwordParameter("password")
//                            .defaultSuccessUrl("/home", true)
//                    .and()
//                        .logout()
//                            .logoutUrl("/logout")
//                            .logoutSuccessUrl("/login/director_login")
//                            .invalidateHttpSession(true)
//                            .deleteCookies("JSESSIONID");
//
//            http.headers()
//                    .cacheControl().disable()
//                    .and()
//                    .csrf().disable();
//        }
//    }

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                    .antMatchers("/registration")
                .and()
                    .authorizeRequests()
                    .anyRequest().authenticated()

                .and()
                    .formLogin().permitAll()
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home", true)
                .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID");

        http.headers()
                .cacheControl().disable()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**", "/images/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}