package main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST, "/user/login", "/user/registration", "/center/newCenter");
        web.ignoring().antMatchers(HttpMethod.PUT, "/");
        web.ignoring().antMatchers(HttpMethod.GET, "/", 
                "/webjars/", "/*.html", "/favicon.ico",
                "//.html", "/**/.css", "/*/.js");
	}
}
