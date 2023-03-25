package lth.com.hrm.HumanManagement.Config;

public class ApplicationConfig {

    @Configuration
    @RequiredArgsConstructor
    public class ApplicationConfig {
        private final UserRepository userRepository;

        @Bean
        public UserDetailsService userDetailsService() {
            return username -> userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(
                    ("User not found")));
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService());
            authenticationProvider.setPasswordEncoder(passwordEncoder());
            return authenticationProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}
