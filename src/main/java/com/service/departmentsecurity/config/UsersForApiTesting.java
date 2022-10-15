package com.service.departmentsecurity.config;

import com.service.departmentsecurity.config.Roles_n_Permissions.ApplicationUserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class UsersForApiTesting  {

    @Autowired
    private final PasswordEncoder PasswordEncoder;

    public UsersForApiTesting(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        PasswordEncoder = passwordEncoder;
    }

    // im using these only for testing
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails walterbranco =  User.builder()
                .username("WW")
                .password(PasswordEncoder.encode ("cock"))
                //.roles(ApplicationUserRoles.CLIENT.name())
                .authorities(ApplicationUserRoles.EMPLOYEE.getGrantedAuthorities())
                .build();

        UserDetails erika =   User.builder()
                .username("erika")
                .password( PasswordEncoder.encode ("tiger2"))
                //.roles(ApplicationUserRoles.EMPLOYEE.name())
                .authorities(ApplicationUserRoles.CLIENT.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(
                walterbranco,
                erika
        );
    }
}
