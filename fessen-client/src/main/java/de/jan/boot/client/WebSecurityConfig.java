package de.jan.boot.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_PAGE = "login.xhtml";
    
    @Autowired
    UserDetailsService userDetailsService;

	@Override
    protected void configure(HttpSecurity http) throws Exception {

		//http.authorizeRequests().antMatchers("/menuPage.xhtml").access("hasAnyRole('USER', 'ADMIN')");
		http.authorizeRequests().antMatchers("/menuPage.xhtml").hasAnyRole("USER", "ADMIN");
		
		// For ADMIN only.
		//http.authorizeRequests().antMatchers("/dishAdministration.xhtml").access("hasRole('ADMIN')");
		http.authorizeRequests().antMatchers("/dishAdministration.xhtml").hasRole("ADMIN");
		
        http.authorizeRequests()
            	// require all requests to be authenticated except for the resources
                .antMatchers("/javax.faces.resource/**","/*.css", "/", LOGIN_PAGE).permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/" + LOGIN_PAGE)
                .failureUrl("/" + LOGIN_PAGE + "?error=true")
                .permitAll()
                .and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/" + LOGIN_PAGE)
                .permitAll()
                .and()
             // not needed as JSF 2.2 is implicitly protected against CSRF
            .csrf()
            	.disable();

        
    }
    

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
        throws Exception {
    	
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    	
//    	UserDetails userDetailsAdmin = User.withDefaultPasswordEncoder().username("admin").password("user").roles("ADMIN").build();
//    	auth.inMemoryAuthentication().withUser(userDetailsAdmin);
//    	UserDetails userDetailsUser1 = User.withDefaultPasswordEncoder().username("iwkor").password("user").roles("ADMIN").build();
//    	auth.inMemoryAuthentication().withUser(userDetailsUser1);
//    	UserDetails userDetailsUser2 = User.withDefaultPasswordEncoder().username("dokor").password("user").roles("ADMIN").build();
//    	auth.inMemoryAuthentication().withUser(userDetailsUser2);
//    	UserDetails userDetailsUser3 = User.withDefaultPasswordEncoder().username("ewkor").password("user").roles("ADMIN").build();
//    	auth.inMemoryAuthentication().withUser(userDetailsUser3);
//    	UserDetails userDetailsUser4 = User.withDefaultPasswordEncoder().username("jakor").password("user").roles("ADMIN").build();
//    	auth.inMemoryAuthentication().withUser(userDetailsUser4);
    }
    
    //To encode a clear text password, simply write out to the console (println()) the result of bCryptPasswordEncoder.encode(password).
    //The encoded password can then be put into the db user table.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}