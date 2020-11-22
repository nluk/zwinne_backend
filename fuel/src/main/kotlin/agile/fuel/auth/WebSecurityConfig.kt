package agile.fuel.auth

import agile.fuel.config.MongoProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.annotation.Resource


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(value = [JwtConfigProperties::class])
class WebSecurityConfig(
        @Qualifier("mongoAuth")
        @Resource
        val userDetailsService: UserDetailsService,
) : WebSecurityConfigurerAdapter() {

    @Autowired
    @Throws(Exception::class)
    fun globalUserDetails(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder())
    }

    @Bean
    fun authenticationTokenFilterBean(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(userDetailsService, tokenUtil())
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun unauthorizedHandler() = JwtAuthenticationEntryPoint()

    @Bean
    fun encoder() = BCryptPasswordEncoder()

    @Bean
    fun tokenUtil() = TokenUtil()

}