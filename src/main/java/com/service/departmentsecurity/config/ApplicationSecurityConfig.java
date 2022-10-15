package com.service.departmentsecurity.config;

import com.service.departmentsecurity.config.Roles_n_Permissions.ApplicationUserPermission;
import com.service.departmentsecurity.config.Roles_n_Permissions.ApplicationUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder PasswordEncoder;

    @Autowired
    public ApplicationSecurityConfig(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        PasswordEncoder = passwordEncoder;
    }


    private static final String[] WHITE_LIST_URLS = {
            "/",
    };


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers(WHITE_LIST_URLS) .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //Here goes the Defaults XSRF-TOKEN as cookie name and X-XSRF-TOKEN as header name
                //Relevant note : On a level of api testing ( in my case im using postman ) i will disable ( comment ) the csrf token config
                //but in a real functional and security microservice context , this configuration should be always enabled  -->
                //.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .httpBasic();
    }



}
