package xyz.purema.eater.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import xyz.purema.eater.config.security.ApiAccessDeniedHandler
import xyz.purema.eater.config.security.AuthEntryPoint
import xyz.purema.eater.config.security.JwtAuthenticationFilter
import xyz.purema.eater.interfaces.ITokenService

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    tokenService: ITokenService
) : WebSecurityConfigurerAdapter() {
    private val jwtAuthenticationFilter = JwtAuthenticationFilter(tokenService)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    override fun configure(http: HttpSecurity) {
        http.csrf().disable().cors().disable()
            .addFilterAfter(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling()
            .accessDeniedHandler(ApiAccessDeniedHandler())
            .authenticationEntryPoint(AuthEntryPoint())
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/v1/customer/register", "/v1/customer/login").permitAll()
            .antMatchers("/v1/seeder/**").permitAll()
            .anyRequest().authenticated()
    }
}