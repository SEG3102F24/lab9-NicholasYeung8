package seg3x02.tempconverterapi.controller

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun userDetailsService(): UserDetailsService {
        return InMemoryUserDetailsManager(
            createUser("user1", "pass1"),
            createUser("user2", "pass2")
        )
    }

    private fun createUser(username: String, password: String) = 
        User.builder()
            .username(username)
            .password("{noop}$password")  // Using {noop} for plaintext passwords
            .roles("USER")
            .build()

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth -> auth.anyRequest().authenticated() }  // Secures all requests
            .httpBasic()  // Enables HTTP Basic Authentication
        return http.build()
    }
}
