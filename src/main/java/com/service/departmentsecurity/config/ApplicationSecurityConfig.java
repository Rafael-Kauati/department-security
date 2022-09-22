package com.service.departmentsecurity.config;

import com.service.departmentsecurity.config.Roles_n_Permissions.ApplicationUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
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
                .csrf().disable() //shall come back later to enable de csrf config, after solving the httpServerlet problem
                .authorizeRequests()
                .antMatchers(WHITE_LIST_URLS) .permitAll()
                .antMatchers("/management/**") .hasRole(ApplicationUserRoles.EMPLOYEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    // im using these only for testing
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
       UserDetails client =  User.builder()
                .username("WW")
               .password(PasswordEncoder.encode ("cock"))
                .roles(ApplicationUserRoles.CLIENT.name())
                .build();

       UserDetails torres =   User.builder()
               .username("babau")
               .password( PasswordEncoder.encode ("euler"))
               .roles(ApplicationUserRoles.EMPLOYEE.name())
               .build();
        return new InMemoryUserDetailsManager(
                client,
                torres
        );
    }

}
