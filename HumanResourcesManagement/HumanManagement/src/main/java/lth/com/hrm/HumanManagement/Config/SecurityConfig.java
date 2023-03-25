package lth.com.hrm.HumanManagement.Config;

@Configuration // Config Security
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Accept for Authorization of Controller
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    // @Autowired
    // private final AuthenticateEntryPoint authenticateEntryPoint;
    // @Autowired
    // private final RestAccessDeniedHandler restAccessDeniedHandler;
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
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/login")
                .permitAll()// Accept all Roles
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/demo/**")
                .authenticated() // Required Authenticate
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
