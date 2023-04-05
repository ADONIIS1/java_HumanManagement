package lth.com.hrm.HumanManagement.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration // Config Security
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Accept for Authorization of Controller
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    RestAccessDeniedHandler accessDeniedHandler() {
        return new RestAccessDeniedHandler();
    }

    @Bean
    AuthenticateEntryPoint authenticationEntryPoint() {
        return new AuthenticateEntryPoint();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers("/auth/login")
                .permitAll()// Accept all Roles
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/auth/refreshtoken")
                .permitAll()// Accept all Roles
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/demo/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/user/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/role/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/blog/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/degree/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/department/**")
                .authenticated()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/salary/**")
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
