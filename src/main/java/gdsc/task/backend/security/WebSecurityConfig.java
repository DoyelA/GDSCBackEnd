package gdsc.task.backend.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.user.name}")
    private String username;

    @Value("${spring.security.user.password}")
    private String password;

    @Value("${spring.security.user.roles}")
    private String roles;

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().csrf().disable();
//    }

    //user authentication using form login
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(new UserDetails() {
            @Override
            public String getPassword() {
                return password ;
            }

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities(){
                return Arrays.asList(new SimpleGrantedAuthority(roles));
            }
        }).passwordEncoder(passwordEncoder()).and().authenticationProvider(new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                UserDetails userDetails=auth.getDefaultUserDetailsService().loadUserByUsername((String) authentication.getPrincipal());
                if(userDetails.getPassword().equals(authentication.getCredentials())) {
                    List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
                    userDetails.getAuthorities().forEach(grantedAuthority -> {
                        simpleGrantedAuthorityList.add(new SimpleGrantedAuthority(grantedAuthority.getAuthority()));
                    });
                    return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), simpleGrantedAuthorityList);
                }
                return null;
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder(){
            @Override
            public String encode(CharSequence rawPassword) {
                return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return new String(Base64.getDecoder().decode(encodedPassword.toString().getBytes())).matches(rawPassword.toString());
            }
        };
    }
}
