package ru.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

/**
 * Класс конфигурации для настроек безопасности.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Определяет цепочку фильтров безопасности.
     *
     * @param http объект HttpSecurity
     * @return объект SecurityFilterChain
     * @throws Exception если произошла ошибка
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .requestMatchers("/auth/**", "/resources/**", "/css/**", "/js/**", "/images/**")
                .permitAll()
                .requestMatchers("/", "/upcoming-tasks/**", "/completed-tasks/**",
                        "/failed-tasks/**", "/edit-profile/**", "/update-profile/**")
                .hasAnyAuthority("USER", "ADMIN")
                .requestMatchers("/**")
                .hasAnyAuthority("ADMIN")
                .and().formLogin(form -> form
                        .loginPage("/auth/login-page")
                        .failureUrl("/auth/login-page"))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login-page")
                        .deleteCookies("token"))
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
